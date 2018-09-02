package com.frank.shipment;

import com.frank.book.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Courier {

    final private Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();

    public void receive(Order order) {
        order.setAsReceived(this);
    }

    public List<LocalDate> getPossibleDates(Order order) {
        // On the basis of the order data or some internal Courier states,
        // choose the best strategy to calculate the possible dates
        PossibleDateCalculatorStrategy possibleDateCalculatorStrategy = new SimplePossibleDateCalculatorStrategy();
        return possibleDateCalculatorStrategy.calculate();
    }

}
