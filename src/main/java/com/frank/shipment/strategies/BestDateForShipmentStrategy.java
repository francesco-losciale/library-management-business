package com.frank.shipment.strategies;

import java.time.LocalDate;

public interface BestDateForShipmentStrategy {

    LocalDate calculate();
}
