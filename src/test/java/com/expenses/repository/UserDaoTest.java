package com.expenses.repository;

import com.expenses.domain.*;
import com.expenses.exception.GroupExpenseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        User user=new User("test","trest@test.com");
        new Group("abc",user);
        ExpenseDetails expenseDetails=new ExpenseDetails();
        expenseDetails.setDescription("this is fun if it works");
        Expense expense=new Expense(20.0,expenseDetails,user);
        user.addExpense(expense);
        System.out.print(user);
        dao.save(user);
        List<User> users=dao.findAll();
        for (User user1:users){
            if (user1.getUserName().equals("test")){
                System.err.println("user found");

            }
        }

    }
    @Test
    @Transactional
    public void testGroupExpenses(){
         List<User> users =dao.findAll();
        for (User user:users){
            if(user.getUserName().equals("test")){
                System.err.println("user found");
                Group group=new Group("tester",user);
                ExpenseDetails details=new ExpenseDetails();
                GroupExpense expense= null;
                try {
                    expense = new GroupExpense(20.0,details,user,group);
                    group.addExpense(expense);
                } catch (GroupExpenseException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
            System.err.println("user "+user);

        }
    }
    @Test
    public void testCreateGroup(){
        User user=new User("test","trest@test.com");
        Group group=new Group("abc",user);
        System.out.print(user);
        groupDao.save(group);

    }
}
