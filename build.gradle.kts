// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
        // alttaki eklenti (com.google.android.libraries.mapsplatform.secrets-gradle-plugin) eklentisinin
        // uygulanıp uygulanmadığını kontrol eder. ( from alperen )
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}