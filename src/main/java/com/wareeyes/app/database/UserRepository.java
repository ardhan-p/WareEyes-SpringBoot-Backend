package com.wareeyes.app.database;

import com.wareeyes.app.userlogin.User;

public interface UserRepository {

    boolean checkLogin(User user);
    boolean checkEmail(User user);
    int resetPassword(User user);

    int insertUser(User user);
}
