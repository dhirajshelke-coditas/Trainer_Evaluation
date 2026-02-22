package com.hotel.model;


public class Guest {

    private String guestId;
    private String name;
    private String email;
    private String phone;

    public Guest(String guestId, String name, String email, String phone) {
        this.guestId = guestId;
        this.name    = name;
        this.email   = email;
        this.phone   = phone;
    }

    public String getGuestId() { 
        return guestId;
    }
    public String getName() { 
        return name; 
    }
    public String getEmail() { 
        return email; 
    }
    public String getPhone() { 
        return phone; 
    }

    @Override
    public String toString() {
        return String.format("Guest|ID=%s, Name=%s, Email=%s, Phone=%s|",
                guestId, name, email, phone);
    }

}
