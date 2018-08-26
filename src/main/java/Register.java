import java.util.HashMap;
import java.util.Map;

public class Register {

    final private Map<Object, Book> register;

    public Register() {
        register = new HashMap<Object, Book>();
    }

    public void add(Book book) {
        book.addAvailability();
        register(book);
    }

    public void add(Book book, int copies) {
        book.addAvailability(copies);
        register(book);
    }

    public Book get(Object key) {
        return register.get(key);
    }

    private void register(Book book) {
        if (!register.containsKey(book.getId())) {
            register.put(book.getId(), book);
        }
    }

}
