package com.jermaine.newskmm.android.ui.features

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jermaine.article_details.ui.Details
import com.jermaine.article_list.ui.Home
import com.jermaine.common_ui.ArticleViewRep
import com.jermaine.components.theme.AppTheme
import com.jermaine.newskmm.android.ui.utils.ARGS_ARTICLE
import com.jermaine.newskmm.android.ui.utils.NAV_DETAILS
import com.jermaine.newskmm.android.ui.utils.NAV_HOME

@ExperimentalAnimationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    App()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun App() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = NAV_HOME) {
        buildArticleList(navController)
        buildArticleDetails(navController)
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.buildArticleList(navController: NavController) {
    composable(NAV_HOME) {
        Home { item ->
            navController.currentBackStackEntry?.arguments = bundleOf(
                ARGS_ARTICLE to item
            )
            navController.navigate(NAV_DETAILS)
        }
    }
}

@ExperimentalAnimationApi
private fun NavGraphBuilder.buildArticleDetails(navController: NavHostController) {
    composable(
        NAV_DETAILS,
        enterTransition = { initial, _ ->
            when (initial.destination.route) {
                NAV_HOME -> {
                    // When initial route is HOME, perform slide in from right animation.
                    slideIntoContainer(
                        towards = AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                }
                else -> {
                    null
                }
            }
        },
        exitTransition = { _, target ->
            when (target.destination.route) {
                NAV_HOME -> {
                    // When initial route is HOME, perform slide in from right animation.
                    slideOutOfContainer(
                        towards = AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
                else -> {
                    null
                }
            }
        },
        arguments = listOf(
            navArgument("$NAV_DETAILS/$ARGS_ARTICLE") {
                type = NavType.ParcelableType(ArticleViewRep::class.java)
            }
        )
    ) {
        Details(
            navController
                .previousBackStackEntry
                ?.arguments
                ?.getParcelable(
                    ARGS_ARTICLE
                )
        ) {
            navController.navigateUp()
        }
    }
}
