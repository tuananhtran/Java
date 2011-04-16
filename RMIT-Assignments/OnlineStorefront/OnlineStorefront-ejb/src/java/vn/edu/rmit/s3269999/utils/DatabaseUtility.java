package vn.edu.rmit.s3269999.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseUtility {

    public static Long getNextId(String tableName) {
        Long nextId = null;

        try {
            Connection connection = getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM " + tableName);
            ResultSet result = statement.executeQuery();



            if (result.next()) {
                nextId = new Long(result.getLong(1) + 1);
            } else {
                nextId = new Long(1);
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Cannot query data from database! Details: " + e.getMessage());
        }

        return nextId;
    }

    private static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("jdbc/cosc2465");
        } catch (NamingException e) {
            System.err.println("Cannot get locate database object! Details: " + e.getMessage());
        }
        return dataSource;
    }
}
