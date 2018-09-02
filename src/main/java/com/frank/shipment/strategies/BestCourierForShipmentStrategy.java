package com.frank.shipment.strategies;

import com.frank.shipment.Courier;

public interface BestCourierForShipmentStrategy {

    Courier calculate();
}
