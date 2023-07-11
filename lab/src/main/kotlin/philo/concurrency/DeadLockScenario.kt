package philo.concurrency

class DeadLockScenario {
}

fun main() {
    val lock1 = Object()
    val lock2 = Object()

    val th_1 = createThread1(lock1, lock2)
    val th_2 = createThread2(lock1, lock2)


    th_1.start()
    th_2.start()

    Thread.sleep(1_000)

    println("Thread DeadLock Scenario SuccessFully!")

    System.exit(0)
}

private fun createThread2(lock1: Any, lock2: Any) =
    Thread {
        synchronized(lock2) {
            println("Thread 2: Holding lock 2...")
            Thread.sleep(10)
            println("Thread 2: Waiting for lock 1...")
            synchronized(lock1) {
                println("Thread 1: Holding lock 1 & 2...")
            }
        }
    }

private fun createThread1(lock1: Any, lock2: Any) =
    Thread {
        synchronized(lock1) {
            println("Thread 1: Holding lock 1...")
            Thread.sleep(10)
            println("Thread 1: Waiting for lock 2...")
            synchronized(lock2) {
                println("Thread 1: Holding lock 1 & 2...")
            }
        }
    }

