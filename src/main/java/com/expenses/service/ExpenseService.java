package com.expenses.service;

import com.expenses.domain.Expense;
import com.expenses.exception.UserDoesNotExistException;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ExpenseService {
    Expense createNewExpense(String ownerEmailId, Double amount) throws UserDoesNotExistException;

    Expense createNewExpense(String ownerEmailId, Double amount, String description) throws UserDoesNotExistException;

    Expense createNewExpense(String ownerEmailId, Double amount, String description, Date date) throws UserDoesNotExistException;

    Expense retrieveExpense(int expenseId);

}
