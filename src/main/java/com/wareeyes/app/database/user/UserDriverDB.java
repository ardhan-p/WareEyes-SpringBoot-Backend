package com.wareeyes.app.database.user;

import com.wareeyes.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean checkLogin(User user) {
        String query = "SELECT * FROM USER WHERE email = ?";
        User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail());
        return dbUser.getPassword().equals(user.getPassword());
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
        return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getPassword(), user.isAdmin());
    }
}
