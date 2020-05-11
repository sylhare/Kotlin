plugins {
    kotlin("jvm")
}

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    from(sourceSets.main.get().allSource)
    from(sourceSets.test.get().allSource) // because we want source test too
}

configurations {
    create("mock")
}

tasks.register<Jar>("testArchive") {
    archiveBaseName.set("core-test")
    from(project.the<SourceSetContainer>()["test"].output)
    include("examples/mocks/**")
}

artifacts {
    add("mock", tasks["testArchive"])
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