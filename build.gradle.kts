plugins {
    kotlin("jvm") version "2.4.0"
    id("com.gradleup.shadow") version "9.3.0+"
    `maven-publish`
}

group = "mc.tsukimiya"
version = "1.1.2"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    compileOnly("com.zaxxer:HikariCP:7.0.2")
    compileOnly("com.mysql:mysql-connector-j:9.3.0")
    compileOnly("org.xerial:sqlite-jdbc:3.50.3.0")
}

kotlin {
    jvmToolchain(25)
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tsukimiyaproject/DBConnector")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            artifactId = "db-connector"
            from(components["java"])
        }
    }
}
