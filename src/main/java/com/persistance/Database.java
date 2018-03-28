package com.persistance;

import java.sql.*;

public class Database {
    private final Connection connection;
    private static final String GET_QUERY = "select * from names where name = '%s'";
    private static final String INSERT_QUERY = "insert into NAMES (NAME,COUNT) values ('%s',%d)";
    private static final String UPDATE_QUERY = "update NAMES set COUNT=%d where NAME='%s'";

    public Database(Connection connection) {
        this.connection = connection;
    }

    public String save(String name) throws SQLException {
        int count = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(GET_QUERY, name));
        while (resultSet.next()) {
            count = resultSet.getInt("COUNT");
        }
        if (count == 0) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format(INSERT_QUERY, name, ++count));
            return "OK";
        } else {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format(UPDATE_QUERY, (count + 1), name));
            return name + count;
        }
    }
}
