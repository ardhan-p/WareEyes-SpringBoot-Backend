package com.wareeyes.app.controller;

import com.wareeyes.app.database.user.UserDriverDB;
import com.wareeyes.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserDriverDB db;

    @PostMapping("/validateLogin")
    public boolean validateLogin(@RequestBody User user) {
        boolean validate = db.checkLogin(user);

        if (validate) {
            LOGGER.info(user.getEmail() + " has logged in!");
        } else {
            LOGGER.info(user.getEmail() + " unsuccessful login!");
        }

        return validate;
    }

    @PostMapping("/validateEmail")
    public boolean validateEmail(@RequestBody User user) {
        boolean validate = db.checkEmail(user);

        if (validate) {
            LOGGER.info(user.getEmail() + " is valid");
        } else {
            LOGGER.info(user.getEmail() + " is not valid");
        }

        return validate;
    }

    @PostMapping("/updatePassword")
    public int updatePassword(@RequestBody User user){
        int validate = db.resetPassword(user);

        if (validate == 1) {
            LOGGER.info("Password " + user.getPassword() + " has changed successfully");
        } else {
            LOGGER.info("Password " + user.getPassword() + " has failed to change ");
        }

        return validate;
    }

    @GetMapping("/")
    public int accessAPI() {
        return 1;
    }
    // TODO: add api for adding users
}
