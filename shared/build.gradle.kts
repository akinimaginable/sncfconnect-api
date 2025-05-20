plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "org.etrange"
version = "unspecified"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}
