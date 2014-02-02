package com.expenses.service;

import com.expenses.domain.User;
import com.expenses.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User createNewUser(String userName, String emailId) {
        if (validateNewUserCredentials(userName,emailId)){
            User user=new User(userName,emailId);
            user=userDao.save(user);
            sendVerificationMail(user);
            return user;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User retrieveUser(String emailId) {
        User user=userDao.findByEmail(emailId);
        return user;
    }

    public boolean activateUser(User user){
        //todo
        user.setActive(true);
        return true;
    }

    private void sendVerificationMail(User user) {
        //todo
    }

    private boolean validateNewUserCredentials(String userName, String emailId) {
        return retrieveUser(emailId) == null;

    }
}
