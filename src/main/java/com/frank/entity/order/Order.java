package com.frank.entity.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.frank.entity.Hydratable;
import com.frank.entity.book.Book;
import com.frank.entity.book.BookCollection;
import com.frank.entity.courier.Courier;
import com.frank.entity.courier.dto.ShipmentDetails;

public class Order implements Hydratable {

    private final String orderNumber = generateOrderNumber();
    private final List<Book> bookList = new ArrayList<>();

    private Courier courier;
    private OrderState state = OrderState.INITIALIZED;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal deliveryFee = BigDecimal.ZERO;

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public OrderState getState() {
        return state;
    }

    public BigDecimal getTotalCost() {
        return getDeliveryFee().add(getPrice());
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void add(BookCollection bookCollection) {
        bookCollection.stream().forEach((book) -> {
            bookList.add(book);
            price = price.add(book.getActualPrice());
        });
    }

    public void setCourier(Courier courier) {
        this.state = OrderState.RECEIVED_BY_THE_COURIER;
        this.courier = courier;
    }

    public Courier getCourier() {
        return courier;
    }

    public void deliver(ShipmentDetails shipmentDetails) {
        this.deliveryFee = shipmentDetails.getDeliveryFee();
        this.state = OrderState.SENT_DELIVERY;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public boolean equals(Object object) {
        if (Order.class.isInstance(object)) {
            return this.getOrderNumber().equals(((Order)object).getOrderNumber());
        }
        return false;
    }

    private String generateOrderNumber() {
        return "1234";
    }

}
