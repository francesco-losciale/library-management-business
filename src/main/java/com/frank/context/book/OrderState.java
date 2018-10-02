package com.frank.context.book;

public class OrderState {

    enum OrderStateEnum {
        INITIALIZED,
        RECEIVED_BY_THE_COURIER,
    }

    private OrderStateEnum orderStateEnum = OrderStateEnum.INITIALIZED;

    public void received() {
        this.orderStateEnum = OrderStateEnum.RECEIVED_BY_THE_COURIER;
    }

    public boolean isReceived() {
        return this.orderStateEnum == OrderStateEnum.RECEIVED_BY_THE_COURIER;
    }

}
