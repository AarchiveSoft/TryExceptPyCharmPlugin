plugins {
    id("org.jetbrains.intellij") version "1.17.4"
    java
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
}

group = "com.softwaare"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://www.jetbrains.com/intellij-repository/releases")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // implementation("com.jetbrains.intellij.python:python-sdk:241.18034.82")
    implementation(files("libs/python-sdk-241.18034.82.jar"))
    implementation(files("libs/python-psi-241.18034.82.jar"))
    implementation(files("libs/python-community-impl-241.18034.82.jar"))
    implementation(files("libs/python-ast-241.18034.82.jar"))
    implementation(files("libs/python-ast-241.18034.82-sources.jar"))
    implementation(files("libs/python-parser-241.18034.82.jar"))
    implementation(files("libs/python-syntax-core-241.18034.82.jar"))
    // No direct dependency on com.jetbrains.python:python-psi-api
}

intellij {
    version.set("2024.1.4")
    type.set("PC") // Use PyCharm Community Edition
    plugins.set(listOf())
    downloadSources.set(true)
}

tasks {
    patchPluginXml {
        changeNotes.set("Initial plugin version.")
    }

    compileJava {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}

tasks.processResources {
    from("src/main/resources") {
        include("**/*")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.buildPlugin {
    from("src/main/resources") {
        into("resources")
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
}