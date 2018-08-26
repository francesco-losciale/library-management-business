import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class BookCollectionTest {

    final Register register = new Register();

    @Before
    public void init() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        register.add(book);
    }

    @After
    public void end() {
        register.clean();
    }

    @Test
    public void testCollectBooksTogether() {
        Book book = register.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        assertTrue(bookCollection.contains(book));
    }

    @Test
    public void testSeekBookInCollection() {
        Book book = register.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Book result = bookCollection.seek(book);
        assertEquals(result, book);
    }

    @Test
    public void testSeekBookInCollections() {
        Book book = register.get("isbn");
        BookCollection bookCollection1 = new BookCollection();
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.add(book);
        Book result = BookCollection.seek(book, bookCollection1, bookCollection2);
        assertEquals(result, book);
    }

    @Test
    public void testMoveBookToDifferentCollection() {
        Book book = register.get("isbn");
        BookCollection bookCollection1 = new BookCollection();
        bookCollection1.add(book);
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.move(book, bookCollection2);
        assertFalse(bookCollection1.contains(book));
        assertTrue(bookCollection2.contains(book));
    }

    @Test
    public void testMakeBookOutOfOrder() {
        Book book = register.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order.setBookAsOutOfOrder(book);
        assertTrue(book.isOutOfOrder());
    }

    @Test
    public void testMakeBookAvailable() {
        Book book = register.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order.setBookAvailable(book);
        assertFalse(book.isOutOfOrder());
    }

    @Test
    public void testPlaceCollectionIntoShelf() {
        Book book = register.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        BookShelf bookShelf = new BookShelf(10);
        bookShelf.place(bookCollection);
        List<Book> notFoundBookList = new ArrayList<>();
        bookCollection.stream().forEach(currentBook -> {
            if (!bookShelf.contains(currentBook)) {
                notFoundBookList.add(currentBook);
            }
        });
        assertTrue(notFoundBookList.isEmpty());
    }
}
