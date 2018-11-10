package com.frank.entity.courier.dto

import java.math.BigDecimal
import java.time.LocalDate

import com.frank.entity.courier.Courier

class ShipmentDetails {

    var courier: Courier? = null

    var date: LocalDate? = null

    var deliveryFee: BigDecimal? = null
}
