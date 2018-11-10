package com.frank.entity.courier.strategies.impl

import com.frank.entity.courier.strategies.PossibleDateCalculatorStrategy

import java.time.LocalDate
import java.util.ArrayList
import java.util.concurrent.ThreadLocalRandom

class RandomPossibleDateCalculatorStrategy : PossibleDateCalculatorStrategy {

    override fun calculate(): List<LocalDate> {
        val listDate = ArrayList<LocalDate>()
        listDate.add(calculateRandomLocalDate())
        return listDate
    }

    private fun calculateRandomLocalDate(): LocalDate {
        val minDay = LocalDate.now().toEpochDay()
        val maxDay = LocalDate.now().plusYears(50).toEpochDay()
        val randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay)
        return LocalDate.ofEpochDay(randomDay)
    }
}
