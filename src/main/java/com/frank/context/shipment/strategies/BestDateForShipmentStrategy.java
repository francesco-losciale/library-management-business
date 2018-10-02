package com.frank.context.shipment.strategies;

import java.time.LocalDate;

public interface BestDateForShipmentStrategy {

    LocalDate calculate();
}
