package com.frank.test.book;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.frank.entity.book.Book;
import com.frank.entity.book.BookCollection;
import com.frank.entity.book.BookRegister;
import com.frank.entity.courier.Courier;
import com.frank.entity.courier.dto.ShipmentDetails;
import com.frank.entity.order.Order;
import com.frank.usecase.DeliveryUseCase;

public class DeliveryTest {

    final BookRegister bookRegister = new BookRegister();
    final DeliveryUseCase deliveryUseCase = new DeliveryUseCase();

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
    public void testRequestDeliverSingleBook() {
        Courier courier = new Courier();
        Order order = new Order();
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection(book);
        order.add(bookCollection);

        ShipmentDetails shipmentDetails = Mockito.mock(ShipmentDetails.class);
        when(shipmentDetails.getCourier()).thenReturn(courier);
        when(shipmentDetails.getDeliveryFee()).thenReturn(BigDecimal.valueOf(3.99));

        deliveryUseCase.send(shipmentDetails, order);
        assertEquals(book.getActualPrice().add(BigDecimal.valueOf(3.99)), order.getTotalCost());
    }


    @Test
    public void testRequestDeliverMoreThanOneBook() {
        Courier courier = new Courier();
        Order order = new Order();
        Book book1 = bookRegister.get("isbn");
        Book book2 = bookRegister.get("isbn");
        Book book3 = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection(book1, book2, book3);
        order.add(bookCollection);

        ShipmentDetails shipmentDetails = Mockito.mock(ShipmentDetails.class);
        when(shipmentDetails.getCourier()).thenReturn(courier);
        when(shipmentDetails.getDeliveryFee()).thenReturn(BigDecimal.valueOf(3.99));

        deliveryUseCase.send(shipmentDetails, order);
        assertEquals(book1.getActualPrice().add(book2.getActualPrice()).add(book3.getActualPrice()).add(BigDecimal.valueOf(3.99)), order.getTotalCost());
    }
}
