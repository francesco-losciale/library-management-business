package com.frank.shipment;

import java.time.LocalDate;
import java.util.List;

public interface PossibleDateCalculatorStrategy {

    public List<LocalDate> calculate();
}
