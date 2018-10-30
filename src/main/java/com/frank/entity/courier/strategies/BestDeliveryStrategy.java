package com.frank.entity.courier.strategies;

import com.frank.entity.courier.dto.ShipmentDetails;

public interface BestDeliveryStrategy {

    ShipmentDetails calculate();
}
