package com.frank.book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

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
}
