package com.jermaine.newskmm.data

import com.jermaine.newskmm.domain.article.Article
import com.jermaine.newskmm.domain.core.Result
import com.jermaine.newskmm.network.article.ArticleRemoteSource

class ArticleRepository() {
    private val articleRemoteSource: ArticleRemoteSource by lazy {
        ArticleRemoteSource()
    }

    fun getArticles(callback: (Result<List<Article>>) -> Unit) {
        articleRemoteSource.getArticles(callback)
    }
}
