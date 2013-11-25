package com.expenses.domain;

import com.expenses.helper.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupExpense extends Expense{
    private Group group;
    private Map<User,Expense> distributedExpense;

    public GroupExpense(int id, Double amount, ExpenseDetails description, User expenseOwner, Group group) {
        super(id, amount, description, expenseOwner);
        this.group = group;
        List<User> members;
        int groupSize;
        distributedExpense=new HashMap<User, Expense>((groupSize=(members=group.getMembers()).size()));
        for (User member:members){
            Expense expense;
            member.addExpense((expense=new Expense(Helper.generateId(),amount/groupSize,description,expenseOwner)));
            distributedExpense.put(member,expense);
        }

    }


}
