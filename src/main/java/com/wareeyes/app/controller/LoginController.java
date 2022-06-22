package com.wareeyes.app.controller;

import com.wareeyes.app.database.UserDriverDB;
import com.wareeyes.app.userlogin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserDriverDB sql;

    @GetMapping("/validate")
    public boolean validateLogin(@RequestBody User user) {
        boolean validate = sql.checkLogin(user);

        if (validate) {
            LOGGER.info(user.getEmail() + " has logged in!");
        } else {
            LOGGER.info(user.getEmail() + " unsuccessful login!");
        }

        return validate;
    }

    // TODO: add api for adding users
}
