package com.wareeyes.app.controller;

import com.wareeyes.app.database.notification.NotificationDriverDB;
import com.wareeyes.app.entity.MessageEvent;
import com.wareeyes.app.entity.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller class which contains all the RESTful APIs that involves the notification system
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

    @GetMapping("/fetchTopicData/{topic}/{date}")
    public List<MessageEvent> fetchTopicData(@PathVariable String topic, @PathVariable String date) {
        return db.selectTopicDataByDate(topic, date);
    }

    @PostMapping("/delete")
    public int deleteNotifications(@RequestBody List<Notification> notiList) {
        int result = db.deleteNotifications(notiList);
        return result;
    }
}
