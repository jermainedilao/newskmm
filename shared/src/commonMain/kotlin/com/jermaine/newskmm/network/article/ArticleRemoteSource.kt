package com.jermaine.newskmm.network.article

import com.jermaine.newskmm.BuildKonfig
import com.jermaine.newskmm.domain.article.Article
import com.jermaine.newskmm.domain.core.Result
import com.jermaine.newskmm.network.Api.httpClient
import com.jermaine.newskmm.network.article.model.ArticleDTO
import com.jermaine.newskmm.network.article.model.ArticleResponse
import com.jermaine.newskmm.network.base.BaseRemoteSource
import io.ktor.client.request.*

internal class ArticleRemoteSource() : BaseRemoteSource() {

    fun getArticles(callback: (Result<List<Article>>) -> Unit) {
        launch {
            val url = "https://newsapi.org/v2/everything"
            val response: ArticleResponse = httpClient.get(url) {
                parameter("sources", "techcrunch,techradar")
                parameter("sortBy", "publishedAt")
                parameter("apiKey", BuildKonfig.news_api_key)
            }

            print(response)

            callback.invoke(
                Result.Success(
                    response.articles.map(ArticleDTO::toDomain)
                )
            )
        }
    }
}
