package com.company.db.dao;

import com.company.classes.Customer;
import com.company.classes.Users;
import com.company.jaxb.ConvertingJAXB;

import java.util.ArrayList;
import java.util.List;

public class CustomerTestDrive {
    private  static CustomerDAO customerDAO;
    private static String fileCustomers = "Customer.xml";

    static  {
        customerDAO = new CustomerDAO();
    }

    public static void getAllFromDB() {
        try {
            List<Customer> customer = customerDAO.getAll();
            Users<Customer> users = new Users<>();
            users.setUsersArray(customer);
            ConvertingJAXB.writeToXML(users, fileCustomers);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getById(int id) {
        try {
            System.out.println(customerDAO.getByID(id).getBirthDay());;
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllFromXML() {
        Users<Customer> customers = new Users<>();
        customers.setUsersArray(new ArrayList<>());
        Users newCustomers = ConvertingJAXB.readFromXML(fileCustomers, Users.class);
        try {
            customerDAO.insertAll(newCustomers.getUsersArray());
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll() {
        try {
            customerDAO.deleteAll();
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByID(int id) {
        try {
            customerDAO.deleteByID(id);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insertAll(List<Customer> customerList) {
        try {
            customerDAO.insertAll(customerList);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Customer customer) {
        try {
            customerDAO.insert(customer);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAll(List<Customer> customerList) {
        try {
            customerDAO.updateAll(customerList);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Customer customer) {
        try {
            customerDAO.update(customer);
        } catch (CustomerDAO.CustomerDAOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Customer customer = new Customer(1, "Дмитрий", "Петрович", "Мешков", "333333333hiiuh", "555666", "Лондон", "05.12.1975");
        Customer customer1 = new Customer(2, "Владимир", "Васильевич", "Путин", "hi8888h", "5555555666", "Москва", "10.07.1971");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        //getAllFromDB();
        //getAllFromXML();
        //deleteAll();
        //deleteByID(5);
        //getById(4);

        //updateAll(customerList);
        //update(customer);
        //insert(customer1);
        //insertAll(customerList);

    }
}
