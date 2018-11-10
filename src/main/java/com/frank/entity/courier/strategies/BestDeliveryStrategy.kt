package com.frank.entity.courier.strategies

import com.frank.entity.courier.dto.ShipmentDetails

interface BestDeliveryStrategy {

    fun calculate(): ShipmentDetails
}
