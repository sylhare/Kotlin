plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":core"))
    testImplementation(project(":core", "mock"))
}

version = "1.0.0"