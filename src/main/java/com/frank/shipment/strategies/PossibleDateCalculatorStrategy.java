package com.frank.shipment.strategies;

import java.time.LocalDate;
import java.util.List;

public interface PossibleDateCalculatorStrategy {

    List<LocalDate> calculate();
}
