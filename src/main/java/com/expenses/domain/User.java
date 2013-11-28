package com.expenses.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    private int id;

    private String userName;

    private String email;

    private boolean isActive;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name="MEMBER",joinColumns = {@JoinColumn(name = "USER_ID")},inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")})
    private List<Group> memberOfGroup;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "expenseOwner")
    private List<Expense> expenses;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        return result;
    }
}