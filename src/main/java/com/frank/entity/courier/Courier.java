package com.frank.entity.courier;

import com.frank.entity.order.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Courier {

    final private Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();

    private List<LocalDate> availability; // TODO populate this field someway

    public void receive(Order order) {
        order.setCourier(this);
    }

    public List<LocalDate> getAvailability() {
        return availability;
    }

}
