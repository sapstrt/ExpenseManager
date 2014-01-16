package com.expenses.service;

import com.expenses.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User createNewUser(String userName, String emailId) {
        if (validateNewUserCredentials(userName,emailId)){
            //todo: check if user id exists
            User user=new User(userName,emailId);
            //todo : userDao
            return user;
        }
        return null;
    }

    private boolean validateNewUserCredentials(String userName, String emailId) {
        return retrieveUser(emailId)==null?true:false;

    }

    @Override
    public User retrieveUser(String emailId) {
        //todo : implement method
        return null;
    }
}
