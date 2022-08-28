package com.wareeyes.app.database.data;

import com.wareeyes.app.entity.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// maps the SQL data row into a workable Data object
public class DataRowMapper implements RowMapper<Data> {
    @Override
    public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
        Data data = new Data();

        data.setId(rs.getInt("id"));
        data.setData(rs.getInt("data"));

        return data;
    }
}
