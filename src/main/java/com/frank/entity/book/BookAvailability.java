package com.frank.entity.book;

class BookAvailability {

    private int availability;

    private BookAvailability(int availability) {
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public void increase() {
        this.availability += 1;
    }

    final public static BookAvailability ZERO = new BookAvailability(0);
    final public static BookAvailability ONE = new BookAvailability(1);

}
