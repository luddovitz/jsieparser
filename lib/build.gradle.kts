plugins {
    `java-library`
    `maven-publish`
}

group = "luddovitz"
version = "1.0"

publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = "luddovitz"
            artifactId = "jsieparser"
            version = "1.0"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/luddovitz/jsieparser")
        credentials {
            username = project.findProperty("USERNAME") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("TOKEN") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}