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
}
