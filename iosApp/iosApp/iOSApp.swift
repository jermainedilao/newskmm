import SwiftUI
import shared_articles

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
