package philo

import mu.KLogger
import mu.KotlinLogging
import io.philo.log.infoGreen

@Suppress("SqlResolve", "SqlNoDataSourceInspection")
class SqlContentGenerator {

    private val log: KLogger = KotlinLogging.logger {}

    fun generateSampleSql(): String {

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

        return """
        insert into venue_seat (venue_id, id, seat_number, seat_type, created_at, updated_at 
        values ${data.joinToString(",\n")};
        """.trimIndent()
    }

    fun generateVenueSeats(venueId: Int, startSeatPkNumber: Int, vipSeatNumber: Int, generalSeatNumber: Int): String {

        val record = mutableListOf<String>()
        val endSeatNumber = vipSeatNumber + generalSeatNumber

        for (i in 1..vipSeatNumber) {
            val seatId = calcSeatId(startSeatPkNumber, i)
            record.add("($venueId, $seatId, 'A${i}', '${"VIP"}', now(), now())")
        }

        for (i in vipSeatNumber + 1..endSeatNumber) {
            val seatId = calcSeatId(startSeatPkNumber, i)
            record.add("($venueId, $seatId, 'A${i}', '${"GENERAL"}', now(), now())")
        }

        val values = record.joinToString(",\n")

        val sql = """
            insert into venue_seat (venue_id, id, seat_number, seat_type, created_at, updated_at)
            values $values;
            """.trim()

        log.infoGreen { "SQL Generated Successfully !!" }

        return sql
    }

    private fun calcSeatId(startSeatNumber: Int, currentIndex: Int) = startSeatNumber - 1 + currentIndex
}