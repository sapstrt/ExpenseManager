package com.expenses.domain;

import com.expenses.exception.UserException;

import javax.jdo.annotations.PersistenceCapable;
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
@PersistenceCapable
public class Group {
    private int id;
    private String groupName;

    private Map<User, MemberProps> memberMap;
   // private List<GroupExpense> groupExpenses;

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        List<User> members;
        members = new ArrayList<User>(memberMap.keySet());
        return members;
    }

    public Group() {
        memberMap=new HashMap<User, MemberProps>();
    }

    public Group(int id, String groupName, User owner) {
        this.id = id;
        this.groupName = groupName;
        memberMap = new HashMap<User, MemberProps>();
        memberMap.put(owner, MemberProps.ADMIN);
        owner.addGroup(this);
     //   groupExpenses = new ArrayList<GroupExpense>();
    }

    public void addMember(User member) {
        memberMap.put(member, MemberProps.PASSIVE);
    }

    public boolean isAdmin(User member) throws UserException {
        MemberProps memberProperty;
        if ((memberProperty = memberMap.get(member)) == null)
            throw new UserException(member + " not a member of " + this);
        return memberProperty.isAdmin();
    }

    public boolean isActive(User member) throws UserException {
        MemberProps memberProperty;
        if ((memberProperty = memberMap.get(member)) == null)
            throw new UserException(member + " not a member of " + this);
        return memberProperty.isActive();
    }

    public void activateMember(User member) throws UserException {
        if ( memberMap.get(member) == null)
            throw new UserException(member + " not a member of " + this);
        memberMap.put(member,MemberProps.ACTIVE);

    }

  /*  public void addExpense(GroupExpense groupExpense) {
        groupExpenses.add(groupExpense);
    }
*/
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<User, MemberProps> getMemberMap() {
        return memberMap;
    }

    public void setMemberMap(Map<User, MemberProps> memberMap) {
        this.memberMap = memberMap;
    }
}
