package com.frank.context.shipment.strategies.impl;

import com.frank.context.book.Order;
import com.frank.context.shipment.Courier;
import com.frank.context.shipment.strategies.BestCourierForShipmentStrategy;
import com.frank.context.shipment.strategies.BestDateForShipmentStrategy;

import java.time.LocalDate;
import java.util.*;

public class SimpleBestCourierForShipmentStrategy implements BestCourierForShipmentStrategy {

    final private List<Courier> couriers = new ArrayList<>();

    final private Order order;

    public SimpleBestCourierForShipmentStrategy(Order order, Courier...couriers) {
        this.order = order;
        this.couriers.addAll(Arrays.asList(couriers));
    }

    @Override
    public Courier calculate() {
        final Map<LocalDate, Courier> courierLocalDateMap = mapCourierOnBestDateTheyOffer();
        LocalDate bestDate = courierLocalDateMap.keySet().stream().min(LocalDate::compareTo).get();
        return courierLocalDateMap.get(bestDate);
    }

    private Map<LocalDate, Courier> mapCourierOnBestDateTheyOffer() {
        final Map<LocalDate, Courier> courierLocalDateMap = new HashMap<>();
        this.couriers.stream().forEach((courier -> {
            final BestDateForShipmentStrategy bestDateForShipmentStrategy = new SimpleBestDateForShipmentStrategy(order, courier);
            LocalDate bestDate = bestDateForShipmentStrategy.calculate();
            courierLocalDateMap.put(bestDate, courier);
        }));
        return courierLocalDateMap;
    }
}
