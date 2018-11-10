package com.frank.entity.order

import java.math.BigDecimal
import java.util.ArrayList

import com.frank.entity.Hydratable
import com.frank.entity.book.Book
import com.frank.entity.book.BookCollection
import com.frank.entity.courier.Courier
import com.frank.entity.courier.dto.ShipmentDetails

class Order : Hydratable {

    val orderNumber = generateOrderNumber()
    private val bookList = ArrayList<Book>()

    var courier: Courier? = null
        set(courier) {
            this.state = OrderState.RECEIVED_BY_THE_COURIER
            field = courier
        }
    var state = OrderState.INITIALIZED
        private set
    var price = BigDecimal.ZERO
        private set
    var deliveryFee: BigDecimal? = BigDecimal.ZERO
        private set

    val totalCost: BigDecimal
        get() = deliveryFee!!.add(price)

    fun add(bookCollection: BookCollection) {
        bookCollection.asList().forEach { book ->
            bookList.add(book)
            price = price.add(book.actualPrice!!)
        }
    }

    fun deliver(shipmentDetails: ShipmentDetails) {
        this.deliveryFee = shipmentDetails.deliveryFee
        this.state = OrderState.SENT_DELIVERY
    }

    fun getBookList(): List<Book> {
        return bookList
    }

    override fun equals(`object`: Any?): Boolean {
        return if (Order::class.java.isInstance(`object`)) {
            this.orderNumber == (`object` as Order).orderNumber
        } else false
    }

    private fun generateOrderNumber(): String {
        return "1234"
    }

}
