package com.wareeyes.app.database.data;

import com.wareeyes.app.database.user.UserRowMapper;
import com.wareeyes.app.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Data getData(int value) {
        String query = "SELECT * FROM DATA WHERE data = ?";
        Data data = jdbcTemplate.queryForObject(query, new DataRowMapper(), value);
        return data;
    }
}
