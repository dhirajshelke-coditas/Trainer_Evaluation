package com.hotel.model;

import java.time.LocalDate;

import com.hotel.model.reservations.Reservation;

public class SpaReservation extends Reservation {

    private String treatmentType;
    private int    durationMinutes;

    private static final double RATE_PER_MINUTE = 50;

    public SpaReservation(String reservationId, String guestId,
                          LocalDate reservationDate, String treatmentType,
                          int durationMinutes) {
        super(reservationId, guestId, reservationDate);
        this.treatmentType   = treatmentType;
        this.durationMinutes = durationMinutes;
    }

    public String getTreatmentType()  { return treatmentType; }
    public int    getDurationMinutes(){ return durationMinutes; }

    @Override
    public String getReservationType() { return "SpaReservation"; }

    @Override
    public double getTotalCost() { return RATE_PER_MINUTE * durationMinutes; }
}