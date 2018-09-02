package com.frank.shipment;

import com.frank.book.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleBestDateForShipmentStrategy implements BestDateForShipmentStrategy {

    final private List<Courier> couriers = new ArrayList<>();

    final private Order order;

    public SimpleBestDateForShipmentStrategy(Order order, Courier... couriers) {
        this.order = order;
        this.couriers.addAll(Arrays.asList(couriers));
    }

    @Override
    public LocalDate calculate() {
        List<LocalDate> allDates = new ArrayList<>();
        couriers.parallelStream().forEach((courier -> {
            courier.calculatePossibleDates(order);
            allDates.addAll(courier.getCalculatedPossibleDates());
        }));
        return allDates.stream().min(LocalDate::compareTo).get();
    }
}
