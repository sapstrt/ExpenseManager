/*
package com.expenses.service;

import com.expenses.domain.ExpenseDetails;
import com.expenses.domain.Group;
import com.expenses.domain.GroupExpense;
import com.expenses.domain.User;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.helper.Helper;

import java.util.Date;

*/
/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 *//*

public class GroupExpenseServiceImpl implements GroupExpenseService {
    UserService userService;
    GroupService groupService;
    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId) throws UserDoesNotExistException, GroupNotFoundException {
        User user;
        Group group;
        if ((user=userService.retrieveUser(ownerEmailId))==null)
            throw  new UserDoesNotExistException(ownerEmailId);
        if ((group=groupService.retrieveGroup(groupId))==null){
            throw new GroupNotFoundException(groupId);
        }
        GroupExpense groupExpense=new GroupExpense(Helper.generateId(),amount,new ExpenseDetails(),user,group);
        group.addExpense(groupExpense);
        //todo dao
        return groupExpense;
    }

    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String description) throws UserDoesNotExistException, GroupNotFoundException {
        GroupExpense groupExpense=createNewExpense(ownerEmailId,amount,groupId);
        groupExpense.getDescription().setDescription(description);
        return groupExpense;
    }

    @Override
    public GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String description, Date date) throws UserDoesNotExistException, GroupNotFoundException {
        GroupExpense groupExpense=createNewExpense(ownerEmailId,amount,groupId, description);
        groupExpense.getDescription().setTimestamp(date);
        return groupExpense;
    }

    @Override
    public GroupExpense retrieveExpense(int expenseId) {
        //todo
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
*/
