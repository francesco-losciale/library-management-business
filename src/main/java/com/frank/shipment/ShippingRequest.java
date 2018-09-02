package com.frank.shipment;

import com.frank.book.Order;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ShippingRequest {

    private ShippingRequestState shippingRequestState = new ShippingRequestState();

    private Courier courier;
    private Order order;
    private LocalDate localDate;

    public ShippingRequest(Courier courier, Order order, LocalDate localDate) {
        this.courier = courier;
        this.order = order;
        this.localDate = localDate;
    }

    public boolean isShippingRequestSent() {
        return this.shippingRequestState.isSent();
    }

    public void send() {
        this.shippingRequestState.sent();
    }

    public ShippingResponse accept() {
        this.shippingRequestState.accept();
        ShippingResponse shippingResponse = new ShippingResponse();
        shippingResponse.setDeliveryFee(BigDecimal.ONE);
        return shippingResponse;
    }
}
