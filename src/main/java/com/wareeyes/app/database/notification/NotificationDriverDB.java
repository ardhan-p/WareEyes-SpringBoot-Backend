package com.wareeyes.app.database.notification;

import com.wareeyes.app.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertNotification(Notification noti) {
        String query = "INSERT INTO NOTIFICATION (message, date, time)";
        return jdbcTemplate.update(query, noti.getMessage(), noti.getDate(), noti.getTime());
    }

    public List<Notification> selectAllNotifications() {
        String query = "SELECT * FROM NOTIFICATION";
        List<Notification> list = jdbcTemplate.query(query, new NotificationRowMapper());
        return list;
    }
}
