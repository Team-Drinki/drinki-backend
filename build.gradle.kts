plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
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
	implementation(libs.spring.boot.starter.web)
	testImplementation(libs.spring.boot.starter.test)
	developmentOnly(libs.spring.boot.devtools)
	testImplementation(libs.kotlin.test.junit5)
	testRuntimeOnly(libs.junit.platform.launcher)
	implementation(libs.exposed.spring.boot.starter)
	implementation(libs.exposed.kotlin.datetime)
	implementation(libs.jackson.module.kotlin)
	runtimeOnly(libs.h2)
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

tasks.withType<Test> {
	useJUnitPlatform()
}
