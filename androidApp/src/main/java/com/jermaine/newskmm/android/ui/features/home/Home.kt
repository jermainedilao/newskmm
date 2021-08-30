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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
import com.jermaine.newskmm.android.ui.features.ArticlePreviewProvider
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep
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
                    vertical = 16.dp
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
    @PreviewParameter(ArticlePreviewProvider::class) article: ArticleViewRep,
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
                maxLines = 3,
                fontWeight = FontWeight.Bold
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