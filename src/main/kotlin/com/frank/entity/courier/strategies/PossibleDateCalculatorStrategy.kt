package com.frank.entity.courier.strategies

import java.time.LocalDate

interface PossibleDateCalculatorStrategy {

    fun calculate(): List<LocalDate>
}
