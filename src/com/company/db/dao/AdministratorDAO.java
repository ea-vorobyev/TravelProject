package com.company.db.dao;

import com.company.classes.Administrator;
import com.company.classes.Customer;
import com.company.db.ConnectionManagerPostgresSQL;
import com.company.db.IConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO implements IAbstractDAO<Administrator> {

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgresSQL.getInstance();
    }

    static class AdministratorDAOException extends Exception {

    }

    private PreparedStatement getUpdateStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "UPDATE administrator SET first_name = ?, last_name = ?, family_name = ?, password = ?, town = ?, birth_day = ? WHERE admin_id = ? ");
    }

    private PreparedStatement getInsertStatement() throws SQLException {
        return manager.getConnection().prepareStatement(
                "INSERT INTO administrator(admin_id, first_name, last_name, family_name, password, town, birth_day) VALUES(?, ?, ?, ?, ?, ?, ?)");
    }


    @Override
    public List<Administrator> getAll() throws AdministratorDAOException {
        List<Administrator> administratorList = new ArrayList<>();
        Statement statement = null;

        try {
            statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM administrator");
            while (resultSet.next()) {
                Administrator administrator = new Administrator(
                        resultSet.getInt("admin_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("family_name"),
                        resultSet.getString("password"),
                        resultSet.getString("town"),
                        resultSet.getString("birth_day"));
                administratorList.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
        return administratorList;
    }

    @Override
    public Administrator getByID(int id) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement("SELECT * FROM administrator WHERE admin_id = ? ");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Administrator(
                    resultSet.getInt("admin_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("family_name"),
                    resultSet.getString("password"),
                    resultSet.getString("town"),
                    resultSet.getString("birth_day"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }


    @Override
    public void updateAll(List<Administrator> administratorList) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            for (Administrator administrator: administratorList) {
                statement.setString(1, administrator.getFirstName());
                statement.setString(2, administrator.getLastName());
                statement.setString(3, administrator.getFamilyName());
                statement.setString(4, administrator.getPassword());
                statement.setString(5, administrator.getTown());
                statement.setString(6, administrator.getBirthDay());
                statement.setInt(7, administrator.getAdmin_id());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }

    @Override
    public void update(Administrator administrator) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = getUpdateStatement();
            statement.setString(1, administrator.getFirstName());
            statement.setString(2, administrator.getLastName());
            statement.setString(3, administrator.getFamilyName());
            statement.setString(4, administrator.getPassword());
            statement.setString(5, administrator.getTown());
            statement.setString(6, administrator.getBirthDay());
            statement.setInt(7, administrator.getAdmin_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }


    @Override
    public void deleteAll() throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM administrator");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }

    @Override
    public void deleteByID(int id) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection().prepareStatement(
                    "DELETE FROM administrator WHERE admin_id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }


    @Override
    public void insertAll(List<Administrator> administratorList) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            for (Administrator administrator : administratorList) {
                statement.setInt(1, administrator.getAdmin_id());
                statement.setString(2, administrator.getFirstName());
                statement.setString(3, administrator.getLastName());
                statement.setString(4, administrator.getFamilyName());
                statement.setString(5, administrator.getPassword());
                statement.setString(6, administrator.getTown());
                statement.setString(7, administrator.getBirthDay());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }

    @Override
    public void insert(Administrator administrator) throws AdministratorDAOException {
        PreparedStatement statement = null;
        try {
            statement = getInsertStatement();
            statement.setInt(1, administrator.getAdmin_id());
            statement.setString(2, administrator.getFirstName());
            statement.setString(3, administrator.getLastName());
            statement.setString(4, administrator.getFamilyName());
            statement.setString(5, administrator.getPassword());
            statement.setString(6, administrator.getTown());
            statement.setString(7, administrator.getBirthDay());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AdministratorDAOException();
        }
    }

}
