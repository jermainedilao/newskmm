package com.jermaine.newskmm.android.ui.features.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jermaine.newskmm.android.R
import com.jermaine.newskmm.android.ui.common.KmmTopAppBar
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep

@Composable
fun Details(navController: NavController, article: ArticleViewRep?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        KmmTopAppBar(stringResource(id = R.string.details)) {
            navController.navigateUp()
        }
    }
}