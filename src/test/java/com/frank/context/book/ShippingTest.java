package com.frank.context.book;

import com.frank.context.shipment.Courier;
import com.frank.context.shipment.ShippingRequest;
import com.frank.context.shipment.ShippingResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ShippingTest {

    final BookRegister bookRegister = new BookRegister();

    @Before
    public void init() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        book.setActualPrice(BigDecimal.valueOf(12.39));
        bookRegister.add(book);
    }

    @Test
    public void testRequestShipForTheCourier() {
        Courier courier = new Courier();
        Order order = new Order();
        LocalDate date = LocalDate.now();
        ShippingRequest shippingRequest = new ShippingRequest(courier, order, date);
        shippingRequest.send();
        assertTrue(shippingRequest.isShippingRequestSent());
    }

    @Test
    public void testShippingRequestAcceptedThenAddTheCostForDeliveryToTheOrder() {
        Courier courier = new Courier();
        Order order = new Order();
        LocalDate date = LocalDate.now();
        ShippingRequest shippingRequest = new ShippingRequest(courier, order, date);
        shippingRequest.send();
        assertTrue(shippingRequest.isShippingRequestSent());
        ShippingResponse shippingResponse = shippingRequest.accept();
        BigDecimal deliveryFee = shippingResponse.getDeliveryFee();
        order.setDeliveryFee(deliveryFee);
        assertEquals(order.getDeliveryFee(), deliveryFee);
    }

    @Test
    public void testCalculateTheOrderTotalCost() {
        Book book1 = bookRegister.get("isbn");
        Book book2 = bookRegister.get("isbn");
        Book book3 = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection(book1, book2, book3);
        Order order = new Order();
        order.add(bookCollection);
        Courier courier = new Courier();
        LocalDate date = LocalDate.now();
        ShippingRequest shippingRequest = new ShippingRequest(courier, order, date);
        shippingRequest.send();
        assertTrue(shippingRequest.isShippingRequestSent());
        ShippingResponse shippingResponse = shippingRequest.accept();
        BigDecimal deliveryFee = shippingResponse.getDeliveryFee();
        order.setDeliveryFee(deliveryFee);
        assertEquals(order.getDeliveryFee(), deliveryFee);
        assertEquals(order.getTotalCost(), order.getPrice().add(order.getDeliveryFee()));
    }
}
