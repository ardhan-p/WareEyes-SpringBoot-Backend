package com.wareeyes.app.database.notification;

import com.wareeyes.app.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Not;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NotificationDriverDB {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertNotification(Notification noti) {
        String query = "INSERT INTO NOTIFICATION (message, date, time) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, noti.getMessage(), noti.getDate(), noti.getTime());
    }

    public int deleteNotifications(List<Notification> notiList) {
        String query = "DELETE FROM NOTIFICATION WHERE ID = ?";
        try {
            for (Notification noti : notiList) {
                System.out.println(noti);
                int result = jdbcTemplate.update(query, noti.getId());
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Notification> selectAllNotifications() {
        String query = "SELECT * FROM NOTIFICATION";
        List<Notification> list = jdbcTemplate.query(query, new NotificationRowMapper());
        return list;
    }

    public List<Notification> selectTopicDataByDate(String topic, String date) {
        if (date.matches("[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]")) {
            System.out.println(topic + " - " + date);
            String query = "SELECT * FROM NOTIFICATION WHERE message LIKE '%" + topic + "%' AND date LIKE '" + date + "'";
            List<Notification> list = jdbcTemplate.query(query, new NotificationRowMapper());
            for (Notification noti : list) {
                System.out.println(noti);
            }
            return list;
        } else {
            return null;
        }
    }
}
