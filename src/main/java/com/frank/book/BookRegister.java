package com.frank.book;

import java.util.HashMap;
import java.util.Map;

public class BookRegister {

    final private Map<Object, Book> register;

    final private Map<Book, Integer> availability;

    public BookRegister() {
        register = new HashMap<>();
        availability = new HashMap<>();
    }

    public void add(Book item) {
        availability.put(item, 1);
        register(item);
    }

    public void add(Book item, int copies) {
        availability.put(item, copies);
        register(item);
    }

    public Book get(Object key) {
        return register.get(key);
    }

    public void setBookAsOutOfOrder(Book item) {
        this.availability.put(item, 0);
    }

    public void setBookAvailable(Book item) {
        if (this.availability.containsKey(item)) {
            Integer copies = this.availability.get(item);
            this.availability.put(item, copies + 1);
        } else {
            this.availability.put(item, 1);
        }
    }

    public boolean isBookAvailable(Book item) {
        return this.availability.get(item) > 0;
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
