package com.example.appify;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String adress;

    // Constructor, getters, and setters
    public Customer(String name, String email, String phoneNumber, String city, String adress) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city=city;
        this.adress=adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

