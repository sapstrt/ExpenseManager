package com.expenses.service;

import com.expenses.domain.Group;
import com.expenses.domain.User;
import com.expenses.exception.GroupCreationException;
import com.expenses.exception.GroupNotFoundException;
import com.expenses.exception.UserActivationException;
import com.expenses.exception.UserDoesNotExistException;
import com.expenses.helper.Helper;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupServiceImpl implements GroupService {
    UserService userService;
    @Override
    public Group createGroup(String groupName, String ownerEmailId) throws GroupCreationException {
        User user;
        if ((user=userService.retrieveUser(ownerEmailId))==null){
            throw new GroupCreationException("User does not exist");
        }
        if (validateGroupCredentials(user,groupName)){
            //todo : validate for group id
            Group group=new Group(Helper.generateId(),groupName,user);
            //todo : groupDao
            return group;
        }

        return null;
    }

    private boolean validateGroupCredentials(User user, String groupName) {
        return user.getGroup(groupName)==null?true:false;
    }

    @Override
    public void addMember(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException {
        User member;
        Group group;
        if ((member=userService.retrieveUser(memberEmailId))==null){
            throw new UserDoesNotExistException(memberEmailId);
        }
        if ((group=retrieveGroup(groupId))==null)
            throw new GroupNotFoundException(groupId);
        group.addMember(member);
        member.addGroup(group);
        //todo update dao


    }

    @Override
    public void activateMembership(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException, UserActivationException {
        User member;
        Group group;
        if ((member=userService.retrieveUser(memberEmailId))==null)
            throw new UserDoesNotExistException(memberEmailId);
        if ((group=retrieveGroup(groupId))==null)
            throw new GroupNotFoundException(groupId);
        if (!checkPreconditionsToActivateMembership(member))
            throw new UserActivationException(memberEmailId,group.getGroupName());
        //group.activateMember(member);
        //todo : groupDao


    }

    private boolean checkPreconditionsToActivateMembership(User member) {
        //todo
        return true;
    }

    @Override
    public Group retrieveGroup(int groupId) {
        //todo dao
        return null;
    }
}
