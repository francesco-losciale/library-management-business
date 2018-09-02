package com.frank.book;

import com.frank.shipment.Courier;
import com.frank.shipment.ShippingRequest;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;

public class ShippingTest {

    @Test
    public void testRequestShipForTheCourier() {
        Courier courier = new Courier();
        Order order = new Order();
        LocalDate date = LocalDate.now();
        ShippingRequest shippingRequest = new ShippingRequest(courier, order, date);
        shippingRequest.send();
        assertTrue(shippingRequest.isShippingRequestSent());
    }
}
