package com.frank.usecase;

import com.frank.context.book.BookCollection;
import com.frank.context.book.Order;
import com.frank.context.shipment.Courier;
import com.frank.context.shipment.strategies.BestDeliveryStrategy;
import com.frank.context.shipment.strategies.PossibleDateCalculatorStrategy;

public class OrderUseCase {

    public Order newOrder(BookCollection bookCollection) {
        Order order = new Order();
        order.add(bookCollection);
        return order;
    }

    public void sendOrderToCourier(Order order, Courier courier) {
        courier.receive(order);
    }

    public void checkCourierDeliveryDate(Courier courier, PossibleDateCalculatorStrategy possibleDateCalculatorStrategy) {
        if (!courier.getAvailability().contains(possibleDateCalculatorStrategy.calculate())) {
            throw new RuntimeException("This courirer isn't available in the order dates");
        }
    }

    public void sendOrderToBestCourier(Order order, BestDeliveryStrategy strategy, Courier... couriers) {
        throw new RuntimeException("Not implemented yet");
        // Find the best courier
        // order.setCourier(bestCourier)
    }
}
