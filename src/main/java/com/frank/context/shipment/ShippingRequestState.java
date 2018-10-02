package com.frank.context.shipment;

public class ShippingRequestState {

    enum ShippingRequestStateEnum {
        INITIALIZED,
        SENT_TO_THE_COURIER,
        ACCEPTED,
    }

    private ShippingRequestStateEnum orderStateEnum = ShippingRequestStateEnum.INITIALIZED;

    public void sent() {
        this.orderStateEnum = ShippingRequestStateEnum.SENT_TO_THE_COURIER;
    }

    public boolean isSent() {
        return this.orderStateEnum == ShippingRequestStateEnum.SENT_TO_THE_COURIER;
    }

    public void accept() {
        this.orderStateEnum = ShippingRequestStateEnum.ACCEPTED;
    }

}
