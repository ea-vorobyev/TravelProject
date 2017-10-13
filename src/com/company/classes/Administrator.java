package com.company.classes;

import javax.xml.bind.annotation.XmlElement;

public class Administrator extends User {
    private int admin_id;

    public Administrator() {

    }

    public Administrator(int admin_id, String firstName, String lastName, String familyName, String password, String town,String birthDay) {
        super(firstName, lastName, familyName, password, town, birthDay);
        this.admin_id = admin_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    @XmlElement
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}
