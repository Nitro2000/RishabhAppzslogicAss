plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.bitspan.rishabhappzslogicass"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bitspan.rishabhappzslogicass"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // Navigation
    val navigationVersion = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")


    // Retrofit
    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")




    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.16.0") {
        exclude("com.android.support")
    }
    kapt ("com.github.bumptech.glide:compiler:4.16.0")


//     Hilt dependency
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-compiler:2.50")



    implementation("com.facebook.shimmer:shimmer:0.5.0")

    //DatStore
    implementation ("androidx.datastore:datastore-preferences-core:1.0.0")
    implementation ("androidx.datastore:datastore-preferences:1.0.0")
    implementation ("androidx.datastore:datastore:1.0.0")


    val lifecycle_version = "2.7.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


}