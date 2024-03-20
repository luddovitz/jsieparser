plugins {
    id("java")
    `maven-publish`
}

group = "com.ludwigreis"
version = "1.0-SNAPSHOT"

publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = "com.ludwigreis"
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
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}