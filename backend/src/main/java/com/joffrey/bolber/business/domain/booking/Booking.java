package com.joffrey.bolber.business.domain.booking;

import com.joffrey.bolber.business.domain.driver.Coordinates;
import com.joffrey.bolber.business.domain.driver.Driver;

import java.util.Objects;

public final class Booking {
    private final Coordinates departure;
    private final Coordinates destination;
    private Driver driver;

    public Booking(Coordinates departure, Coordinates destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
    }

    public Coordinates departure() {
        return departure;
    }

    public Coordinates destination() {
        return destination;
    }

    public Driver driver() {
        return driver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(departure, booking.departure) && Objects.equals(destination, booking.destination) && Objects.equals(driver, booking.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departure, destination, driver);
    }

    @Override
    public String toString() {
        return "Booking{" +
               "departure=" + departure +
               ", destination=" + destination +
               ", driver=" + driver +
               '}';
    }
}
