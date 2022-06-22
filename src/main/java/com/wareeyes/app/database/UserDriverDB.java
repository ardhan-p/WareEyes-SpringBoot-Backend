package com.wareeyes.app.database;

import com.wareeyes.app.userlogin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDriverDB implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean checkLogin(User user) {
        String query = "SELECT * FROM USER WHERE email = ?";
        User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail());
        return dbUser.getPassword().equals(user.getPassword());
    }

    @Override
    public boolean checkEmail(User user) {
        String query = "SELECT * FROM USER WHERE email = ?";
        User dbUser = jdbcTemplate.queryForObject(query, new UserRowMapper(), user.getEmail());
        return dbUser.getEmail().equals(user.getEmail());
    }

    @Override
    public int insertUser(User user) {
        String query = "INSERT INTO USER (email, name, password, isAdmin) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(query, user.getEmail(), user.getName(), user.getPassword(), user.isAdmin());
    }
}
