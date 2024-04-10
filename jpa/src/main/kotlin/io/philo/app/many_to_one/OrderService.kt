package io.philo.app.many_to_one

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
    private val em: EntityManager
) {

    fun order_whole(): Map<String, Long?> {

        val order = Order("order")
        val orderHistory = OrderHistory("orderHistory")
        orderHistory.order = order
        orderHistoryRepository.save(orderHistory)
        val orderId = order.id
        val orderHistoryId = orderHistory.id

        em.flush()
        em.clear()

        val foundOrderHistory = orderHistoryRepository.findById(orderHistoryId!!).get()
        val foundOrder = foundOrderHistory.order!!
        foundOrder.name = "newOrder"

        em.flush()
        em.clear()

        orderHistoryRepository.save(foundOrderHistory)

        return mapOf(Pair("orderId", orderId), Pair("orderHistoryId", orderHistoryId))
    }

    fun order1(): Map<String, Long?> {

        val order = Order("order")
        val orderHistory = OrderHistory("orderHistory")
        orderHistory.order = order
        orderHistoryRepository.save(orderHistory)
        val orderId = order.id
        val orderHistoryId = orderHistory.id

        em.flush()
        em.clear()

        return mapOf(Pair("orderId", orderId), Pair("orderHistoryId", orderHistoryId))
    }

    fun order2(orderHistoryId: Long): String {
        val foundOrderHistory = orderHistoryRepository.findById(orderHistoryId!!).get()
        val foundOrder = foundOrderHistory.order!!
        foundOrder.name = "newOrder"

//        flushAndClear()

        orderHistoryRepository.save(foundOrderHistory)

        flushAndClear()

        return "ok"
    }

    private fun flushAndClear() {
        em.flush(); em.clear()
    }
}
