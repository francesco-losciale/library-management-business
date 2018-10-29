package com.frank.dto;

import com.frank.context.shipment.Courier;

public class DeliveryDetails {

    private Courier courier;

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
