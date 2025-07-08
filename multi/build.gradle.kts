import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.2.0"
    id("jacoco")
    id("com.jfrog.artifactory") version "5.2.5"
    id("org.sonarqube") version "5.1.0.4882"
}

val artifactoryUser: String by project
val artifactoryPassword: String by project
val artifactoryUrl: String by project


/**
 *
 * Configuration for all projects, including the main one.
 * You can check those using:
 *
 *    gradle projects
 *
 * Root project 'multi'
 * +--- Project ':app'
 * \--- Project ':core'
 *
 **/
allprojects {
    // Use gradle projects, to see the structure
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }
    
    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

/**
 *
 * Configuration that will apply for all the subprojects
 * Usually the ones with codes
 *
 **/
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "jacoco")
    apply(plugin = "com.jfrog.artifactory")
    apply(plugin = "maven-publish")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    sonarqube {
        properties {
            property("sonar.java.coveragePlugin", "jacoco")
            property("sonar.coverage.jacoco.xmlReportPaths", "../build/reports/jacoco/test/jacocoTestReport.xml")
            property("sonar.exclusions", "**/config/**")
        }
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
        }
    }

    tasks.jacocoTestReport {
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            events("skipped", "failed")
        }
        maxParallelForks = 1
    }

    configure<org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention> {
        publish {
            contextUrl = artifactoryUrl
            repository {
                repoKey = "gradle"
                username = artifactoryUser
                password = artifactoryPassword
            }
            defaults {
                publications("mavenJava")
            }
        }
    }
}

/**
 *
 * Generate coverage for all projects using:
 *
 *   gradle clean build test codeCoverageReport
 *
 **/
tasks.register<JacocoReport>("codeCoverageReport") {

    executionData(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))
    subprojects.forEach{ sourceSets(it.sourceSets.main.get()) }

    reports {
        xml.required.set(true)
        xml.outputLocation.set(File("${layout.buildDirectory.get().asFile}/reports/jacoco/report.xml"))
        html.required.set(true)
    }

    dependsOn(allprojects.map { it.tasks.named<Test>("test") })
}

tasks.withType<JacocoReport> {
    afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it).apply {
                exclude("**/config/**") // Remove class / folder from coverage
            }
        }))
    }
}


/**
 * The answer (taken from gradle.properties)
 * Test it using:
 *
 *    gradle compute
 *
 */
val answer: String? by project

tasks.register("compute") {
    doLast { println("The answer is $answer.") }
    //doLast { println("${project.findProperty("answer")}") }
}