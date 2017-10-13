package com.company.classes;

import com.company.jaxb.ConvertingJAXB;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Customer customerOne = new Customer(1,"Евгений", "Александрович","Воробьев", "qwerty123", "89131064143", "Томск", "18.04.1988");
        Customer customerTwo = new Customer(2, "Артем", "Александрович", "Комельков", "yttiJl66", "89131080245", "Томск", "13.04.1988");
        Users<Customer> customers = new Users<>();
        customers.setUsersArray(new ArrayList<>());

        customers.getUsersArray().add(customerOne);
        customers.getUsersArray().add(customerTwo);

        String fileCustomers = "Customer.xml";
        ConvertingJAXB.writeToXML(customers, fileCustomers);
        Users newCustomers = ConvertingJAXB.readFromXML(fileCustomers, Users.class);

        //===========================================================================

        Administrator administrator = new Administrator(1, "Сергей", "Александрович","Шкуратько", "l8fljfdjfdjfdjfofgorig", "Самара", "01.07.1990");
        Users<Administrator> administrators = new Users<>();
        administrators.setUsersArray(new ArrayList<>());
        administrators.getUsersArray().add(administrator);
        String fileAdmin = "Administrator.xml";
        ConvertingJAXB.writeToXML(administrators, fileAdmin);
        Users newAdministrators = ConvertingJAXB.readFromXML(fileAdmin, Users.class);

        //=============================================================================

        Travel travel1 = new Travel(1, "Англия", "Лондон", "5", "всё включено", "7", 15, 20);
        Travel travel2 = new Travel(2,"Испания", "Мадрид", "4", "всё включено", "5", 20, 25);
        Travel travel3 = new Travel(3, "Италия", "Рим", "4", "всё включено", "7", 10, 13);
        Travel travel4 = new Travel(4, "Англия", "Манчестер", "5", "всё включено", "8", 5, 12);
        Travels travels = new Travels();
        travels.setTravelsArray(new ArrayList<>());
        travels.getTravelsArray().add(travel1);
        travels.getTravelsArray().add(travel2);
        travels.getTravelsArray().add(travel3);
        travels.getTravelsArray().add(travel4);
        String fileTravels = "Travel.xml";
        ConvertingJAXB.writeToXML(travels, fileTravels);
        Travels newTravels = ConvertingJAXB.readFromXML(fileTravels, Travels.class);

    }

}
