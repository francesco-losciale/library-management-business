package com.frank.context.book;

import com.frank.entity.courier.strategies.BestDeliveryStrategy;
import com.frank.entity.courier.strategies.impl.RandomPossibleDateCalculatorStrategy;
import com.frank.entity.courier.strategies.impl.NearestDeliveryStrategy;
import com.frank.entity.book.Book;
import com.frank.entity.book.BookCollection;
import com.frank.entity.book.BookRegister;
import com.frank.entity.courier.Courier;
import com.frank.entity.order.Order;
import com.frank.entity.order.OrderState;
import com.frank.usecase.OrderUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class OrderTest {

    final BookRegister bookRegister = new BookRegister();
    final OrderUseCase orderUseCase = new OrderUseCase();

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
        Order order = orderUseCase.newOrder(bookCollection);
        assertTrue(order.getBookList().contains(book));
    }

    @Test
    public void testCalculateOrderedBooksTotalPrice() {
        Book book1 = bookRegister.get("isbn");
        Book book2 = bookRegister.get("isbn");
        Book book3 = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection(book1, book2, book3);
        assertEquals(orderUseCase.newOrder(bookCollection).getPrice().doubleValue(),
                book1.getActualPrice().add(book2.getActualPrice()).add(book3.getActualPrice()).doubleValue());
    }

    @Test
    public void testContactCourierSendingOrderInformation() {
        Order order = orderUseCase.newOrder(new BookCollection());
        Courier courier = new Courier();
        orderUseCase.sendOrderToCourier(order, courier);
        assertEquals(order.getState(), OrderState.RECEIVED_BY_THE_COURIER);
        assertTrue(order.getCourier().equals(courier));
    }

    @Test(expected = RuntimeException.class)
    public void testOrderCheckFailsIfCourierIsNotAvailableForDelivery() {
        Courier courier = new Courier();
        orderUseCase.checkCourierDeliveryDate(courier, new RandomPossibleDateCalculatorStrategy());
    }

    @Test
    public void testReadWhatAreAllTheVacantDaysFromCouriersThenPickTheNearestOne() {
        Order order = new Order();
        Courier firstCourier = Mockito.mock(Courier.class);
        when(firstCourier.getAvailability()).thenReturn(Arrays.asList(LocalDate.of(2018, Month.APRIL, 10)));
        Courier secondCourier = Mockito.mock(Courier.class);
        when(secondCourier.getAvailability()).thenReturn(Arrays.asList(LocalDate.of(2018, Month.APRIL, 9)));
        BestDeliveryStrategy strategy = new NearestDeliveryStrategy(order, firstCourier, secondCourier);
        orderUseCase.sendOrderToBestCourier(order, strategy, firstCourier, secondCourier);
        assertTrue(order.getCourier().equals(secondCourier));
    }

}
