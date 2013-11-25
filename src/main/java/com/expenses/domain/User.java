package com.expenses.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int id;
    private String userName;
    private String email;
    private List<Group> memberOfGroup;
    private List<Expense> expenses;
    private boolean isActive;

    public User(int id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        expenses = new ArrayList<Expense>();
        memberOfGroup = new ArrayList<Group>();
        isActive=false;
    }


    public void addGroup(Group group) {
        memberOfGroup.add(group);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void setActive(boolean active){
        isActive=active;
    }

    public Group getGroup(String groupName) {
        for (Group group:memberOfGroup){
            if (group.getGroupName().equals(groupName))
                return group;
        }
        return null;
    }


    public String getEmail() {
        return email;
    }
}
