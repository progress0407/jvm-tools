package philo.io.performance

import io.kotest.core.spec.style.FunSpec

/**
 * 유의미한 차이를 알아내지 못했다
 */
class PerformanceFileIOManagerTest : FunSpec({

    val nonBufferedFileIOManager = TestNonBufferedFileIOManager()
    val bufferedFileIOManager = TestBufferedFileIOManager()

    val contentToSave = contentToSave()

    test("[성능] 버퍼 읽기와 쓰기를 비교한다 - Non-Buffered") {

        for (i in 1..100) {
            val fileName = "test$i.txt"
            nonBufferedFileIOManager.save(contentToSave, "temp", "performance", "non-buffered", fileName)
//            nonBufferedFileIOManager.load("temp", "performance", "non-buffered", fileName)
        }
    }

    test("[성능] 버퍼 읽기와 쓰기를 비교한다 - Buffered") {

        for (i in 1..100) {
            val fileName = "test$i.txt"
            bufferedFileIOManager.save(contentToSave, "temp", "performance", "buffered", fileName)
//            bufferedFileIOManager.load("temp", "performance", "buffered", fileName)
        }
    }
})

private fun contentToSave(): String {
    val sb = StringBuilder()
    for (i in 1..100_000) {
        sb.append("Hello World abcdedfghijklmnopqrstuvwxyz0123456789$i\n")
    }

    val contentToSave = sb.toString()
    return contentToSave
}