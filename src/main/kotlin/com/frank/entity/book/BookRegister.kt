package com.frank.entity.book

import java.math.BigDecimal
import java.util.HashMap

class BookRegister {

    private val register: MutableMap<Any, Book>

    private val availability: MutableMap<Book, BookAvailability>

    init {
        register = HashMap()
        availability = HashMap()
    }

    fun add(item: Book) {
        availability[item] = BookAvailability.ONE
        register(item)
    }

    operator fun get(key: Any): Book? {
        return register[key]
    }

    fun setBookAsOutOfOrder(item: Book) {
        this.availability[item] = BookAvailability.ZERO
    }

    fun setBookAvailable(item: Book) {
        if (this.availability.containsKey(item)) {
            this.availability[item]?.increase()
        } else {
            this.availability[item] = BookAvailability.ONE
        }
    }

    fun isBookAvailable(item: Book): Boolean {
//        TODO 2 can't use the operator while accessing nullable fields...
//        Infix call corresponds to a dot-qualified call 'this.availability[item]?.availability.compareTo(0)'
//          which is not allowed on a nullable receiver 'this.availability[item]?.availability'. Use '?.'-qualified call instead
//        return this.availability[item]?.availability > 0
        return this.availability[item]?.availability?.compareTo(0) != 0
    }

    fun clean() {
        register.clear()
    }

    private fun register(item: Book) {
        if (!register.containsKey(item.id)) {
            register[item.id] = item
        }
    }

}
