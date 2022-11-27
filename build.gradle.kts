import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "de.skyslycer"
version = "3.0.0"

val shadePattern = "$group.shade"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://oss.sonatype.org/content/groups/public")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")
    implementation("org.bstats:bstats-bukkit:3.0.0")
    implementation("net.kyori:adventure-text-minimessage:4.11.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.11.0")
    implementation("com.tchristofferson:ConfigUpdater:2.0-SNAPSHOT")
}

tasks {
    shadowJar {
        relocate("org.bstats", "$shadePattern.bstats")
        relocate("net.kyori", "$shadePattern.kyori")
        relocate("com.tchristofferson.configupdater", "$shadePattern.configupdater")
        minimize()
        archiveFileName.set("BookRules.jar")
    }

    build {
        dependsOn(shadowJar)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}