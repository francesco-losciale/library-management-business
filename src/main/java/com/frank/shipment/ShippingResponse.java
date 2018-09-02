package com.frank.shipment;

import java.math.BigDecimal;

public class ShippingResponse {

    private BigDecimal deliveryFee;

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
