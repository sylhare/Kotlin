import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    group = "samples"
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
    compile(kotlin("stdlib"))
    testCompile("junit:junit:4.12")
}

// To work with the `application` plugin of gradle -> gradle run
application {
    mainClassName = "samples.MainKt"
}

// To add manifest in jar, but still get `Exception in thread "main" java.lang.NoClassDefFoundError:
// kotlin/jvm/internal/Intrinsics at samples.MainKt.main(Main.kt)
val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "samples.MainKt"
    }
}

// For fat-jar in build/libs -> java -jar hello-kotlin-fat.jar
val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "samples.MainKt"
    }
    from(
        configurations.runtime.map {
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