package com.frank.context.shipment.strategies;

import com.frank.context.shipment.Courier;

public interface BestCourierForShipmentStrategy {

    Courier calculate();
}
