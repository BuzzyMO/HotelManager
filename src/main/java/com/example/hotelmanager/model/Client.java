package com.example.hotelmanager.model;

public class Client {

    private String numPass;
    private String firstName;
    private String lastName;

    public String getNumPass() {
        return numPass;
    }

    public void setNumPass(String numPass) {
        this.numPass = numPass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numPass=" + numPass +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
