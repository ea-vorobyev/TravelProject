package com.company.db.dao;

import com.company.classes.Travel;
import com.company.classes.Travels;
import com.company.jaxb.ConvertingJAXB;

import java.util.ArrayList;
import java.util.List;

public class TravelTestDrive {
    private  static TravelDAO travelDAO;
    private static String fileTravel = "Travel.xml";

    static  {
        travelDAO = new TravelDAO();
    }

    public static void getAllFromDB() {
        try {
            List<Travel> travelList = travelDAO.getAll();
            Travels travels = new Travels();
            travels.setTravelsArray(travelList);
            ConvertingJAXB.writeToXML(travels, fileTravel);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getById(int id) {
        try {
            System.out.println(travelDAO.getByID(id).getCountry());;
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllFromXML() {
        Travels travels = new Travels();
        travels.setTravelsArray(new ArrayList<>());
        Travels newTravels = ConvertingJAXB.readFromXML(fileTravel, Travels.class);
        try {
            travelDAO.insertAll(newTravels.getTravelsArray());
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAll() {
        try {
            travelDAO.deleteAll();
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByID(int id) {
        try {
            travelDAO.deleteByID(id);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insertAll(List<Travel> travelList) {
        try {
            travelDAO.insertAll(travelList);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(Travel travel) {
        try {
            travelDAO.insert(travel);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAll(List<Travel> customerList) {
        try {
            travelDAO.updateAll(customerList);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Travel travel) {
        try {
            travelDAO.update(travel);
        } catch (TravelDAO.TravelDAOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Travel travel = new Travel(1, "Англия", "Халл", "Эллеон", "всё включено", "5", 20, 1);
        Travel travel2 = new Travel(2, "Россия", "Томск", "Томск1", "всё включено", "6", 5, 1);
        List<Travel> travelList = new ArrayList<>();
        travelList.add(travel);
        travelList.add(travel2);

        //getAllFromDB();
        //getAllFromXML();
        //deleteAll();
        //deleteByID(1);
        //getById(2);

        //insertAll(travelList);
        //insert(travel);
        //updateAll(travelList);
        //update(travel);

    }
}
