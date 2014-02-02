package com.expenses.service;

import com.expenses.domain.*;
import com.expenses.exception.GroupExpenseException;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.helper.Helper;
import com.expenses.repository.ExpenseDao;
import com.expenses.repository.GroupExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.*/

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GroupExpenseServiceImpl implements GroupExpenseService {
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;
    @Autowired
    GroupExpenseDao groupExpenseDao;
    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException {
        User user;
        Group group;
        if ((user=userService.retrieveUser(ownerEmailId))==null)
            throw  new UserDoesNotExistException(ownerEmailId);
        if ((group=groupService.retrieveGroup(groupId))==null){
            throw new GroupNotFoundException(groupId);
        }
        GroupExpense groupExpense=new GroupExpense(amount,new ExpenseDetails(),user,group);
        group.addExpense(groupExpense);
        return groupExpense;
    }

    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String description) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException {
        GroupExpense groupExpense=createNewExpense(ownerEmailId,amount,groupId);
        groupExpense.getDescription().setDescription(description);
        return groupExpense;
    }

    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String description, Date date) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException {
        GroupExpense groupExpense=createNewExpense(ownerEmailId,amount,groupId, description);
        groupExpense.getDescription().setTimestamp(date);
        return groupExpense;
    }

    @Override
    public GroupExpense retrieveExpense(int expenseId) {
        GroupExpense expense=groupExpenseDao.findOne(expenseId);
        return expense;
    }
}
