package com.wareeyes.app.database.notification;

import com.wareeyes.app.entity.Notification;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// maps the SQL data row into a workable Notification object
public class NotificationRowMapper implements RowMapper<Notification> {

    @Override
    public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
        Notification noti = new Notification();

        noti.setId(rs.getInt("id"));
        noti.setMessage(rs.getString("message"));
        noti.setDate(rs.getString("date"));
        noti.setTime(rs.getString("time"));

        return noti;
    }
}
