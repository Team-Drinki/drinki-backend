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

group = "io.github.ssudrinki"
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
	implementation(libs.kotlin.reflect)
	implementation(libs.kotlinx.serialization.json)
	implementation(libs.spring.boot.starter.web)
	testImplementation(libs.spring.boot.starter.test)
	developmentOnly(libs.spring.boot.devtools)
	testImplementation(libs.kotlin.test.junit5)
	testRuntimeOnly(libs.junit.platform.launcher)

	implementation(libs.exposed.spring.boot.starter)
	implementation(libs.exposed.kotlin.datetime)
	implementation(libs.exposed.json)
	implementation(libs.exposed.migration)
	implementation(libs.exposed.jdbc)
	implementation(libs.exposed.core)      // 추가
	implementation(libs.exposed.dao)       // 추가
	implementation(libs.exposed.jdbc)      // 추가
//	implementation(libs.jackson.module.kotlin)
	runtimeOnly(libs.postgresql)
	developmentOnly(libs.flyway.database.postgresql)
	implementation("org.springframework.boot:spring-boot-starter-validation")

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
