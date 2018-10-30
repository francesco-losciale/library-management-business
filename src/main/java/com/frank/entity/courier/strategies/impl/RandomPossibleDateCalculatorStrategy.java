package com.frank.entity.courier.strategies.impl;

import com.frank.entity.courier.strategies.PossibleDateCalculatorStrategy;

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
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.now().plusYears(50).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }
}
