package com.wareeyes.app.controller;

import com.wareeyes.app.database.notification.NotificationDriverDB;
import com.wareeyes.app.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    private final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationDriverDB db;

    @GetMapping("/get")
    public List<Notification> getNotifications() {
        return db.selectAllNotifications();
    }

    @PostMapping("/post")
    public int addNotification(@RequestBody Notification noti) {
        int insert = db.insertNotification(noti);

        if (insert == 1) {
            LOGGER.info("Notification has been inserted successfully!");
        } else {
            LOGGER.info("Notification cannot be inserted");
        }

        return insert;
    }

    @PostMapping("/fetchTopicData")
    public List<Notification> fetchTopicData(@RequestBody Notification noti) {
        return db.selectTopicDataByDate(noti.getMessage(), noti.getDate());
    }

    @PostMapping("/delete")
    public int deleteNotifications(@RequestBody List<Notification> notiList) {
        int result = db.deleteNotifications(notiList);
        return result;
    }
}
