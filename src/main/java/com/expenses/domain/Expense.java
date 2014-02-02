package com.expenses.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.*/


@Entity
@Table(name = "EXPENSE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("U")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double amount;

    @Embedded
    private ExpenseDetails description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "expense_owner")
    private User expenseOwner;

    public Expense() {
    }

    public Expense(Double amount, ExpenseDetails description, User expenseOwner) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (id != expense.id) return false;
       if (!expenseOwner.equals(expense.expenseOwner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + expenseOwner.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", description=" + description  +
                '}';
    }
}
