package com.hotel.service;

import com.hotel.exception.GuestNotFoundException;
import com.hotel.exception.InvalidReservationException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.model.EventHallReservation;
import com.hotel.model.RoomReservation;
import com.hotel.model.SpaReservation;
import com.hotel.model.reservations.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


 //Supports RoomReservation, EventHallReservation, and SpaReservation via polymorphism.

public class ReservationService {

    private final List<Reservation> reservations = new ArrayList<>();
    private final GuestService      guestService;
    private final RoomService       roomService;

    public ReservationService(GuestService guestService, RoomService roomService) {
        this.guestService = guestService;
        this.roomService  = roomService;
    }

    //  Room Reservation 

    public void addRoomReservation(String reservationId, String guestId,
                                   LocalDate date, String roomNumber,
                                   int nights)
            throws InvalidReservationException, GuestNotFoundException, RoomNotFoundException {

        validateCommon(reservationId, guestId, date);

        if (!guestService.guestExists(guestId)) {
            throw new GuestNotFoundException(guestId);
        }
        if (nights <= 0) {
            throw new InvalidReservationException("Number of nights must be greater than 0.");
        }

        // Book the room (throws RoomNotFoundException if not found or unavailable)
        roomService.bookRoom(roomNumber);
        double price = roomService.getRoom(roomNumber).getPricePerNight();

        reservations.add(new RoomReservation(reservationId, guestId, date, roomNumber, nights, price));
        System.out.println("✔ Room reservation added: " + reservationId);
    }

    //  Event Hall Reservation 
    public void addEventHallReservation(String reservationId, String guestId,
                                        LocalDate date, String hallName,
                                        int hours)
            throws InvalidReservationException, GuestNotFoundException {

        validateCommon(reservationId, guestId, date);

        if (!guestService.guestExists(guestId)) {
            throw new GuestNotFoundException(guestId);
        }
        if (hours <= 0) {
            throw new InvalidReservationException("Number of hours must be greater than 0.");
        }

        reservations.add(new EventHallReservation(reservationId, guestId, date, hallName, hours));
        System.out.println("✔ Event hall reservation added: " + reservationId);
    }

    //  Spa Reservation 

    public void addSpaReservation(String reservationId, String guestId,
                                  LocalDate date, String treatmentType,
                                  int durationMinutes)
            throws InvalidReservationException, GuestNotFoundException {

        validateCommon(reservationId, guestId, date);

        if (!guestService.guestExists(guestId)) {
            throw new GuestNotFoundException(guestId);
        }
        if (durationMinutes <= 0) {
            throw new InvalidReservationException("Duration must be greater than 0 minutes.");
        }

        reservations.add(new SpaReservation(reservationId, guestId, date, treatmentType, durationMinutes));
        System.out.println("✔ Spa reservation added: " + reservationId);
    }

    //  Cancel Reservation 

    public void cancelReservation(String reservationId)
            throws InvalidReservationException, RoomNotFoundException {

        Reservation toRemove = null;
        for (Reservation r : reservations) {
            if (r.getReservationId().equalsIgnoreCase(reservationId)) {
                toRemove = r;
                break;
            }
        }

        if (toRemove == null) {
            throw new InvalidReservationException("Reservation not found: " + reservationId);
        }

        // If it's a room reservation, free the room
        if (toRemove instanceof RoomReservation) {
            roomService.releaseRoom(((RoomReservation) toRemove).getRoomNumber());
        }

        reservations.remove(toRemove);
        System.out.println("✔ Reservation cancelled: " + reservationId);
    }

    //  Queries 

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    // Private Helpers 

    private void validateCommon(String reservationId, String guestId, LocalDate date)
            throws InvalidReservationException {

        if (reservationId == null || reservationId.trim().isEmpty()) {
            throw new InvalidReservationException("Reservation ID cannot be empty.");
        }
        if (guestId == null || guestId.trim().isEmpty()) {
            throw new InvalidReservationException("Guest ID cannot be empty.");
        }
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new InvalidReservationException("Reservation date must be today or in the future.");
        }
        // Duplicate ID check
        for (Reservation r : reservations) {
            if (r.getReservationId().equalsIgnoreCase(reservationId)) {
                throw new InvalidReservationException("Duplicate Reservation ID: " + reservationId);
            }
        }
    }
}
