package com.company.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlSeeAlso({Customer.class, Administrator.class})
abstract class User {
    private String firstName;
    private String lastName;
    private String familyName;
    private String password;
    private String town;
    private String birthDay;

    public User() {
    }

    public User(String firstName, String lastName, String familyName, String password, String town, String birthDay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.familyName = familyName;
        this.password = password;
        this.town = town;
        this.birthDay = birthDay;
    }

    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFamilyName() {
        return familyName;
    }

    @XmlElement
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTown() {
        return town;
    }

    @XmlElement
    public void setTown(String town) {
        this.town = town;
    }

    public String getBirthDay() {
        return birthDay;
    }

    @XmlElement
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
