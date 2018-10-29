package com.frank.context.shipment.strategies.impl;

import com.frank.context.book.Order;
import com.frank.context.shipment.Courier;
import com.frank.context.shipment.strategies.BestDeliveryStrategy;
import com.frank.dto.DeliveryDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
        DeliveryDetails deliveryDetails = new DeliveryDetails();
        if (this.couriers.size() > 0) {
            Courier best = this.couriers.get(0);
            for (Courier courier : this.couriers) {
                if (getMinDateAmongList(courier.getAvailability()).isBefore(getMinDateAmongList(best.getAvailability()))) {
                    best = courier;
                }
            }
            deliveryDetails.setCourier(best);
        }
        return deliveryDetails;
    }

    private LocalDate getMinDateAmongList(List<LocalDate> list) {
        return list.stream().min(LocalDate::compareTo).get();
    }
}
