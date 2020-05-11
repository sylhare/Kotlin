import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    id("jacoco")
    id("com.jfrog.artifactory") version "4.13.0"
    id("org.sonarqube") version "2.6.2"
}

val artifactoryUser: String by project
val artifactoryPassword: String by project
val artifactoryUrl: String by project

allprojects {
    // Use gradle projects, to see the structure
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        jcenter()
        mavenCentral()
    }
}

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
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
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

    artifactory {
        setContextUrl(artifactoryUrl)
        publish(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
            repository(delegateClosureOf<groovy.lang.GroovyObject> {
                setProperty("repoKey", "gradle")
                setProperty("username", artifactoryUser)
                setProperty("password", artifactoryPassword)
                setProperty("maven", true)
            })
            defaults(delegateClosureOf<groovy.lang.GroovyObject> {
                invokeMethod("publications", "mavenJava")
                setProperty("publishArtifacts", true)
            })
        })
        resolve(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.ResolverConfig> {
            setProperty("repoKey", "gradle")
            setProperty("username", artifactoryUser)
            setProperty("password", artifactoryPassword)
            setProperty("maven", true)
        })
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