package com.frank.context.shipment;

import com.frank.context.book.Order;
import com.frank.context.shipment.strategies.PossibleDateCalculatorStrategy;
import com.frank.context.shipment.strategies.impl.RandomPossibleDateCalculatorStrategy;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Courier {

    final private Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();

    private List<LocalDate> availability; // TODO populate this field someway

    public void receive(Order order) {
        order.addAsContacted(this);
    }

    public List<LocalDate> getAvailability() {
        return availability;
    }

}
