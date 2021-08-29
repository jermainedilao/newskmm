package com.jermaine.newskmm.android.ui.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jermaine.newskmm.android.R

@Preview
@Composable
fun KmmTopAppBar(
    title: String = stringResource(id = R.string.app_name),
    isChildScreen: Boolean = true,
    onNavigateBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = if (isChildScreen) {
            {
                IconButton(onClick = onNavigateBackClick) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}