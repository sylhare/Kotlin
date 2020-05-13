plugins {
    kotlin("jvm")
}

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(sourceSets.main.get().output)
    include("*")
}

configurations {
    create("mock") // Based on artifact
}

tasks.register<Jar>("testArchive") {
    archiveBaseName.set("core-test")
    from(sourceSets.test.get().output)
    include("examples/mocks/**")
}

artifacts {
    add("mock", tasks["testArchive"]) // Add a configuration to a task
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "examples"
            artifactId = "core"
            version = "1.0"
            artifact(sourcesJar)
            artifact("$buildDir/libs/core.jar")
        }
    }
}