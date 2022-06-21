package com.wareeyes.app.database;

import com.wareeyes.app.kafka.KafkaConsumer;
import com.wareeyes.app.userlogin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseDriver {
    private final Logger LOGGER = LoggerFactory.getLogger(UserDatabaseDriver.class);

    public boolean checkLogin(User user) {
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement prepStmnt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM USER WHERE email = ?";
            dataSource = ConfigDataSource.source();
            connection = dataSource.getConnection();
            prepStmnt = connection.prepareStatement(query);

            // set email
            prepStmnt.setString(1, user.getEmail());
            rs = prepStmnt.executeQuery();

            User dbUser;

            if (!rs.next()) {
                LOGGER.info(user.getEmail() + ": invalid login!");
                return false;
            } else {
                do {
                    dbUser = new User(rs.getString("email"), rs.getString("password"), rs.getBoolean("isAdmin"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                } while (rs.next());
            }

            // compares inputted password with hashed password
            if (user.getPassword().equals(dbUser.getPassword())) {
                LOGGER.info(user.getEmail() + ": valid login!");
                return true;
            } else {
                LOGGER.info(user.getEmail() + ": invalid login!");
                return false;
            }

        } catch (SQLException e) {
            LOGGER.info("Unable to check user!");
            e.printStackTrace();
        }

        return false;
    }

    public int insertUser(User user) {
        DataSource dataSource = null;
        Connection connection = null;
        PreparedStatement prepStmnt = null;

        int result = 0;

        try {
            String query = "INSERT INTO USER (email, password, isAdmin) VALUES (?, ?, ?)";
            dataSource = ConfigDataSource.source();
            connection = dataSource.getConnection();
            prepStmnt = connection.prepareStatement(query);

            // set email
            prepStmnt.setString(1, user.getEmail());

            // set password
            prepStmnt.setString(2, user.getPassword());

            // set role
            prepStmnt.setBoolean(3, user.isAdmin());

            result = prepStmnt.executeUpdate();
            LOGGER.info("Successfully inserted user!");
        } catch (SQLException e) {
            LOGGER.info("Unable to insert user!");
            e.printStackTrace();
        }

        return result;
    }
}
