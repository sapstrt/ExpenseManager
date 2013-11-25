package com.expenses.service;

import com.expenses.domain.User;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 9:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    User createNewUser(String userName, String emailId);

    User retrieveUser(String emailId);


}
