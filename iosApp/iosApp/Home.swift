import SwiftUI
import shared
import Combine

struct Home: View {
    let greet = Greeting().greeting()
    
    @ObservedObject var viewModel: Home.ViewModel
    
	var body: some View {
        ArticleList(
            articles: viewModel.articles
        )
	}
}

extension Home {
    class ViewModel : ObservableObject {
        
        @Published var articles = [Article]()
        
        let articlesRepository: ArticleRepository
        
        init(articlesRepository: ArticleRepository) {
            self.articlesRepository = articlesRepository
            getArticles()
        }
        
        func getArticles() {
            articlesRepository.getArticles(callback: { result in
                if let success = result as? ResultSuccess {
                    print("success")
                    self.articles = (success.data as! [Article])
                } else {
                    print("error!!!")
                }
            })
        }
    }
}

struct ArticleList : View {
    var articles = [Article]()
    
    var body: some View {
        NavigationView {
            List(articles, id: \.title) { article in
                // https://stackoverflow.com/a/59832389/5285687
                ZStack {
                    ArticleRow(article: article)
                    NavigationLink(destination: Details()) {
                        EmptyView()
                    }
                    .buttonStyle(PlainButtonStyle())
                }
            }
            .navigationTitle("NewsKMM")
        }
    }
}

struct ArticleRow: View {
    var article: Article
    
    var body: some View {
        VStack {
            if article.urlToImage != nil {
                let url = URL(string: article.urlToImage!)
                if url != nil {
                    AsyncImage(
                        url: url!,
                        placeholder: { Text("Loading...") }
                    ).aspectRatio(contentMode: .fit)
                }
            }
            Text(article.title)
            Text(article.description_)
                .font(.system(size: 14))
        }
    }
}

class ImageLoader : ObservableObject {
    @Published var image: UIImage?
    private let url: URL
    
    private var cancellable: AnyCancellable?
    private var cache: ImageCache?
    
    init(url: URL, cache: ImageCache? = nil) {
        self.url = url
        self.cache = cache
    }
    
    deinit {
        cancel()
    }
    
    func load () {
        if let image = cache?[url] {
            self.image = image
            return
        }
        
        cancellable = URLSession.shared.dataTaskPublisher(for: url)
            .map{ UIImage(data: $0.data) }
            .replaceError(with: nil)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] in self?.image = $0 }
    }
    
    func cancel() {
        cancellable?.cancel()
    }
}


struct AsyncImage<Placeholder : View> : View {
    @StateObject private var loader: ImageLoader
    private let placeholder: Placeholder
    
    init(url: URL, @ViewBuilder placeholder: () -> Placeholder) {
        self.placeholder = placeholder()
        _loader = StateObject(wrappedValue: ImageLoader(url: url))
    }
    
    var body: some View {
        content.onAppear(perform: loader.load)
    }
    
    private var content: some View {
        Group {
            if loader.image != nil {
                Image(uiImage: loader.image!)
                    .resizable()
            } else {
                placeholder
            }
        }
    }
}

protocol ImageCache {
    subscript(_url: URL) -> UIImage? { get set }
}

struct TemporaryImageCache: ImageCache {
    private let cache = NSCache<NSURL, UIImage>()
    
    subscript(_ key: URL) -> UIImage? {
        get { cache.object(forKey: key as NSURL) }
        set { newValue == nil ? cache.removeObject(forKey: key as NSURL) : cache.setObject(newValue!, forKey: key as NSURL) }
    }
}
