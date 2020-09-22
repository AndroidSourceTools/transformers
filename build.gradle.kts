// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:4.1.0-rc02")
    classpath("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.5")
    classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Libraries.kotlinVersion}")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
  tasks.withType(Javadoc::class) {
    enabled = false
  }
}
