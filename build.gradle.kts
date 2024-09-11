plugins {
    id("org.jetbrains.intellij") version "1.17.4"
    java
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
}

group = "com.softwaare"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.jetbrains.python:python-psi-api:221.5080.212")
}

intellij {
    version.set("2022.2.1")
    type.set("PC") // Use PyCharm Community Edition
    plugins.set(listOf("Pythonid:222.3345.118")) // Specific Python plugin version compatible with PyCharm
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

    processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/java")
        resources.srcDirs("src/main/resources")
    }
}