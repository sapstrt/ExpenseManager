package com.expenses.repository;

import com.expenses.domain.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 1/26/14
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GroupExpenseDao extends JpaRepository<GroupExpense,Integer> {
}
