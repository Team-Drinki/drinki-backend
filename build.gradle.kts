import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.flyway)
}

buildscript {
	dependencies {
		classpath(libs.flyway.database.postgresql)
		classpath(libs.postgresql)
	}
}

group = "io.github.teamdrinki"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.serialization.json)
    
    // Testing
    testImplementation(libs.kotlin.test.junit5)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.spring.boot.starter.test)
    
    // Spring Boot
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.oauth2.client)
    implementation("org.springframework.boot:spring-boot-starter-validation")
    developmentOnly(libs.spring.boot.devtools)
    
    // Exposed ORM
    implementation(libs.exposed.spring.boot.starter)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.exposed.json)
    implementation(libs.exposed.migration)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    
    // Database
    runtimeOnly(libs.postgresql)
    implementation(libs.hikari)
    
    // Flyway
    implementation(libs.flyway.database.postgresql)
    // implementation(libs.flyway.core) // 주석 처리됨
    
    // Others
    implementation(libs.jackson.module.kotlin)
    implementation(libs.auth0.jwt)
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

springBoot {
	mainClass.set("io.github.teamdrinki.drinkibackend.DrinkibackendApplicationKt")
}

flyway {
	url = System.getenv("DB_URL")
	user = System.getenv("DB_USER")
	password = System.getenv("DB_PASSWORD")
	locations = arrayOf("filesystem:src/main/resources/db/migration")
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks.named<BootJar>("bootJar") {
	archiveFileName = "${rootProject.name}.jar"
}

tasks.register<JavaExec>("generateMigration") {
	group = "database"
	description = "Generate Migration SQL"
	classpath = sourceSets["main"].runtimeClasspath
	mainClass.set("io.github.teamdrinki.drinkibackend.devops.MigrationScriptGeneratorKt")
}
