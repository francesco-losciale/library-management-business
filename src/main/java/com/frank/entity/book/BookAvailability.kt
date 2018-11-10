package com.frank.entity.book

internal class BookAvailability private constructor(availability: Int) {

    var availability: Int = 0
        private set

    init {
        this.availability = availability
    }

    fun increase() {
        this.availability += 1
    }

    companion object {

        val ZERO = BookAvailability(0)
        val ONE = BookAvailability(1)
    }

}
