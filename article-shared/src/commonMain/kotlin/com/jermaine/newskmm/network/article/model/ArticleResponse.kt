package com.jermaine.newskmm.network.article.model

import com.jermaine.newskmm.domain.article.Article
import com.jermaine.newskmm.domain.article.Source
import kotlinx.serialization.Serializable

@Serializable
internal data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDTO>
)

@Serializable
internal data class ArticleDTO(
    val source: SourceDTO,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
) {
    companion object {
        fun toDomain(articleDto: ArticleDTO): Article {
            return with(articleDto) {
                Article(
                    source = SourceDTO.toDomain(source),
                    author = author.orEmpty(),
                    title = title.orEmpty(),
                    description = description.orEmpty(),
                    url = url.orEmpty(),
                    urlToImage = urlToImage.orEmpty(),
                    publishedAt = publishedAt.orEmpty(),
                    content = content.orEmpty()
                )
            }
        }
    }
}

@Serializable
internal data class SourceDTO(
    val id: String?,
    val name: String?
) {
    companion object {
        fun toDomain(sourceDto: SourceDTO): Source {
            return with(sourceDto) {
                Source(
                    id = id.orEmpty(),
                    name = name.orEmpty()
                )
            }
        }
    }
}
