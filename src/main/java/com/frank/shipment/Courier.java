package com.frank.shipment;

import com.frank.book.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Courier {

    final private Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();

    private List<LocalDate> calculatedPossibleDates;

    public void receive(Order order) {
        order.addAsContacted(this);
    }

    public void calculatePossibleDates(Order order) {
        // On the basis of the order data or some internal Courier states,
        // choose the best strategy to calculate the possible dates
        PossibleDateCalculatorStrategy possibleDateCalculatorStrategy = new RandomPossibleDateCalculatorStrategy();
        this.calculatedPossibleDates = possibleDateCalculatorStrategy.calculate();
    }

    public List<LocalDate> getCalculatedPossibleDates() {
        return calculatedPossibleDates;
    }

}
