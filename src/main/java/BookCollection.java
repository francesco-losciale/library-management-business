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

    public void move(Book book, BookCollection bookCollection) {
        final boolean remove = this.collection.remove(book);
        if (remove) {
            bookCollection.add(book);
        } else {
            throw new RuntimeException("No occurrence of " + book + " was found");
        }
    }

    public static Book seek(Book book, BookCollection... collections) {
        for (BookCollection bookCollection : collections) {
            if (bookCollection.seek(book) != null) {
                return bookCollection.seek(book);
            }
        }
        return null;
    }

    public static void setOutOfOrder(Book book) {
        book.setOutOfOrder(true);
    }

}
