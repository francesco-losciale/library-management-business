package com.frank.entity.courier.strategies;

import com.frank.entity.courier.Courier;

public interface BestCourierForShipmentStrategy {

    Courier calculate();
}
