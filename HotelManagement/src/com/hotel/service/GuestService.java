package com.hotel.service;

import com.hotel.exception.GuestNotFoundException;
import com.hotel.exception.InvalidGuestException;
import com.hotel.model.Guest;

import java.util.ArrayList;
import java.util.List;




public class GuestService {

    private final List<Guest> guests = new ArrayList<>();

  
     // Adds a new guest after validating data and checking for duplicates.
 
    public void addGuest(String guestId, String name, String email, String phone)
            throws InvalidGuestException {

        if (guestId == null || guestId.trim().isEmpty()) {
            throw new InvalidGuestException("Guest ID cannot be empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidGuestException("Guest name cannot be empty.");
        }
        if (email == null || !email.contains("@")) {
            throw new InvalidGuestException("Invalid email address: " + email);
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidGuestException("Phone number cannot be empty.");
        }

        // Duplicate check
        for (Guest g : guests) {
            if (g.getGuestId().equalsIgnoreCase(guestId.trim())) {
                throw new InvalidGuestException("Duplicate Guest ID: " + guestId);
            }
        }

        guests.add(new Guest(guestId.trim(), name.trim(), email.trim(), phone.trim()));
        System.out.println("✔ Guest added successfully: " + name);
    }


    public Guest getGuest(String guestId) throws GuestNotFoundException {
        for (Guest g : guests) {
            if (g.getGuestId().equalsIgnoreCase(guestId)) {
                return g;
            }
        }
        throw new GuestNotFoundException(guestId);
    }

    public List<Guest> getAllGuests() {
        return new ArrayList<>(guests);
    }

    public boolean guestExists(String guestId) {
        for (Guest g : guests) {
            if (g.getGuestId().equalsIgnoreCase(guestId)) return true;
        }
        return false;
    }
}

