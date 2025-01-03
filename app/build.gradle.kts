import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.firebase)
}



android {
    namespace = "org.cartify.ecommerce"
    compileSdk = 34
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "org.cartify.ecommerce"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val localproperties=Properties().apply {
            load(File("local.properties").inputStream())
        }
        val baseUrl=localproperties.getProperty("BASE_URL")?:""

        buildConfigField("String","BASE_URL",baseUrl)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    flavorDimensions+= listOf("paid_status")
    productFlavors{
        create("free"){
            applicationIdSuffix = ".free"
            dimension = "paid_status"
        }
        create("paid"){
            applicationIdSuffix = ".paid"
            dimension = "paid_status"
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
        compose = true
        buildConfig= true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    //For ViewModel Scope
    implementation(libs.lifecycle.viewmodel.ktx)

    // Dependency Injection with Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    //Dependency For OkHttp logging interceptor
    implementation(libs.logging.interceptor)

    // Retrofit for network calls
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    // Room database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Coroutines for background operation
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.kotlinx.coroutines.test)

    //mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline) // For mocking final classes
    testImplementation(libs.mockito.kotlin)

    //firebase
    implementation(platform(libs.firebase.core))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.installations)

    //coil
//    implementation(platform(libs.coil.compose))
//    implementation(platform(libs.coil.svg))
    implementation( "io.coil-kt:coil-compose:2.4.0")
    //coil svg decoder
    implementation ("io.coil-kt:coil-svg:2.4.0")
}