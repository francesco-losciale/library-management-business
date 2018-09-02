package com.frank.book;

public class OrderState {

    enum OrderStatus {
        INITIALIZED,
        RECEIVED_BY_THE_COURIER,
    }

    private OrderStatus orderStatus = OrderStatus.INITIALIZED;

    public void received() {
        this.orderStatus = OrderStatus.RECEIVED_BY_THE_COURIER;
    }

    public boolean isReceived() {
        return this.orderStatus == OrderStatus.RECEIVED_BY_THE_COURIER;
    }

}
