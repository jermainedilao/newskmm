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
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jermaine.newskmm.android.ui.features.details.Details
import com.jermaine.newskmm.android.ui.features.home.Home
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep
import com.jermaine.newskmm.android.ui.utils.ARGS_ARTICLE
import com.jermaine.newskmm.android.ui.utils.NAV_DETAILS
import com.jermaine.newskmm.android.ui.utils.NAV_HOME
import com.jermaine.newskmm.ui.theme.AppTheme

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
        composable(NAV_HOME) { Home(navController = navController) }
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
                navController = navController,
                navController
                    .previousBackStackEntry
                    ?.arguments
                    ?.getParcelable(
                        ARGS_ARTICLE
                    )
            )
        }
    }
}