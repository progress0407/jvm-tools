package philo.datetime

import java.time.*

class DateTimeMain {
}

fun main() {

    val 한국_타임_존 = ZoneId.of("Asia/Seoul")
    val 영국_타임_존 = ZoneId.of("Europe/London")

    val 한국_일시 = ZonedDateTime.of(LocalDateTime.of(2023, 1, 31, 3, 0), 한국_타임_존)
    val 영국_일시 = ZonedDateTime.of(LocalDateTime.of(2023, 1, 30, 18, 0), 영국_타임_존)

    println("한국_타임_존 = ${한국_타임_존}")
    println("영국_타임_존 = ${영국_타임_존}")
    println("한국_일시 = ${한국_일시}")
    println("영국_일시 = ${영국_일시}")

    println(LocalDateTime.now(한국_타임_존))
    println(LocalDateTime.now(영국_타임_존))
    val 절삭 = LocalDate.now(한국_타임_존).atStartOfDay()
    println("절삭 = ${절삭}")
}