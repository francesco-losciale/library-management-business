import java.util.ArrayList;
import java.util.List;

public class BookShelf {

    final private List<Book> bookInShelf;

    final private int bookPlaceCount;

    public BookShelf(int bookPlaceCount) {
        this.bookPlaceCount = bookPlaceCount;
        this.bookInShelf = new ArrayList<>(bookPlaceCount);
    }

    public void place(BookCollection bookCollection) {
        if (bookPlaceCount - bookInShelf.size() < 0) {
            throw new RuntimeException("No space in shelf " + this + " to store the collection " + bookCollection);
        }
        bookCollection.stream().forEach( book -> bookInShelf.add(book));
    }

    public boolean contains(Book book) {
        return bookInShelf.contains(book);
    }
}
