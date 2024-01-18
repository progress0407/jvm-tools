package philo

import io.philo.io.FileIOManager

class SqlGenerator

fun main() {

    val contentGenerator = SqlContentGenerator()
    val fileIOManager = FileIOManager()

    val fileContent = contentGenerator.generateVenueSeats(20001, 22000, 10, 20)
    val sqlFileName = "sample.sql"

    fileIOManager.save(fileContent, "sql-generator", "sql", sqlFileName)
}

