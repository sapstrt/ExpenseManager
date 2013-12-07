package com.expenses.repository;

import com.expenses.domain.Group;
import com.expenses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 12/4/13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    UserDao dao;
    @Autowired
    GroupDao groupDao;
    @Test
    public void testCreateUSer(){
        User user=new User(2,"test","trest@test.com");
        new Group(1,"abc",user);
        System.out.print(user);
        dao.save(user);

    }
    @Test
    public void testCreateGroup(){
        User user=new User(2,"test","trest@test.com");
        Group group=new Group(1,"abc",user);
        System.out.print(user);
        groupDao.save(group);

    }
}
