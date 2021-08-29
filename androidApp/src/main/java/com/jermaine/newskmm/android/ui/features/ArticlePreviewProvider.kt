package com.jermaine.newskmm.android.ui.features

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep
import com.jermaine.newskmm.android.ui.features.model.SourceViewRep

class ArticlePreviewProvider : PreviewParameterProvider<ArticleViewRep> {
    override val values: Sequence<ArticleViewRep> = fakeArticles.asSequence()
}

private val fakeArticles = listOf(
    ArticleViewRep(
        source = SourceViewRep(
            id = "123",
            name = "techcrunch"
        ),
        author = "Jermaine Dilao",
        title = "Sample Title",
        description = "Two US senators are calling on the Federal Trade Commission to investigate Tesla over the company's claims that its vehicles are self-driving, after a number of deadly crashes involving Tesla models using the autopilot function. Read Full Article at RT.com",
        urlToImage = "https://cdni.rt.com/files/2021.08/article/611e2c542030272c5f53ce51.JPG",
        url = "https://www.rt.com/business/532473-tesla-deceptive-autopilot-claims/",
        publishedAt = "2021-08-19T10:00:49Z",
        content = "Christy Ai and Ben Swann discuss the cult of personality phenomenon of Elon Musk and how it may be the reason why the company has gotten away with making deceptive claims for so long. For more stori… [+54 chars]"
    )
)