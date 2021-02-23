// Use Gradle 4.8, if you have a newer version, use ./gradlew
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "hello"
    version = "1.0"
    repositories {
        jcenter()
    }
}

plugins {
    application
    kotlin("jvm") version "1.3.21"
    jacoco
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

// To work with the `application` plugin of gradle -> gradle run
application {
    mainClassName = "hello.MainKt"
}

// To add manifest in jar, but still get `Exception in thread "main" java.lang.NoClassDefFoundError:
// kotlin/jvm/internal/Intrinsics at hello.MainKt.main(Main.kt)
val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "hello.MainKt"
    }
}

// For fat-jar in build/libs -> java -jar hello-kotlin-fat.jar
val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "hello.MainKt"
    }
    from(
        configurations["runtimeClasspath"].map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    with(tasks["jar"] as CopySpec)
}

// For the fat jar task to work with gradle build
tasks {
    "build" {
        dependsOn(fatJar)
    }
}

// For Jacoco test report
tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.destination = file("${buildDir}/reports/jacoco")
    }
}

// Compile Kotlin on JVM 1.8
tasks.withType<KotlinCompile>{
    kotlinOptions {
        jvmTarget = "1.8"
        //freeCompilerArgs = listOf("-Xjvm-default=compatibility")
    }
}