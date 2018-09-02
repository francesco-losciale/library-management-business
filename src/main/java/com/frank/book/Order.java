package com.frank.book;

import com.frank.shipment.Courier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    public enum OrderStatus {
        INITIALIZED,
        RECEIVED_BY_THE_COURIER,
    }

    private Courier courierInCharge;

    private OrderStatus orderStatus;

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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setAsReceived(Courier courier) {
        this.orderStatus = OrderStatus.RECEIVED_BY_THE_COURIER;
        this.courierInCharge = courier;
    }
}
