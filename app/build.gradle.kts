plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.hsj135"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.hsj135"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.legacy.support.v4)
    implementation(libs.recyclerview)
    implementation(libs.viewpager2)
    implementation(libs.cardview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // 第一章依赖
    implementation("com.github.AppIntro:AppIntro:6.3.1")
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("androidx.activity:activity-compose:1.7.2")
    // 第一章依赖
    implementation("androidx.appcompat:appcompat:1.0.0") //必须 1.0.0 以上
    implementation("io.github.scwang90:refresh-layout-kernel:3.0.0-alpha") //核心必须依赖
    implementation("io.github.scwang90:refresh-header-classics:3.0.0-alpha") //经典刷新头
//    implementation("io.github.scwang90:refresh-header-radar:3.0.0-alpha") //雷达刷新头
//    implementation("io.github.scwang90:refresh-header-falsify:3.0.0-alpha") //虚拟刷新头
    implementation("io.github.scwang90:refresh-header-material:3.0.0-alpha") //谷歌刷新头
//    implementation("io.github.scwang90:refresh-header-two-level:3.0.0-alpha") //二级刷新头
    implementation("io.github.scwang90:refresh-footer-ball:3.0.0-alpha") //球脉冲加载
    implementation("io.github.scwang90:refresh-footer-classics:3.0.0-alpha") //经典加载
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
//    implementation("com.github.leonardoxh:retrofit2-livedata-adapter:1.1.2")

//    第三章依赖
//    implementation("io.github.cymchad:BaseRecyclerViewAdapterHelper4:4.3.2")
    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7")
    implementation("io.github.youth5201314:banner:2.2.3")

    // 第四章依赖
    implementation("io.github.justson:agentweb-core:v5.1.1-androidx")

    // 第五章依赖
//    implementation("com.nightonke:boommenu:2.1.1")
    implementation("com.github.Nightonke:BoomMenu:6ec29918d5")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // 第六章依赖
    implementation("io.github.carguo:gsyvideoplayer:11.3.0")

    // 第七章依赖
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("io.github.bmob:android-sdk:4.1.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.9")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.squareup.okhttp3:okhttp:4.8.1")
    implementation("com.squareup.okio:okio:2.2.2")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("androidx.multidex:multidex:2.0.1")

    // 第八章依赖
    implementation(files("libs/BaiduLBS_Android.jar"))



}