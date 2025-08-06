package io.github.teamdrinki.drinkibackend.devops

import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.migration.MigrationUtils
import org.jetbrains.exposed.v1.spring.boot.discoverExposedTables
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AssignableTypeFilter
import java.io.File
import java.time.ZonedDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun main() {
    // DB 연결
    val url = System.getenv("DB_URL")
    val user = System.getenv("DB_USER")
    val password = System.getenv("DB_PASSWORD")

    val db = Database.connect(url = url, driver = "org.postgresql.Driver", user = user, password = password)

    // 1. 모든 테이블 리스트 모으기
    val allTables = findTables()

    // 2. 모든 테이블의 마이그레이션 SQL 생성
    val migrationSql: List<String> = transaction(db) {
        MigrationUtils.statementsRequiredForDatabaseMigration(*allTables.toTypedArray())
    }

    // 3. 단일 파일로 작성
    // TODO: format sql
    val utcNow = ZonedDateTime.now(ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    val formattedUtc = utcNow.format(formatter)
    val migrationFile = File("src/main/resources/db/migration/V${formattedUtc}__new_migration.sql")
    migrationFile.parentFile.mkdirs()
    migrationFile.writeText(migrationSql.joinToString(separator = ";\n") + ";")

    println("migration script generation complete: ${migrationFile.absolutePath}")
}

fun findTables(): List<Table> {
    // TODO: Check if it can be done without spring framework
    val provider = ClassPathScanningCandidateComponentProvider(false)
    provider.addIncludeFilter(AssignableTypeFilter(Table::class.java))
    val components = provider.findCandidateComponents("io.github.teamdrinki.drinkibackend.schema")
    return components.map { Class.forName(it.beanClassName).kotlin.objectInstance as Table }
}
