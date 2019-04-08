plugins {
    application
    kotlin("jvm") version "1.3.21"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.rabbitmq:amqp-client:2.8.2")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.slf4j:slf4j-simple:1.6.6")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}

application {
    mainClassName = "com.nrkei.microservices.AppKt"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.3.1"
}