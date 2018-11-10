package com.frank.usecase

import com.frank.entity.book.BookCollection
import com.frank.entity.order.Order
import com.frank.entity.courier.Courier
import com.frank.entity.courier.strategies.BestDeliveryStrategy
import com.frank.entity.courier.strategies.PossibleDateCalculatorStrategy
import com.frank.entity.courier.dto.ShipmentDetails
import java.time.LocalDate

class OrderUseCase {

    fun newOrder(bookCollection: BookCollection): Order {
        val order = Order()
        order.add(bookCollection)
        return order
    }

    // TODO check that availableDate list is not empty

    fun sendOrderToCourier(order: Order, courier: Courier) {
        courier.receive(order)
    }

    fun checkCourierDeliveryDate(courier: Courier, possibleDateCalculatorStrategy: PossibleDateCalculatorStrategy) {
        if (!courier.availability!!.containsAll(possibleDateCalculatorStrategy.calculate())) {
            throw RuntimeException("This courier isn't available during the period provided")
        }
    }

    fun sendOrderToBestCourier(order: Order, strategy: BestDeliveryStrategy, vararg couriers: Courier) {
        val shipmentDetails = strategy.calculate()
        order.courier = shipmentDetails.courier
    }
}
