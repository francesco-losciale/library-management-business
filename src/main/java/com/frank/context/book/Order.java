package com.frank.context.book;

import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Repository;
import com.frank.context.shipment.Courier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Hydratable {

    private final String orderNumber = generateOrderNumber();
    private final List<Courier> contactedCourierList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();

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

    public List<Courier> getContactedCourierList() {
        return this.contactedCourierList;
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
        bookCollection.setOrdered(true);
    }

    public void addAsContacted(Courier courier) {
        this.state = OrderState.RECEIVED_BY_THE_COURIER;
        this.contactedCourierList.add(courier);
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    @Override
    public boolean equals(Object object) {
        if (Order.class.isInstance(object)) {
            return this.getOrderNumber().equals(((Order)object).getOrderNumber());
        }
        return false;
    }

    @Override
    public Object save(Repository repository) {
        return repository.add(this);
    }

    private String generateOrderNumber() {
        return "1234";
    }

}
