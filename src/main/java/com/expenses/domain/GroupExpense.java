package com.expenses.domain;

import com.expenses.exception.GroupExpenseException;
import com.expenses.helper.Helper;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.*/


@Entity
@Table(name = "EXPENSE")
@DiscriminatorValue("G")
public class GroupExpense extends Expense {
    @ManyToOne
    private Group group;
    @ElementCollection(targetClass = Expense.class)
    @MapKeyClass(User.class)
    @JoinTable(
            name = "GROUP_EXPENSE_DIST",
            joinColumns = @JoinColumn(name = "expense")
    )
    @Column(name = "member")
    private Map<User, Expense> distributedExpense;

    public GroupExpense() {
    }

    public GroupExpense( Double amount, ExpenseDetails description, User expenseOwner, Group group) throws GroupExpenseException {
        super( amount, description, expenseOwner);
        this.group = group;
        List<User> members;
        int groupSize;
        Map<User,Expense> distributedExpenses = new HashMap<User, Expense>((groupSize = (members = group.getMembers()).size()));
        for (User member : members) {
            Expense expense;
            if (member.equals(expenseOwner)){
                member.addExpense(new Expense(amount,description,expenseOwner));
                member.addExpense(expense=new Expense(amount / groupSize-amount, description, expenseOwner));
            }else {
                member.addExpense((expense = new Expense(amount / groupSize, description, expenseOwner)));
            }
            distributedExpenses.put(member, expense);
        }
        setDistributedExpense(distributedExpenses);
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
        if (totalExpense!=0.0){
            throw new GroupExpenseException("Sum of individual expenses do not tally");
        }
        this.distributedExpense = distributedExpense;
    }

    @Override
    public String toString() {
        return "GroupExpense{" +
                '}';
    }
}
