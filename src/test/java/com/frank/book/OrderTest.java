package com.frank.book;

import com.frank.shipment.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class OrderTest {

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

    @After
    public void end() {
        bookRegister.clean();
    }

    @Test
    public void testPlaceAnOrder() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order order = new Order();
        order.add(bookCollection);
        assertTrue(bookCollection.isOrdered());
    }

    @Test
    public void testCalculateOrderedBooksTotalPrice() {
        Book book1 = bookRegister.get("isbn");
        Book book2 = bookRegister.get("isbn");
        Book book3 = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection(book1, book2, book3);
        Order order = new Order();
        order.add(bookCollection);
        assertEquals(order.getBooksPrice().doubleValue(), book1.getActualPrice().add(book2.getActualPrice()).add(book3.getActualPrice()).doubleValue());
    }

    @Test
    public void testContactCourierSendingOrderInformation() {
        Order order = new Order();
        Courier courier = new Courier();
        courier.receive(order);
        assertTrue(order.getOrderState().isReceived());
        assertTrue(order.getContactedCourierList().contains(courier));
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysForACourierThenPickTheNearestOne() {
        Order order = new Order();
        Courier courier = new Courier();
        List<LocalDate> dateList = courier.getPossibleDates(order);
        LocalDate date = dateList.get(0);
        final Optional<LocalDate> min = dateList.stream().min((o1, o2) -> o1.compareTo(o2));
        assertEquals(min.get(), date);
    }


    @Test
    public void testContactTwoCourierSendingOrderInformation() {
        Order order = new Order();
        Courier firstCourier = new Courier();
        Courier secondCourier = new Courier();
        firstCourier.receive(order);
        secondCourier.receive(order);
        assertTrue(order.getOrderState().isReceived());
        assertTrue(order.getContactedCourierList().contains(firstCourier));
        assertTrue(order.getContactedCourierList().contains(secondCourier));
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysFromCouriersThenPickTheNearestOne() {
        Order order = new Order();
        Courier firstCourier = new Courier();
        Courier secondCourier = new Courier();
        List<LocalDate> firstCourierDateList = firstCourier.getPossibleDates(order);
        List<LocalDate> secondCourierDateList = secondCourier.getPossibleDates(order);
        List<LocalDate> dateList = new ArrayList<>();
        dateList.addAll(firstCourierDateList);
        dateList.addAll(secondCourierDateList);
        LocalDate date = dateList.get(0);
        date = date.minusDays(1);
        final Optional<LocalDate> min = dateList.stream().min((o1, o2) -> o1.compareTo(o2));
        assertEquals(min.get(), date);
    }
}
