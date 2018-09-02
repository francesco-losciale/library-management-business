package com.frank.book;

import com.frank.shipment.Courier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderState orderState = new OrderState();

    private Courier courierInCharge;

    private List<Book> bookList = new ArrayList<>();

    private BigDecimal booksPrice = BigDecimal.ZERO;

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

    public void setAsReceived(Courier courier) {
        this.orderState.received();
        this.courierInCharge = courier;
    }

    public OrderState getOrderState() {
        return orderState;
    }

}
