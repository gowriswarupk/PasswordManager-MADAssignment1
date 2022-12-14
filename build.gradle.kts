import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.openjfx.javafxplugin") version "0.0.8"
    application
}

group = "org.setu.passwordmanager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:2.0.1")
    implementation("io.github.microutils:kotlin-logging:3.0.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("no.tornado:tornadofx-controlsfx:0.1.1")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}