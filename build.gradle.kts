import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    // Java support
    id("java")
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij.platform") version "2.0.1"

    kotlin("jvm") version "2.0.20"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

// Configure project's dependencies
repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        jetbrainsRuntime()
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "comma"
        name = "Raku Comma"
        version = "1.0.0"
        description = "It's an awesome plugin!"
    }

    pluginVerification {
        ides {
            ide(IntelliJPlatformType.IntellijIdeaCommunity, "2024.2")
        }
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2024.2")

        bundledPlugin("com.intellij.java")

        jetbrainsRuntime("21.0.3", "osx", "aarch64")

        pluginVerifier()
        zipSigner()
        instrumentationTools()
    }
    implementation(kotlin("stdlib-jdk8"))
    implementation(files("libs/xchart-3.8.0.jar"))
    implementation(files("libs/moarvmremote.jar"))
    implementation("io.airlift:aircompressor:2.0.2")
    implementation("org.json:json:20240303")
    implementation("org.tap4j:tap4j:4.4.2")
}

tasks {
    instrumentCode {
        formsDirs = files("src/main/org/raku/project/projectWizard/components")
    }
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
//intellij {
//    pluginName.set(properties("pluginName"))
//    version.set(properties("platformVersion"))
//    type.set(properties("platformType"))
//
//    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
//    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
//}
//
//tasks {
//    // Set the JVM compatibility versions
//    properties("javaVersion").let {
//        withType<JavaCompile> {
//            sourceCompatibility = it
//            targetCompatibility = it
//        }
//    }
//
//    wrapper {
//        gradleVersion = properties("gradleVersion")
//    }
//
//    patchPluginXml {
//        version.set(properties("pluginVersion"))
//        sinceBuild.set(properties("pluginSinceBuild"))
//        untilBuild.set(properties("pluginUntilBuild"))
//    }
//}