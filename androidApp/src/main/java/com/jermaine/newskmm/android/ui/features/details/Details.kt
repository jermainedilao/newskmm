package com.jermaine.newskmm.android.ui.features.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jermaine.newskmm.android.R
import com.jermaine.newskmm.android.ui.common.KmmTopAppBar
import com.jermaine.newskmm.android.ui.features.ArticlePreviewProvider
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep

@Composable
fun Details(navController: NavController, article: ArticleViewRep?) {
    Column {
        KmmTopAppBar(stringResource(id = R.string.details)) {
            navController.navigateUp()
        }
        Content(article = article)
    }
}

@Preview(showBackground = true)
@Composable
private fun Content(@PreviewParameter(ArticlePreviewProvider::class) article: ArticleViewRep?) {
    if (article == null) return

    Column(modifier = Modifier.fillMaxSize()) {
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
                fontWeight = FontWeight.Bold
            )
            Text(
                text = article.description,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 8.dp)
            )
        }
    }
}