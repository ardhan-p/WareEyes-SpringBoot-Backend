package com.wareeyes.app.controller;

import com.wareeyes.app.database.user.UserDriverDB;
import com.wareeyes.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controller class which contains all the RESTful APIs that involves user objects and logins
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    // database driver class is autowired to access database methods
    @Autowired
    UserDriverDB db;

    // validates the requested user object for login purposes
    @PostMapping("/validateLogin")
    public User validateLogin(@RequestBody User user) {
        User validationUser = db.checkLogin(user);

        if (validationUser == null) {
            LOGGER.info(user.getEmail() + " is invalid!");
            return null;
        } else {
            LOGGER.info(user.getEmail() + " has logged in!");
            return validationUser;
        }
    }

    // validates the requested user object to check if
    // requested email value exists in the database
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

    // updates password of a user from the newly requested user object
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

    // returns a list of all available user objects in database
    @GetMapping("/getUser")
    public List<User> getAllUsers() {
        return db.selectAllUsers();
    }

    // gets a specific user object from their id value
    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable String id) {
        User searchedUser = db.getUser(id);
        return searchedUser;
    }

    // inserts a new user into the database from the requested user object
    @PostMapping("/addUser")
    public int addUser(@RequestBody User user) {
        int result = db.insertUser(user);

        if (result == 1) {
            LOGGER.info("User " + user.getEmail() + " has been added successfully");
        } else {
            LOGGER.info("User " + user.getEmail() + " cannot be added, already exists");
        }

        return result;
    }

    // updates current user in the database from the requested user object
    @PostMapping("/updateUser")
    public int updateUser(@RequestBody User user) {
        int result = db.updateUser(user);

        if (result == 1) {
            LOGGER.info("User " + user.getEmail() + " has been updated successfully");
        } else {
            LOGGER.info("User " + user.getEmail() + " cannot be updated");
        }

        return result;
    }

    // deletes users from a requested list of user objects
    @PostMapping("/deleteUsers")
    public int deleteUser(@RequestBody List<User> userList) {
        int result = db.deleteUsers(userList);
        return result;
    }
}
