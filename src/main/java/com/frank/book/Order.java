package com.frank.book;

import com.frank.shipment.Courier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderState orderState = new OrderState();

    private List<Courier> contactedCourierList = new ArrayList<>();

    private List<Book> bookList = new ArrayList<>();

    private BigDecimal booksPrice = BigDecimal.ZERO;

    private BigDecimal deliveryFee = BigDecimal.ZERO;

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
}
