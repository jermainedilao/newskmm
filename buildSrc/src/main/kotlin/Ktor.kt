object Ktor {
    private const val version = "1.6.2"
    private const val logbackClassicVersion = "1.2.5"

    // Common
    const val clientCore = "io.ktor:ktor-client-core:$version"
    const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
    const val logbackClassic = "ch.qos.logback:logback-classic:$logbackClassicVersion"

    // Android
    const val clientAndroid = "io.ktor:ktor-client-android:$version"
    const val clientCioJvm = "io.ktor:ktor-client-cio-jvm:$version"
    const val clientAuthJvm = "io.ktor:ktor-client-auth-jvm:$version"
    const val clientLoggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
    const val clientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"

    // iOS
    const val clientIos = "io.ktor:ktor-client-ios:$version"
}