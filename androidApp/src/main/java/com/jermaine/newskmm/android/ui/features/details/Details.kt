package com.jermaine.newskmm.android.ui.features.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
    // Note: Needed to save it as state here. Navigating up in jetpack compose
    // causes this composable to get recreated which results to weird transition.
    val art = rememberSaveable {
        mutableStateOf(article)
    }
    Column {
        KmmTopAppBar(stringResource(id = R.string.details)) {
            navController.navigateUp()
        }
        Content(article = art.value)
    }
}

@Preview(showBackground = true)
@Composable
private fun Content(@PreviewParameter(ArticlePreviewProvider::class) article: ArticleViewRep?) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            Image(
                painter = rememberImagePainter(
                    data = article?.urlToImage
                ),
                contentDescription = "Article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f, false),
                contentScale = ContentScale.Crop
            )
            Text(
                text = article?.title.orEmpty(),
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = article?.description.orEmpty(),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Text(
                text = stringResource(id = R.string.author_format, article?.author.orEmpty()),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 16.dp),
            )
            Text(
                text = stringResource(id = R.string.source_format, article?.source?.name.orEmpty()),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 16.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.published_at_format,
                    article?.publishedAt.orEmpty()
                ),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 16.dp),
            )

            // Reference: https://stackoverflow.com/a/65656351/5285687
            val annotatedString = buildAnnotatedString {
                val string = article?.url.orEmpty()
                val startIndex = string.indexOf("https")
                val endIndex = startIndex + string.length

                append(string)
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = startIndex,
                    end = endIndex
                )

                addStringAnnotation(
                    tag = "URL",
                    annotation = article?.url.orEmpty(),
                    start = startIndex,
                    end = endIndex
                )
            }
            val uriHandler = LocalUriHandler.current
            ClickableText(
                text = annotatedString,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 16.dp),
                onClick = {
                    // TODO: 8/30/21 Open url
                    annotatedString
                        .getStringAnnotations("URL", it, it)
                        .firstOrNull()
                        ?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
    }
}