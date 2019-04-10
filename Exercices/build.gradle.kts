import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.1")

    testRuntime("org.junit.platform:junit-platform-launcher:1.4.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.1")
    testRuntime("org.junit.vintage:junit-vintage-engine:5.4.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging { events("passed", "skipped", "failed") }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.3.1"
}
