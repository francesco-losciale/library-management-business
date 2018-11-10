package com.frank.entity.courier.strategies.impl

import com.frank.entity.order.Order
import com.frank.entity.courier.Courier
import com.frank.entity.courier.strategies.BestDeliveryStrategy
import com.frank.entity.courier.dto.ShipmentDetails

import java.time.LocalDate
import java.util.ArrayList
import java.util.Arrays

class NearestDeliveryStrategy(private val order: Order, vararg couriers: Courier) : BestDeliveryStrategy {

    private val couriers = ArrayList<Courier>()

    init {
        this.couriers.addAll(Arrays.asList(*couriers))
    }

    override fun calculate(): ShipmentDetails {
        val shipmentDetails = ShipmentDetails()
        if (this.couriers.size > 0) {
            var best = this.couriers[0]
            for (courier in this.couriers) {
                if (getMinDateAmongList(courier.availability!!).isBefore(getMinDateAmongList(best.availability!!))) {
                    best = courier
                }
            }
            shipmentDetails.courier = best
        }
        return shipmentDetails
    }

    private fun getMinDateAmongList(list: List<LocalDate>): LocalDate {
        return list.stream().min { obj, other -> obj.compareTo(other) }.get()
    }
}
