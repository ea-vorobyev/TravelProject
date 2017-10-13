package com.company.db.dao;

import com.company.classes.Customer;
import com.company.db.ConnectionManagerPostgresSQL;
import com.company.db.IConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements IAbstractDAO<Customer> {

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    static class CustomerDAOException extends Exception {

    }

    private PreparedStatement getUpdateStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "UPDATE customer SET first_name = ?, last_name = ?, family_name = ?, password = ?, contact_number = ?, town = ?, birth_day = ? WHERE customer_id = ? ");
    }

    private PreparedStatement getInsertStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "INSERT INTO customer(customer_id, first_name, last_name, family_name, password, contact_number, town, birth_day) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
    }


    @Override
    public List<Customer> getAll() throws CustomerDAOException {
        List<Customer> customerList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("family_name"),
                        resultSet.getString("password"),
                        resultSet.getString("contact_number"),
                        resultSet.getString("town"),
                        resultSet.getString("birth_day"));
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
        return customerList;
    }

    @Override
    public Customer getByID(int id) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement("SELECT * FROM customer WHERE customer_id = ? ");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Customer(
                    resultSet.getInt("customer_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("family_name"),
                    resultSet.getString("password"),
                    resultSet.getString("contact_number"),
                    resultSet.getString("town"),
                    resultSet.getString("birth_day"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }


    @Override
    public void updateAll(List<Customer> customerList) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            for (Customer customer: customerList) {
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getFamilyName());
                statement.setString(4, customer.getPassword());
                statement.setString(5, customer.getContactNumber());
                statement.setString(6, customer.getTown());
                statement.setString(7, customer.getBirthDay());
                statement.setInt(8, customer.getCustomer_id());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }

    @Override
    public void update(Customer customer) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getFamilyName());
            statement.setString(4, customer.getPassword());
            statement.setString(5, customer.getContactNumber());
            statement.setString(6, customer.getTown());
            statement.setString(7, customer.getBirthDay());
            statement.setInt(8, customer.getCustomer_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }


    @Override
    public void deleteAll() throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM customer");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }

    @Override
    public void deleteByID(int id) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM customer WHERE customer_id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }


    @Override
    public void insertAll(List<Customer> customerList) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            for (Customer customer : customerList) {
                statement.setInt(1, customer.getCustomer_id());
                statement.setString(2, customer.getFirstName());
                statement.setString(3, customer.getLastName());
                statement.setString(4, customer.getFamilyName());
                statement.setString(5, customer.getPassword());
                statement.setString(6, customer.getContactNumber());
                statement.setString(7, customer.getTown());
                statement.setString(8, customer.getBirthDay());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }

    @Override
    public void insert(Customer customer) throws CustomerDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            statement.setInt(1, customer.getCustomer_id());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getFamilyName());
            statement.setString(5, customer.getPassword());
            statement.setString(6, customer.getContactNumber());
            statement.setString(7, customer.getTown());
            statement.setString(8, customer.getBirthDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CustomerDAOException();
        }
    }

}
