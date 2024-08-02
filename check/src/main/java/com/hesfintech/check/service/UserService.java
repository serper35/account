package com.hesfintech.check.service;

import com.hesfintech.check.model.User;

public interface UserService {
    User create(User user);

    void changePassword(User user, String currentPassword, String newPassword);

    User getUserById(int id);

    User getUserByLogin(String login);

    User getCurrentUser();
}
