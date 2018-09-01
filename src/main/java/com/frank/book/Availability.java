package com.frank.book;

public class Availability {

    private int availability;

    private Availability(int availability) {
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public void increase() {
        this.availability += 1;
    }

    final public static Availability ZERO = new Availability(0);
    final public static Availability ONE = new Availability(1);

}
