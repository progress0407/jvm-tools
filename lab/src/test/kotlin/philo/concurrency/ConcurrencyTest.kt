package philo.concurrency

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ConcurrencyTest : FunSpec({

    test("어떠한 동시성 처리도 하지 않은 경우 변경 작업이 손실된다") {

        val shareMemory = ShareMemory()
        val threads = `동시성 처리되지 않은 ThreadList 생성`(shareMemory)

        // when
        threads.`실행 및 대기`()

        // then
        shareMemory.count shouldNotBe 40_000
    }

    test("synchronized 블록으로 동시성 처리를 하면 변경 작업이 손실되지 않는다") {
        val shareMemory = ShareMemory()
        val threads = `synchronized 블록으로 처리한 ThreadList 생성`(shareMemory, 4, 10_000)

        // when
        threads.`실행 및 대기`()

        // then
        shareMemory.count shouldBe 40_000
    }

    test("Atomic 연산을 통해서도 Thread Safe하게 실행할 수 있다") {
        val shareMemory = AtomicShareMemory()
        val threads = `Atomic 연산하는 ThreadList 생성`(shareMemory, 4, 10_000)

        // when
        threads.`실행 및 대기`()

        // then
        shareMemory.count.get() shouldBe 40_000
    }

    test("synchronized 연산 보다 Atomic 연산이 빠르다") {
        val shareMemory1 = ShareMemory()
        val shareMemory2 = AtomicShareMemory()
        val `쓰레드 수` = 8
        val `각 쓰레드 연산 횟수` = 20_000_000
        val synchronizedThreads = `synchronized 블록으로 처리한 ThreadList 생성`(shareMemory1, `쓰레드 수`, `각 쓰레드 연산 횟수`)
        val atomicThreads = `Atomic 연산하는 ThreadList 생성`(shareMemory2, `쓰레드 수`, `각 쓰레드 연산 횟수`)

        // when
        val time1 = System.currentTimeMillis()
        synchronizedThreads.`실행 및 대기`()
        val time2 = System.currentTimeMillis()
        atomicThreads.`실행 및 대기`()
        val time3 = System.currentTimeMillis()

        val `synchronized 연산 시간` = (time2 - time1)
        val `Atomic 연산 시간` = (time3 - time2)

        // logging
        println("`synchronized 연산 시간` = ${`synchronized 연산 시간`}")
        println("`Atomic 연산 시간` = ${`Atomic 연산 시간`}")

        // then
        shareMemory1.count shouldBe `쓰레드 수` * `각 쓰레드 연산 횟수`
        shareMemory2.count.get() shouldBe `쓰레드 수` * `각 쓰레드 연산 횟수`
        `Atomic 연산 시간` shouldBeLessThan `synchronized 연산 시간`
    }
})

private fun ArrayList<Thread>.`실행 및 대기`() {
    this.forEach { it.start() }
    this.forEach { it.join() }
}

fun `Atomic 연산하는 ThreadList 생성`(shareMemory: AtomicShareMemory, threadNum: Int, opNum: Int) : ArrayList<Thread> {

    val threads = ArrayList<Thread>()

    repeat(threadNum) {
        val thread = Thread {
            repeat(opNum) {
                shareMemory.count.getAndIncrement()
            }
        }
        threads.add(thread)
    }

    return threads
}

fun `synchronized 블록으로 처리한 ThreadList 생성`(shareMemory: ShareMemory, threadNum: Int, opNum: Int): ArrayList<Thread> {
    val lock = Object() // Lock 객체 생성
    val threads = ArrayList<Thread>()

    repeat(threadNum) {
        val thread = SynchronizedThread(lock, shareMemory, opNum)
        threads.add(thread)
    }

    return threads
}


private fun `동시성 처리되지 않은 ThreadList 생성`(shareMemory: ShareMemory): ArrayList<Thread> {

    val threads = ArrayList<Thread>()

    repeat(4) {
        val thread = Thread {
            repeat(10_000) {
                shareMemory.count++
            }
        }
        threads.add(thread)
    }
    return threads
}