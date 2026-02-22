package com.hotel.model.reservations;


import java.time.LocalDate;

/**
 * HMS-102: Abstract base class for all reservation types.
 */
public abstract class Reservation {

    private String      reservationId;
    private String      guestId;
    private LocalDate   reservationDate;

    public Reservation(String reservationId, String guestId, LocalDate reservationDate) {
        this.reservationId   = reservationId;
        this.guestId         = guestId;
        this.reservationDate = reservationDate;
    }

    public String    getReservationId()   { return reservationId; }
    public String    getGuestId()         { return guestId; }
    public LocalDate getReservationDate() { return reservationDate; }

    public abstract String getReservationType();
    public abstract double getTotalCost();

    @Override
    public String toString() {
        return String.format("%s|ID=%s, GuestID=%s, Date=%s, Cost=%.2f|",
                getReservationType(), reservationId, guestId, reservationDate, getTotalCost());
    }
}

