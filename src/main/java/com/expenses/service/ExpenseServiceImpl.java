package com.expenses.service;

import com.expenses.domain.Expense;
import com.expenses.domain.ExpenseDetails;
import com.expenses.domain.User;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.helper.Helper;
import com.expenses.repository.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.*/

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    UserService userService;
    @Autowired
    ExpenseDao expenseDao;
    @Override
    public Expense createNewExpense(String ownerEmailId, Double amount) throws UserDoesNotExistException {
        User user;
        if((user=userService.retrieveUser(ownerEmailId))==null){
            throw new UserDoesNotExistException(ownerEmailId);
        }
        ExpenseDetails details=new ExpenseDetails();
        Expense expense=new Expense(amount,details,user);
        user.addExpense(expense);
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
        Expense expense=expenseDao.findOne(expenseId);
        return expense;
    }

    @Override
    public List<Expense> retrieveAllExpenses(String email) {
        List<Expense>expenses= userService.retrieveUser(email).getExpenses();
        System.out.println(expenses);//todo lazy initialization forces this line. Find a solution
        return expenses;
    }

    public Expense createNewExpense(Expense expense) throws Exception, UserDoesNotExistException {
        if (expense==null||expense.getAmount()==0.0 || expense.getAmount()==null||expense.getExpenseOwner()==null || expense.getExpenseOwner().getEmail()==null)
            throw new Exception();
        else if(expense.getDescription()==null || expense.getDescription().equals(new ExpenseDetails())){
            expense=createNewExpense(expense.getExpenseOwner().getEmail(),expense.getAmount());
        }else{
            expense=createNewExpense(expense.getExpenseOwner().getEmail(),expense.getAmount(),expense.getDescription().getDescription());
        }

        return expense;
    }
}
