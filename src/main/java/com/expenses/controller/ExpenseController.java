package com.expenses.controller;

import com.expenses.domain.Expense;
import com.expenses.domain.User;
import com.expenses.service.ExpenseService;
import com.expenses.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 1/30/14
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public @ResponseBody Expense addNewExpense(@RequestParam("email") String email, @RequestBody Expense expense){

        return null;
    }
    @RequestMapping(method =RequestMethod.GET)
    @Transactional(readOnly = true)
    public @ResponseBody List<Expense> getAllExpenses(@RequestParam("email") String email){
        return expenseService.retrieveAllExpenses(email);
    }

}
