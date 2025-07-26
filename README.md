# DBConnector

## 使い方

```kotlin
import mc.tsukimiya.dbconnector.Database

Database.transaction {
    val connection = Database.getConnection() // java.sql.Connection
    // ~~
}
```
