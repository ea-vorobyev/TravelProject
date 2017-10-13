package com.company.db.dao;

import com.company.classes.Travel;
import com.company.db.ConnectionManagerPostgresSQL;
import com.company.db.IConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO implements IAbstractDAO<Travel> {

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    static class TravelDAOException extends Exception {

    }

    private PreparedStatement getUpdateStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "UPDATE travel SET country = ?, town = ?, level_hotel = ?, food = ?, rating = ?, quantity = ?, customer = ? WHERE travel_id = ? ");
    }

    private PreparedStatement getInsertStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "INSERT INTO travel(travel_id, country, town, level_hotel, food, rating, quantity, customer) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
    }


    @Override
    public List<Travel> getAll() throws TravelDAOException {
        List<Travel> travelList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM travel");
            while (resultSet.next()) {
                Travel travel = new Travel(
                        resultSet.getInt("travel_id"),
                        resultSet.getString("country"),
                        resultSet.getString("town"),
                        resultSet.getString("level_hotel"),
                        resultSet.getString("food"),
                        resultSet.getString("rating"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("customer"));
                travelList.add(travel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
        return travelList;
    }

    @Override
    public Travel getByID(int id) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement("SELECT * FROM travel WHERE travel_id = ? ");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Travel(
                    resultSet.getInt("travel_id"),
                    resultSet.getString("country"),
                    resultSet.getString("town"),
                    resultSet.getString("level_hotel"),
                    resultSet.getString("food"),
                    resultSet.getString("rating"),
                    resultSet.getInt("quantity"),
                    resultSet.getInt("customer"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }


    @Override
    public void updateAll(List<Travel> travelList) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            for (Travel travel: travelList) {
                statement.setString(1, travel.getCountry());
                statement.setString(2, travel.getTown());
                statement.setString(3, travel.getLevelHotel());
                statement.setString(4, travel.getFood());
                statement.setString(5, travel.getRating());
                statement.setInt(6, travel.getQuantity());
                statement.setInt(7, travel.getCustomer());
                statement.setInt(8, travel.getTravel_id());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }

    @Override
    public void update(Travel travel) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            statement.setString(1, travel.getCountry());
            statement.setString(2, travel.getTown());
            statement.setString(3, travel.getLevelHotel());
            statement.setString(4, travel.getFood());
            statement.setString(5, travel.getRating());
            statement.setInt(6, travel.getQuantity());
            statement.setInt(7, travel.getCustomer());
            statement.setInt(8, travel.getTravel_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }


    @Override
    public void deleteAll() throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM travel");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }

    @Override
    public void deleteByID(int id) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM travel WHERE travel_id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }


    @Override
    public void insertAll(List<Travel> travelList) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            for (Travel travel : travelList) {
                statement.setInt(1, travel.getTravel_id());
                statement.setString(2, travel.getCountry());
                statement.setString(3, travel.getTown());
                statement.setString(4, travel.getLevelHotel());
                statement.setString(5, travel.getFood());
                statement.setString(6, travel.getRating());
                statement.setInt(7, travel.getQuantity());
                statement.setInt(8, travel.getCustomer());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }

    @Override
    public void insert(Travel travel) throws TravelDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            statement.setInt(1, travel.getTravel_id());
            statement.setString(2, travel.getCountry());
            statement.setString(3, travel.getTown());
            statement.setString(4, travel.getLevelHotel());
            statement.setString(5, travel.getFood());
            statement.setString(6, travel.getRating());
            statement.setInt(7, travel.getQuantity());
            statement.setInt(8, travel.getCustomer());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new TravelDAOException();
        }
    }

}
