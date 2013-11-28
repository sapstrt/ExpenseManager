package com.expenses.repository;

import com.expenses.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/28/13
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GroupDao extends JpaRepository<Group,Integer>{
}
