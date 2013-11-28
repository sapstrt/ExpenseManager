package com.expenses.repository;

import com.expenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/28/13
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ExpenseDao extends JpaRepository<Expense,Integer> {
}
