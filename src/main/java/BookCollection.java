import java.util.ArrayList;
import java.util.List;

public class BookCollection {

    private List<Book> collection = new ArrayList<Book>();

    public void add(Book book) {
        collection.add(book);
    }

    public boolean contains(Book book) {
        return collection.contains(book);
    }

    public Book seek(Book book) {
        for (Book currentBook : collection) {
            if (currentBook.equals(book)) {
                return book;
            }
        }
        return null;
    }

    public static Book seek(Book book, BookCollection... collections) {
        for (BookCollection bookCollection : collections) {
            if (bookCollection.seek(book) != null) {
                return bookCollection.seek(book);
            }
        }
        return null;
    }

}
