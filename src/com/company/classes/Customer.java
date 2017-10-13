package com.company.classes;

import javax.xml.bind.annotation.XmlElement;

public class Customer extends User {
    private int customer_id;
    private String contactNumber;

    public Customer() {
    }

    public Customer(int customer_id, String firstName, String lastName, String familyName, String password, String contactNumber, String town, String birthDay) {
        super(firstName, lastName, familyName, password, town, birthDay);
        this.customer_id = customer_id;
        this.contactNumber = contactNumber;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    @XmlElement
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @XmlElement
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

}
