package com.wareeyes.app.database.data;

import com.wareeyes.app.entity.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// database driver class for accessing the data table
@Repository
public class DataDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // SQL query to get specific data object where value corresponds to the data row
    public Data getData(int value) {
        String query = "SELECT * FROM DATA WHERE data = ?";
        Data data = jdbcTemplate.queryForObject(query, new DataRowMapper(), value);
        return data;
    }
}
