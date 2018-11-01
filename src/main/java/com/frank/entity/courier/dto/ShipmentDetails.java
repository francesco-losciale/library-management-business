package com.frank.entity.courier.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.frank.entity.courier.Courier;

public class ShipmentDetails {

    private Courier courier;

    private LocalDate date;

    private BigDecimal deliveryFee;

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
