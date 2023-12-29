package io.philo.app.reactive

import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription

class MySubscription(private val subscriber: Subscriber<in Int>,
                     private val dataList: Iterator<Int>)
    :Subscription {

        constructor(subscriber: Subscriber<in Int>, dataList: Iterable<Int>) :
                this(subscriber, dataList.iterator())

        init {
            Thread.sleep(500)
            println("구독정보($this): 생성 완료")
        }

    /**
     * (백프레셔), 요청 갯수
     */
    override fun request(n: Long) {

        var cnt = n

        while (cnt > 0) {
            if (dataList.hasNext()) {
                subscriber.onNext(dataList.next())
            } else {
                subscriber.onComplete()
                break
            }
            cnt--
        }
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
}