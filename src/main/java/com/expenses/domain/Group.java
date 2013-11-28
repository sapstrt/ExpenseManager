package com.expenses.domain;

import com.expenses.exception.UserException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/24/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "GROUP")
public class Group {
    @Id
    private int id;
    private String groupName;
    private Map<User, MemberProps> memberMap;
    private List<GroupExpense> groupExpenses;

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        List<User> members;
        members = new ArrayList<User>(memberMap.keySet());
        return members;
    }
    public enum MemberProps{
        ADMIN(true,true),ACTIVE(true,false),PASSIVE(false,false);
        private final boolean isActive;
        private final boolean isAdmin;

        MemberProps(boolean isActive, boolean isAdmin) {
            this.isActive=isActive;
            this.isAdmin=isAdmin;
        }

        public boolean isActive() {
            return isActive;
        }

        public boolean isAdmin() {
            return isAdmin;
        }
    }

    public Group(int id, String groupName, User owner) {
        this.id = id;
        this.groupName = groupName;
        memberMap = new HashMap<User, MemberProps>();
        memberMap.put(owner, MemberProps.ADMIN);
        owner.addGroup(this);
        groupExpenses = new ArrayList<GroupExpense>();
    }

    public void addMember(User member) {
        memberMap.put(member, MemberProps.PASSIVE);
    }

    public boolean isAdmin(User member) throws UserException {
        MemberProps memberProperty;
        if ((memberProperty = memberMap.get(member)) == null)
            throw new UserException(member + " not a member of " + this);
        return memberProperty.isAdmin;
    }

    public boolean isActive(User member) throws UserException {
        MemberProps memberProperty;
        if ((memberProperty = memberMap.get(member)) == null)
            throw new UserException(member + " not a member of " + this);
        return memberProperty.isActive;
    }

    public void activateMember(User member) throws UserException {
        if ( memberMap.get(member) == null)
            throw new UserException(member + " not a member of " + this);
        memberMap.put(member,MemberProps.ACTIVE);

    }

    public void addExpense(GroupExpense groupExpense) {
        groupExpenses.add(groupExpense);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (!groupName.equals(group.groupName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + groupName.hashCode();
        return result;
    }
}
