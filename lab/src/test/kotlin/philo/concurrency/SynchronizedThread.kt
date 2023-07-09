package philo.concurrency

class SynchronizedThread(private val lock: Any, private val shareMemory: ShareMemory, private val opNum: Int) : Thread() {

    override fun run() {
        add()
    }

    private fun add() {
        repeat(opNum) {
            synchronized(lock) {
                shareMemory.count++
            }
        }
    }
}
