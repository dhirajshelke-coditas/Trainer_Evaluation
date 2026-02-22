package com.hotel.model;

import com.hotel.exception.*;
import com.hotel.model.reservations.Reservation;
import com.hotel.model.rooms.Room;
import com.hotel.service.GuestService;
import com.hotel.service.ReservationService;
import com.hotel.service.RoomService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;



public class HotelCLI {

    private final Scanner            scanner;
    private final GuestService       guestService;
    private final RoomService        roomService;
    private final ReservationService reservationService;

    public HotelCLI() {
        scanner            = new Scanner(System.in);
        guestService       = new GuestService();
        roomService        = new RoomService();
        reservationService = new ReservationService(guestService, roomService);
    }


    public void start() {
        printBanner();
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addGuest();
                case "2" -> makeReservation();
                case "3" -> viewRooms();
                case "4" -> cancelReservation();
                case "5" -> viewGuests();
                case "6" -> viewReservations();
                case "0" -> {
                    System.out.println("\nThank you for using the Hotel Management System. Goodbye!");
                    running = false;
                }
                default  -> System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        }
    }

    // ─── Menu ─────────────────────────────────────────────────────────────────

    private void printMainMenu() {
        System.out.println("""

                ╔══════════════════════════════════╗
                ║           MAIN MENU              ║
                ║  PLease choose an option from    ║
                ║             below                ║
                ╠══════════════════════════════════╣
                ║  1. Add Guest                    ║
                ║  2. Make Reservation             ║
                ║  3. View Rooms                   ║
                ║  4. Cancel Reservation           ║
                ║  5. View All Guests              ║
                ║  6. View All Reservations        ║
                ║  0. Exit                         ║
                ╚══════════════════════════════════╝
                Enter choice: """);
    }

    //  Add Guest

    private void addGuest() {
        System.out.println("\n--- Add New Guest ---");
        try {
            System.out.print("Guest ID   : "); String id    = scanner.nextLine().trim();
            System.out.print("Full Name  : "); String name  = scanner.nextLine().trim();
            System.out.print("Email      : "); String email = scanner.nextLine().trim();
            System.out.print("Phone      : "); String phone = scanner.nextLine().trim();

            guestService.addGuest(id, name, email, phone);

        } catch (InvalidGuestException e) {
            System.out.println("✘ Error: " + e.getMessage());
        }
    }

    //  Make Reservation

    private void makeReservation() {
        System.out.println("\n--- Make Reservation ---");
        System.out.println("Type: 1->Room  2->Event Hall  3->Spa");
        System.out.print("Choose type: ");
        String type = scanner.nextLine().trim();

        try {
            System.out.print("Reservation ID  : "); String resId   = scanner.nextLine().trim();
            System.out.print("Guest ID        : "); String guestId = scanner.nextLine().trim();
            System.out.print("Date (YYYY-MM-DD): "); LocalDate date = parseDate(scanner.nextLine().trim());

            switch (type) {
                case "1" -> {
                    System.out.print("Room Number : "); String room = scanner.nextLine().trim();
                    System.out.print("Nights      : "); int nights  = parseInt(scanner.nextLine().trim(), "nights");
                    reservationService.addRoomReservation(resId, guestId, date, room, nights);
                }
                case "2" -> {
                    System.out.print("Hall Name   : "); String hall  = scanner.nextLine().trim();
                    System.out.print("Hours       : "); int hours    = parseInt(scanner.nextLine().trim(), "hours");
                    reservationService.addEventHallReservation(resId, guestId, date, hall, hours);
                }
                case "3" -> {
                    System.out.print("Treatment   : "); String treat  = scanner.nextLine().trim();
                    System.out.print("Duration(min): "); int mins     = parseInt(scanner.nextLine().trim(), "minutes");
                    reservationService.addSpaReservation(resId, guestId, date, treat, mins);
                }
                default -> System.out.println("✘ Invalid reservation type.");
            }

        } catch (InvalidReservationException e) {
            System.out.println("Reservation Error: " + e.getMessage());
        } catch (GuestNotFoundException e) {
            System.out.println("Guest Error: " + e.getMessage());
        } catch (RoomNotFoundException e) {
            System.out.println("Room Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    //  View Rooms 

    private void viewRooms() {
        System.out.println("\n--- All Rooms ---");
        List<Room> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println(" No rooms found.");
        } else {
            rooms.forEach(r -> System.out.println("  " + r));
        }
    }

    // Cancel Reservation 

    private void cancelReservation() {
        System.out.println("\n--- Cancel Reservation ---");
        System.out.print("Reservation ID to cancel: ");
        String id = scanner.nextLine().trim();

        try {
            reservationService.cancelReservation(id);
        } catch (InvalidReservationException | RoomNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //  View Helpers 

    private void viewGuests() {
        System.out.println("\n--- All Guests ---");
        List<Guest> guests = guestService.getAllGuests();
        if (guests.isEmpty()) {
            System.out.println("  No guests registered.");
        } else {
            guests.forEach(g -> System.out.println("  " + g));
        }
    }

    private void viewReservations() {
        System.out.println("\n--- All Reservations ---");
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("  No reservations found.");
        } else {
            reservations.forEach(r -> System.out.println("  " + r));
        }
    }

    //  Utility 

    private LocalDate parseDate(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private int parseInt(String input, String fieldName) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number for " + fieldName + ": " + input);
        }
    }

    private void printBanner() {
        System.out.println("""
                ╔══════════════════════════════════════════╗
                     🙏  WELCOME TO ISHANYA PALACE  🙏       
               
                ╚══════════════════════════════════════════╝
                """);
    }
}