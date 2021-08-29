package com.jermaine.newskmm.android.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jermaine.newskmm.android.R
import com.jermaine.newskmm.android.ui.common.KmmTopAppBar
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep
import com.jermaine.newskmm.android.ui.features.model.SourceViewRep
import com.jermaine.newskmm.android.ui.utils.ARGS_ARTICLE
import com.jermaine.newskmm.android.ui.utils.NAV_DETAILS

@Composable
fun Home(navController: NavController) {
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory())
    val articles: List<ArticleViewRep> by viewModel.articles.observeAsState(emptyList())
    val isRefreshing by viewModel.isRefreshing.observeAsState()

    ArticleList(
        articles = articles,
        isRefreshing = isRefreshing ?: false,
        onRefresh = viewModel::getArticles,
        onItemClick = { item ->
            navController.currentBackStackEntry?.arguments = bundleOf(
                ARGS_ARTICLE to item
            )
            navController.navigate(NAV_DETAILS)
        }
    )
}

@Composable
fun ArticleList(
    articles: List<ArticleViewRep>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onItemClick: (ArticleViewRep) -> Unit
) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize()) {
        KmmTopAppBar(
            title = stringResource(id = R.string.app_name),
            isChildScreen = false
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = onRefresh
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(articles) { article ->
                    ArticleItem(article = article, onItemClick = onItemClick)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleItem(
    @PreviewParameter(ArticleProvider::class) article: ArticleViewRep,
    onItemClick: (ArticleViewRep) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .clickable {
                onItemClick(article)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp
    ) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = article.urlToImage
                ),
                contentDescription = "Article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f, false),
                contentScale = ContentScale.Crop
            )
            Text(
                text = article.title,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                maxLines = 3
            )
            Text(
                text = article.description,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                maxLines = 5
            )
        }
    }
}

class ArticleProvider : PreviewParameterProvider<ArticleViewRep> {
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