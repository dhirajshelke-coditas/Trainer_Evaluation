package com.hotel.model;

import java.time.LocalDate;

import com.hotel.model.reservations.Reservation;

public class EventHallReservation extends Reservation {

    private String hallName;
    private int numberOfHours;

    private static final double HOURLY_RATE = 700.00;

    public EventHallReservation(String reservationId, String guestId, LocalDate reservationDate, String hallName, int numberOfHours) {
        super(reservationId, guestId, reservationDate);
        this.hallName = hallName;
        this.numberOfHours = numberOfHours;
    }

    public String getHallName() { 
        return hallName; 
    }
    public int getNumberOfHours(){ 
        return numberOfHours; 
    }

    @Override
    public String getReservationType() {
        return "EventHallReservation"; 
    }

    @Override
    public double getTotalCost() {
        return HOURLY_RATE * numberOfHours; 
    }

}
