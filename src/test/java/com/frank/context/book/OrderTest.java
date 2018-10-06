package com.frank.context.book;

import com.frank.context.shipment.*;
import com.frank.context.shipment.strategies.BestCourierForShipmentStrategy;
import com.frank.context.shipment.strategies.BestDateForShipmentStrategy;
import com.frank.context.shipment.strategies.impl.SimpleBestCourierForShipmentStrategy;
import com.frank.context.shipment.strategies.impl.SimpleBestDateForShipmentStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
        assertEquals(order.getPrice().doubleValue(), book1.getActualPrice().add(book2.getActualPrice()).add(book3.getActualPrice()).doubleValue());
    }

    @Test
    public void testContactCourierSendingOrderInformation() {
        Order order = new Order();
        Courier courier = new Courier();
        courier.receive(order);
        assertTrue(order.getState().isReceived());
        assertTrue(order.getContactedCourierList().contains(courier));
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysForACourierThenPickTheNearestOne() {
        Order order = new Order();
        Courier courier = new Courier();
        courier.calculatePossibleDates(order);
        List<LocalDate> dateList = courier.getCalculatedPossibleDates();
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
        assertTrue(order.getState().isReceived());
        assertTrue(order.getContactedCourierList().contains(firstCourier));
        assertTrue(order.getContactedCourierList().contains(secondCourier));
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysFromCouriersThenPickTheNearestOne() {
        Order order = new Order();
        Courier firstCourier = new Courier();
        Courier secondCourier = new Courier();
        BestDateForShipmentStrategy strategy = new SimpleBestDateForShipmentStrategy(order, firstCourier, secondCourier);
        LocalDate bestDate = strategy.calculate();
        List<LocalDate> firstCourierDateList = firstCourier.getCalculatedPossibleDates();
        List<LocalDate> secondCourierDateList = secondCourier.getCalculatedPossibleDates();
        List<LocalDate> dateList = new ArrayList<>();
        dateList.addAll(firstCourierDateList);
        dateList.addAll(secondCourierDateList);
        final Optional<LocalDate> min = dateList.stream().min(LocalDate::compareTo);
        assertEquals(min.get(), bestDate);
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysFromCouriersThenPickTheCourierThatOffersTheNearestOne() {
        Order order = new Order();
        Courier firstCourier = new Courier();
        Courier secondCourier = new Courier();
        BestCourierForShipmentStrategy strategy = new SimpleBestCourierForShipmentStrategy(order, firstCourier, secondCourier);
        Courier calculatedCourier = strategy.calculate();
        LocalDate firstCourierDate = firstCourier.getCalculatedPossibleDates().stream().min(LocalDate::compareTo).get();
        LocalDate secondCourierDate = secondCourier.getCalculatedPossibleDates().stream().min(LocalDate::compareTo).get();
        Courier bestCourier = firstCourierDate.isBefore(secondCourierDate) ? firstCourier : secondCourier;
        assertEquals(bestCourier, calculatedCourier);
    }

}
