package io.philo.app.reactive

import java.util.concurrent.Flow.Publisher
import java.util.concurrent.Flow.Subscriber

class MyPublisher : Publisher<Int> {

    val dataList: Iterable<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    override fun subscribe(subscriber: Subscriber<in Int>) {
        println("구독자($subscriber): hey, $this! 구독을 시작할게 ")
        println("발행자($this): ok, 구독 정보 만들어 줄게")

        val subscription = MySubscription(subscriber, dataList)
        println("발행자($this): 구독 정보 생성했어! 받아랏!")

        subscriber.onSubscribe(subscription)
    }
}