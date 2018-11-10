package com.frank.entity.courier.strategies

import com.frank.entity.courier.Courier

interface BestCourierForShipmentStrategy {

    fun calculate(): Courier
}
