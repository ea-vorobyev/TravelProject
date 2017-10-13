package com.company.db.dao;

import com.company.classes.Administrator;
import com.company.classes.Users;
import com.company.jaxb.ConvertingJAXB;

import java.util.ArrayList;
import java.util.List;

public class AdministratorTestDrive {
    private  static AdministratorDAO administratorDAO;
    private static String fileAdministrator = "Administrator.xml";

    static  {
        administratorDAO = new AdministratorDAO();
    }

    public static void getAllFromDB() {
        try {
            List<Administrator> administratorList = administratorDAO.getAll();
            Users<Administrator> users = new Users<>();
            users.setUsersArray(administratorList);
            ConvertingJAXB.writeToXML(users, fileAdministrator);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getById(int id) {
        try {
            System.out.println(administratorDAO.getByID(id).getBirthDay());;
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllFromXML() {
        Users<Administrator> customers = new Users<>();
        customers.setUsersArray(new ArrayList<>());
        Users newAdministrators = ConvertingJAXB.readFromXML(fileAdministrator, Users.class);
        try {
            administratorDAO.insertAll(newAdministrators.getUsersArray());
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll() {
        try {
            administratorDAO.deleteAll();
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByID(int id) {
        try {
            administratorDAO.deleteByID(id);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insertAll(List<Administrator> administratorList) {
        try {
            administratorDAO.insertAll(administratorList);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Administrator administrator) {
        try {
            administratorDAO.insert(administrator);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAll(List<Administrator> customerList) {
        try {
            administratorDAO.updateAll(customerList);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Administrator administrator) {
        try {
            administratorDAO.update(administrator);
        } catch (AdministratorDAO.AdministratorDAOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Administrator administrator = new Administrator(1, "Константин", "Константинович", "Константов", "8HeepooiimmmD", "Лондон", "05.12.1975");
        Administrator administrator2 = new Administrator(2, "Игорь", "Васильевич", "Путин", "hi8888h", "Москва", "10.07.1971");
        List<Administrator> administratorList = new ArrayList<>();
        administratorList.add(administrator);
        administratorList.add(administrator2);

        //getAllFromDB();
        //getAllFromXML();
        //deleteAll();
        //deleteByID(1);
        //getById(1);

        //insertAll(administratorList);
        //insertOne(administrator);
        //updateAll(administratorList);
        //update(administrator);

    }
}
