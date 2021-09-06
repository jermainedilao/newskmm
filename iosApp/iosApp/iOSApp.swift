import SwiftUI
import article_shared

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			Home(
                viewModel: .init(
                    articlesRepository: ArticleRepository()
                )
            )
		}
	}
}
