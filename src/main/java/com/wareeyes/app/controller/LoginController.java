package com.wareeyes.app.controller;

import com.wareeyes.app.database.UserDriverDB;
import com.wareeyes.app.userlogin.User;
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
    UserDriverDB sql;

    @PostMapping("/validateLogin")
    public boolean validateLogin(@RequestBody User user) {
        boolean validate = sql.checkLogin(user);

        if (validate) {
            LOGGER.info(user.getEmail() + " has logged in!");
        } else {
            LOGGER.info(user.getEmail() + " unsuccessful login!");
        }

        return validate;
    }

    @PostMapping("/validateEmail")
    public boolean validateEmail(@RequestBody User user) {
        boolean validate = sql.checkEmail(user);

        if (validate) {
            LOGGER.info(user.getEmail() + " is valid");
        } else {
            LOGGER.info(user.getEmail() + " is not valid");
        }

        return validate;
    }

    // TODO: add api for adding users
}
