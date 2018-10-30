package com.frank.entity.courier.strategies.impl;

import com.frank.entity.order.Order;
import com.frank.entity.courier.Courier;
import com.frank.entity.courier.strategies.BestDeliveryStrategy;
import com.frank.entity.courier.dto.ShipmentDetails;

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
    public ShipmentDetails calculate() {
        ShipmentDetails shipmentDetails = new ShipmentDetails();
        if (this.couriers.size() > 0) {
            Courier best = this.couriers.get(0);
            for (Courier courier : this.couriers) {
                if (getMinDateAmongList(courier.getAvailability()).isBefore(getMinDateAmongList(best.getAvailability()))) {
                    best = courier;
                }
            }
            shipmentDetails.setCourier(best);
        }
        return shipmentDetails;
    }

    private LocalDate getMinDateAmongList(List<LocalDate> list) {
        return list.stream().min(LocalDate::compareTo).get();
    }
}
