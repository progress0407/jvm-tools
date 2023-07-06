import java.io.File
import java.nio.file.Paths

class FileManager : IOManager() {

    fun saveFile() {

        val sqlFileName = "sample.sql"
        val fullPath = Paths.get("sql-generator", "sql", sqlFileName)
        createPathIfNotExist(fullPath)

        val venueId = 20002
        val seatTypes = listOf("VIP", "Regular", "Economy")
        val seatNumbers = listOf("A1", "A2", "A3")
        val data = mutableListOf<String>()

        for (i in seatNumbers.indices) {
            val seatId = 22000 + (i + 1)
            val seatNumber = seatNumbers[i]
            val seatType = seatTypes[i]
            data.add("($venueId, $seatId, '$seatNumber', '$seatType', now(), now())")
        }

        val sqlString = """
        insert into venue_seat (venue_id, id, seat_number, seat_type, created_at, updated_at)
        values ${data.joinToString(",\n")};
    """.trimIndent()

        File(fullPath.toUri()).writeText(sqlString)
    }
}