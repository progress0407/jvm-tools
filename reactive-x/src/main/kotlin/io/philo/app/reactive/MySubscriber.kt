package io.philo.app.reactive

import java.util.concurrent.Flow.Subscriber
import java.util.concurrent.Flow.Subscription

class MySubscriber: Subscriber<Int> {

    private lateinit var subscription: Subscription

    override fun onSubscribe(subscription: Subscription) {
        Thread.sleep(500)
        println("구독자($this): 구독 정보 잘 받았어 !")
        this.subscription = subscription
        Thread.sleep(200)
        println("구독자($this): 나 신문 n개씩 줘!")
        subscription.request(1)
    }

    override fun onNext(item: Int) {
        println("구독자($this): 구독 데이터 전달: $item")
        subscription.request(1)
    }

    override fun onComplete() {
        println("구독자($this): 구독 완료")
    }

    override fun onError(throwable: Throwable?) {
        println("구독자($this): 구독 처리 중 에러 발생")
    }
}