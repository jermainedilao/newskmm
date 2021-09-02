object Compose {
    const val version = "1.1.0-alpha02"
    private const val activityVersion = "1.3.0-beta02"
    private const val navigationVersion = "2.4.0-alpha05"
    private const val navigationAnimationVersion = "0.17.0"
    private const val coilVersion = "1.3.2"
    private const val swipeRefreshVersion = "0.17.0"

    const val ui = "androidx.compose.ui:ui:$version"
    const val material = "androidx.compose.material:material:$version"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
    const val activity = "androidx.activity:activity-compose:$activityVersion"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"
    const val navigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:$navigationAnimationVersion"
    const val runtime = "androidx.compose.runtime:runtime:$version"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$version"
    const val coil = "io.coil-kt:coil-compose:$coilVersion"
    const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$swipeRefreshVersion"
}
