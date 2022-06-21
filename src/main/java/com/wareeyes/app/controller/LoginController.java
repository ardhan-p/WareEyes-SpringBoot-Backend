package com.wareeyes.app.controller;

import com.wareeyes.app.database.UserDatabaseDriver;
import com.wareeyes.app.userlogin.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @GetMapping("/validate")
    public boolean validateLogin(@RequestBody User user) {
        UserDatabaseDriver sql = new UserDatabaseDriver();
        return sql.checkLogin(user);
    }
}
