/*
package com.expenses.domain;

import com.expenses.exception.GroupExpenseException;
import com.expenses.helper.Helper;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 *//*

@Entity
@Table(name = "EXPENSE")
@DiscriminatorValue("G")
public class GroupExpense extends Expense {
    private Group group;
    @ManyToMany
    @JoinTable(name = "DISTRIBUTED_EXPENSE",joinColumns = {@JoinColumn(name = "EXPENSE_INFO")},inverseJoinColumns = {@JoinColumn(name = "GROUP")})
    @MapKey(name = "USER")
    private Map<User, Expense> distributedExpense;

    public GroupExpense(int id, Double amount, ExpenseDetails description, User expenseOwner, Group group) {
        super(id, amount, description, expenseOwner);
        this.group = group;
        List<User> members;
        int groupSize;
        distributedExpense = new HashMap<User, Expense>((groupSize = (members = group.getMembers()).size()));
        for (User member : members) {
            Expense expense;
            if (member.equals(expenseOwner)){
                member.addExpense(expense=new Expense(Helper.generateId(),amount / groupSize-amount, description, expenseOwner));
            }else {
                member.addExpense((expense = new Expense(Helper.generateId(), amount / groupSize, description, expenseOwner)));
            }
            distributedExpense.put(member, expense);
        }

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Map<User, Expense> getDistributedExpense() {
        return distributedExpense;
    }

    public void setDistributedExpense(Map<User, Expense> distributedExpense) throws GroupExpenseException {
        Double totalExpense=0.0;
        for (Expense expense:distributedExpense.values() ){
            totalExpense+=expense.getAmount();
        }
        if (totalExpense!=getAmount()){
            throw new GroupExpenseException("Sum of individual expenses do not tally");
        }
        this.distributedExpense = distributedExpense;
    }
}
*/
