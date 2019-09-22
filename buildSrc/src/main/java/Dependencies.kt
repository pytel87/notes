object ApplicationId {
    val id = "com.pytel.notes"
}

object Modules {
    val app = ":app"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "3.4.2"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    const val room = "2.1.0-beta01"

    val googleAuth = "16.0.1"

    val navigationComponent = "2.0.0"
    val constraintLayout = "1.1.2"


    val googleServices = "4.3.0"
    val firebaseAnalytics = "17.2.0"

    val firebaseAuth = "16.0.4"
    val firebaseCore = "16.0.4"

    val fabric = "1.31.0"

    val appCompat = "1.0.2"
    val design = "1.0.0"
    val cardView = "1.0.0"
    val recyclerView = "1.0.0"
    val maps = "15.0.1"

    val ktx = "1.0.2"
    val coroutinesCore = "1.1.1"
    val coroutinesAndroid = "1.1.1"
    val kotlin = "1.3.41"
    val timber = "4.7.1"
    val rxjava = "2.2.10"
    val rxkotlin = "2.3.0"
    val retrofit = "2.6.0"
    val loggingInterceptor = "4.0.0"
    val glide = "4.9.0"
    val rxpaper = "1.4.0"
    val paperdb = "2.6"
    val moshi = "1.8.0"
    val lifecycle = "2.1.0-alpha01"
    val leakCanary = "2.0-alpha-2"
    val crashlytics = "2.10.1"
    val koin = "2.0.0-beta-1"

    val playCore = "1.6.1"

    val junit = "4.12"
    val assertjCore = "3.12.2"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "3.0.0"
    val coroutinesTest = "1.3.1"

}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

    val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val paperdb = "io.paperdb:paperdb:${Versions.paperdb}"
    val rxpaper = "com.github.pakoito:RxPaper2:${Versions.rxpaper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val playCore = "com.google.android.play:core:${Versions.playCore}"

}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val analytics = "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

}
