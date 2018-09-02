package com.frank.shipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPossibleDateCalculatorStrategy implements PossibleDateCalculatorStrategy {

    @Override
    public List<LocalDate> calculate() {
        List<LocalDate> listDate = new ArrayList<>();
        listDate.add(calculateRandomLocalDate());
        return listDate;
    }

    private LocalDate calculateRandomLocalDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}
