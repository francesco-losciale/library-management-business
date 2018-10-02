package com.frank.context.book;

import java.util.HashMap;
import java.util.Map;

public class BookRegister {

    final private Map<Object, Book> register;

    final private Map<Book, BookAvailability> availability;

    public BookRegister() {
        register = new HashMap<>();
        availability = new HashMap<>();
    }

    public void add(Book item) {
        availability.put(item, BookAvailability.ONE);
        register(item);
    }

    public Book get(Object key) {
        return register.get(key);
    }

    public void setBookAsOutOfOrder(Book item) {
        this.availability.put(item, BookAvailability.ZERO);
    }

    public void setBookAvailable(Book item) {
        if (this.availability.containsKey(item)) {
            this.availability.get(item).increase();
        } else {
            this.availability.put(item, BookAvailability.ONE);
        }
    }

    public boolean isBookAvailable(Book item) {
        return this.availability.get(item).getAvailability() > 0;
    }

    public void clean() {
        register.clear();
    }

    private void register(Book item) {
        if (!register.containsKey(item.getId())) {
            register.put(item.getId(), item);
        }
    }

}
