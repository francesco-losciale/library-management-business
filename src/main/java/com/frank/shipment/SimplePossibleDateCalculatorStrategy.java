package com.frank.shipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimplePossibleDateCalculatorStrategy implements PossibleDateCalculatorStrategy {

    @Override
    public List<LocalDate> calculate() {
        List<LocalDate> listDate = new ArrayList<>();
        listDate.add(LocalDate.now());
        return listDate;
    }
}
