package com.company.db;

import java.sql.Connection;

public interface IConnectionManager {
    Connection getConnection();
}
