package com.company.db.dao;

public class Main {

    public static void deleteDB() {
        TravelTestDrive.deleteAll();
        CustomerTestDrive.deleteAll();
        AdministratorTestDrive.deleteAll();
    }

    public static void getFromDB() {
        CustomerTestDrive.getAllFromDB();
        AdministratorTestDrive.getAllFromDB();
        TravelTestDrive.getAllFromDB();
    }

    public static void getFromXML() {
        CustomerTestDrive.getAllFromXML();
        AdministratorTestDrive.getAllFromXML();
        TravelTestDrive.getAllFromXML();
    }

    public static void main(String[] args) {
        System.out.println("!");
        //deleteDB();
        //getFromDB();
        //getFromXML();
    }

}
