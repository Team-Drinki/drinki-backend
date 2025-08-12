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
