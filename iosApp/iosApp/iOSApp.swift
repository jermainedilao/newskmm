import SwiftUI
import shared

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
