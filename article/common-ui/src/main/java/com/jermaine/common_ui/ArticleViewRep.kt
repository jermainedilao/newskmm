package com.jermaine.common_ui

import android.os.Parcelable
import com.jermaine.newskmm.domain.article.Article
import com.jermaine.newskmm.domain.article.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleViewRep(
    val source: SourceViewRep,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
) : Parcelable {
    companion object {
        fun fromDomain(article: Article): ArticleViewRep {
            return with(article) {
                ArticleViewRep(
                    source = SourceViewRep.fromDomain(source),
                    author = author,
                    title = title,
                    description = description,
                    url = url,
                    urlToImage = urlToImage,
                    publishedAt = publishedAt,
                    content = content
                )
            }
        }
    }
}

@Parcelize
data class SourceViewRep(
    val id: String,
    val name: String
) : Parcelable {
    companion object {
        fun fromDomain(source: Source): SourceViewRep {
            return with(source) {
                SourceViewRep(
                    id = id,
                    name = name
                )
            }
        }
    }
}
