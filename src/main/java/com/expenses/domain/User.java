package com.expenses.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

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
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String email;

    private boolean isActive;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Group> memberOfGroup;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "expenseOwner")
    private List<Expense> expenses;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public User() {
        memberOfGroup=new ArrayList<Group>();
    }

    public User( String userName, String email) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Group> getMemberOfGroup() {
        return memberOfGroup;
    }

    public void setMemberOfGroup(List<Group> memberOfGroup) {
        this.memberOfGroup = memberOfGroup;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
