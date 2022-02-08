/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataAccess.UserDB;
import Models.User;

/**
 *
 * @author 835489
 */
public class AccountService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        try {
            User user = userDB.getUserByEmail(email);
            System.out.println(user);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
