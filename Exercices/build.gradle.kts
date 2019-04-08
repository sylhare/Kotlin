plugins {
    application
    kotlin("jvm") version "1.3.21"
}

repositories {
    jcenter()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

tasks.withType<Wrapper> {
    gradleVersion = "5.3.1"
}