package com.expenses.repository;

import com.expenses.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/28/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */

public interface UserDao extends JpaRepository<User,Integer>{
    public List<User> findByUserName(String userName);
    public User findByEmail(String email);

}
