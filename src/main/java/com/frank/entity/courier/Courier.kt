package com.frank.entity.courier

import com.frank.entity.order.Order

import java.time.LocalDate
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedDeque

class Courier {

    private val orderQueue = ConcurrentLinkedDeque<Order>()

    val availability: List<LocalDate>? = null // TODO populate this field someway

    fun receive(order: Order) {
        order.courier = this
    }

}
