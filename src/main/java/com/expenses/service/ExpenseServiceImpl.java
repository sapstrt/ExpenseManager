package com.expenses.service;

import com.expenses.domain.Expense;
import com.expenses.domain.ExpenseDetails;
import com.expenses.domain.User;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.helper.Helper;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExpenseServiceImpl implements ExpenseService {
    UserService userService;
    @Override
    public Expense createNewExpense(String ownerEmailId, Double amount) throws UserDoesNotExistException {
        User user;
        if((user=userService.retrieveUser(ownerEmailId))==null){
            throw new UserDoesNotExistException(ownerEmailId);
        }
        ExpenseDetails details=new ExpenseDetails();
        Expense expense=new Expense(Helper.generateId(),amount,details,user);
        user.addExpense(expense);
        //todo update dao
        return expense;
    }

    @Override
    public Expense createNewExpense(String ownerEmailId, Double amount, String description) throws UserDoesNotExistException {
        Expense expense=createNewExpense(ownerEmailId,amount);
        expense.getDescription().setDescription(description);
        return expense;
    }

    @Override
    public Expense createNewExpense(String ownerEmailId, Double amount, String description, Date date) throws UserDoesNotExistException {
        Expense expense=createNewExpense(ownerEmailId,amount,description);
        expense.getDescription().setTimestamp(date);
        return expense;
    }

    @Override
    public Expense retrieveExpense(int expenseId) {
        //todo dao
        return null;
    }
}
