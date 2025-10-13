# Drinki

## DB Migration

We use flyway & exposed MigrationUtils for configure Database migration.  
Simply put, flyway is a DB configuration management tool,
and exposed MigrationUtils is a tool that generates migration SQL.

To connect with database, you should set following environment variables.  
flyway, exposed migration utils, spring boot are set to use these environment variables.

- `DB_URL`
- `DB_USER`
- `DB_PASSWORD`
- `DB_NAME`

### Apply existing migrations

```
./gradlew flywayMigrate
```

### Generate new migration

The command below creates new migration file named `V${yyyyMMddHHmmss}__new_migration.sql`.  
Before committing or applying migration, you must appropriately change the description (the last part of its name) of the migration file.

```
./gradlew generateMigration
```

## Set Local Development Environment

```
docker compose -f docker-compose.local.yml up
```
The above environment variables are also required when running this command.  
you will need to apply migrations to get the server running properly.

---

# For Frontend Devs

최초에 로컬에서 서버 돌리는 방법

## 1. 환경변수 설정

아래 환경변수를 설정
- `DB_URL`
  - ex) `jdbc:postgresql://localhost:5432/drinki`
- `DB_USER`
  - ex) `drinki_backend`
- `DB_PASSWORD`
  - ex) `1234567890`
- `DB_NAME`
  - ex) `drinki`

## 2. docker container 생성

아래 명령어를 실행해 docker container 생성

```
docker compose -f docker-compose.local.yml up
```

## 3. migration 적용

2단계 명령어 실행하면 server 하나, DB 하나 이렇게 container가 2개 있을 것이다.  
server container 정지하고 아래 명령어 실행 후 다시 실행

```
./gradlew flywayMigrate
```



