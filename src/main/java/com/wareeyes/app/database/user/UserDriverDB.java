package com.wareeyes.app.database.user;

import com.wareeyes.app.database.notification.NotificationRowMapper;
import com.wareeyes.app.entity.Notification;
import com.wareeyes.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkLogin(User user) {
        String query = "SELECT * FROM USER WHERE email = ? AND isAdmin = ?";
        try {
            User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail(), user.isAdmin());
            System.out.println(user);
            return dbUser.getPassword().equals(user.getPassword());
        } catch (Exception e) {
            System.out.println("Caught exception");
            System.out.println(user);
            return false;
        }
    }

    public boolean checkEmail(User user) {
        String query = "SELECT * FROM USER WHERE email = ?";
        User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail());
        return dbUser.getEmail().equals(user.getEmail());
    }

    public int resetPassword(User user) {
        String query = "UPDATE USER SET password = ? WHERE email = ?";
        return jdbcTemplate.update(query, user.getPassword(), user.getEmail());
    }

    public int insertUser(User user) {
        String query = "INSERT INTO USER (email, name, password, isAdmin) VALUES (?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getPassword(), user.isAdmin());
        } catch (Exception e){
            return 0;
        }
    }

    public int deleteUsers(List<User> userList) {
        String query = "DELETE FROM USER WHERE email = ?";
        try {
            for (User user : userList) {
                System.out.println(user);
                int result = jdbcTemplate.update(query, user.getEmail());
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public User getUser(String email) {
        String query = "SELECT * FROM USER WHERE email = ?";
        try {
            User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), email);
            dbUser.setPassword("");
            return dbUser;
        } catch (Exception e) {
            return new User();
        }
    }

    public List<User> selectAllUsers() {
        String query = "SELECT * FROM USER";
        List<User> list = jdbcTemplate.query(query, new UserRowMapper());

        for (User u : list) {
            u.setPassword("");
        }

        return list;
    }
}
