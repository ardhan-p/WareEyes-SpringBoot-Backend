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

    public User checkLogin(User user) {
        String query = "SELECT * FROM USER WHERE email = ? AND isAdmin = ?";
        try {
            User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail(), user.isAdmin());
            System.out.println(dbUser);
            if (dbUser.getPassword().equals(user.getPassword())) {
                dbUser.setPassword("");
                return dbUser;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Caught exception");
            System.out.println(user);
            return null;
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

    public int updateUser(User user) {
        String query = "UPDATE USER SET email = ?, name = ?, password = ? WHERE id = ?";

        try {
            return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getPassword(), user.getId());
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

    public User getUser(String id) {
        String query = "SELECT * FROM USER WHERE id = ?";
        try {
            User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
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
