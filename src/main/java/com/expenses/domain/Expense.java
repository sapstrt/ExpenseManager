package com.expenses.domain;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class Expense {
    private int id;
    private Double amount;
    private ExpenseDetails description;
    private User expenseOwner;

    public Expense(int id, Double amount, ExpenseDetails description, User expenseOwner) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.expenseOwner = expenseOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseDetails getDescription() {
        return description;
    }

    public void setDescription(ExpenseDetails description) {
        this.description = description;
    }

    public User getExpenseOwner() {
        return expenseOwner;
    }

    public void setExpenseOwner(User expenseOwner) {
        this.expenseOwner = expenseOwner;
    }
}
