package com.frank.shipment;

import com.frank.book.Order;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Courier {

    final private Queue<Order> orderQueue = new ConcurrentLinkedDeque<>();

    public void receive(Order order) {
        order.setAsReceived(this);
    }
}
