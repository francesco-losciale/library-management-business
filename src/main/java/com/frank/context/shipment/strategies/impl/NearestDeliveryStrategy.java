package com.frank.context.shipment.strategies.impl;

import com.frank.context.book.Order;
import com.frank.context.shipment.Courier;
import com.frank.context.shipment.strategies.BestDeliveryStrategy;
import com.frank.dto.DeliveryDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NearestDeliveryStrategy implements BestDeliveryStrategy {

    final private List<Courier> couriers = new ArrayList<>();

    final private Order order;

    public NearestDeliveryStrategy(Order order, Courier... couriers) {
        this.order = order;
        this.couriers.addAll(Arrays.asList(couriers));
    }

    @Override
    public DeliveryDetails calculate() {
        Courier best = null;
        for (Courier curier : this.curiers)


        List<LocalDate> dateList = new ArrayList<>();
        couriers.parallelStream().forEach((courier -> {
            dateList.addAll(courier.getAvailability());
        }));
        return dateList.stream().min(LocalDate::compareTo).get();
    }
}
