package com.frank.context.book;

import com.frank.capabilities.Dehydrator;
import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Hydrator;
import com.frank.context.shipment.Courier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order implements Hydratable {

    private Object id;

    private OrderState orderState = new OrderState();

    private List<Courier> contactedCourierList = new ArrayList<>();

    private List<Book> bookList = new ArrayList<>();

    private BigDecimal booksPrice = BigDecimal.ZERO;

    private BigDecimal deliveryFee = BigDecimal.ZERO;

    public Order() {
        this.id = "1"; // TODO generate
    }

    public Object getId() {
        return id;
    }

    public void add(BookCollection bookCollection) {
        bookCollection.stream().forEach((book) -> {
            bookList.add(book);
            booksPrice = booksPrice.add(book.getActualPrice());
        });
        bookCollection.setOrdered(true);
    }

    public BigDecimal getBooksPrice() {
        return booksPrice;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void addAsContacted(Courier courier) {
        this.orderState.received();
        this.contactedCourierList.add(courier);
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public List<Courier> getContactedCourierList() {
        return this.contactedCourierList;
    }

    public BigDecimal getTotalCost() {
        return getDeliveryFee().add(getBooksPrice());
    }

    @Override
    public void hydrate(Hydrator hydrator) {
        hydrator.hydrate(this);
    }

    @Override
    public void dehydrate(Dehydrator dehydrator) {
        dehydrator.dehydrate(this);
    }

    @Override
    public boolean equals(Object obj) {
        return Order.class.cast(obj).getId().equals(this.getId());
    }

}
