package com.expenses.service;

import com.expenses.domain.Expense;
import com.expenses.domain.GroupExpense;
import com.expenses.exception.GroupExpenseException;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserDoesNotExistException;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.*/


public interface GroupExpenseService {
    GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException;

    GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String Description) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException;

    GroupExpense createNewExpense(String ownerEmailId, Double amount, int groupId, String Description, Date date) throws UserDoesNotExistException, GroupNotFoundException, GroupExpenseException;

    GroupExpense retrieveExpense(int expenseId);


}
