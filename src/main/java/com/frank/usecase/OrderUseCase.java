package com.frank.usecase;

import com.frank.entity.book.BookCollection;
import com.frank.entity.order.Order;
import com.frank.entity.courier.Courier;
import com.frank.entity.courier.strategies.BestDeliveryStrategy;
import com.frank.entity.courier.strategies.PossibleDateCalculatorStrategy;
import com.frank.entity.courier.dto.ShipmentDetails;

public class OrderUseCase {

    public Order newOrder(BookCollection bookCollection) {
        Order order = new Order();
        order.add(bookCollection);
        return order;
    }

    // TODO check that availableDate list is not empty

    public void sendOrderToCourier(Order order, Courier courier) {
        courier.receive(order);
    }

    public void checkCourierDeliveryDate(Courier courier, PossibleDateCalculatorStrategy possibleDateCalculatorStrategy) {
        if (!courier.getAvailability().contains(possibleDateCalculatorStrategy.calculate())) {
            throw new RuntimeException("This courier isn't available in the order dates");
        }
    }

    public void sendOrderToBestCourier(Order order, BestDeliveryStrategy strategy, Courier... couriers) {
        ShipmentDetails shipmentDetails = strategy.calculate();
        order.setCourier(shipmentDetails.getCourier());
    }
}
