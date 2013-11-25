package com.expenses.domain;

import com.expenses.exception.UserActivationException;
import com.sun.xml.internal.ws.util.QNameMap;

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
public class Group {
    private int id;
    private String groupName;
    private Map<User, MemberProperty> memberMap;
    private List<GroupExpense> groupExpenses;
    private static MemberProperty CREATOR_PROP = new MemberProperty();
    private static MemberProperty ACTIVE_MEMBER = new MemberProperty(true, false);
    private static MemberProperty PASSIVE_MEMBER = new MemberProperty(false, false);

    public String getGroupName() {
        return groupName;
    }

    public List<User> getMembers() {
        List<User> members;
        members=new ArrayList<User>(memberMap.keySet());
        return members;
    }
    //todo change to enum .?
    public static class MemberProperty {


        private MemberProperty(boolean isActive, boolean isAdmin) {
            this.isActive = isActive;
            this.isAdmin = isAdmin;
        }

        private MemberProperty() {
        }

        boolean isAdmin;
        boolean isActive;


    }


    public Group(int id, String groupName, User owner) {
        this.id = id;
        this.groupName = groupName;
        memberMap = new HashMap<User, MemberProperty>();
        memberMap.put(owner, CREATOR_PROP);
        owner.addGroup(this);
        groupExpenses = new ArrayList<GroupExpense>();
    }

    public void addMember(User member){
        memberMap.put(member,PASSIVE_MEMBER);
    }
    public void activateMember(User member) throws UserActivationException {
        MemberProperty memberProperty;
        if ((memberProperty=memberMap.get(member))==null)
            throw new UserActivationException(member.getEmail(),groupName);
        memberProperty.isActive=true;

    }

    public void addExpense(GroupExpense groupExpense){
        groupExpenses.add(groupExpense);
    }


}
