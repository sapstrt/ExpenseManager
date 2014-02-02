package com.expenses.service;

import com.expenses.domain.Group;
import com.expenses.domain.User;
import com.expenses.exception.*;
import com.expenses.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: vinay.varma
 * Date: 11/25/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class GroupServiceImpl implements GroupService {
    @Autowired
    UserService userService;
    @Autowired
    GroupDao groupDao;
    @Override
    public Group createGroup(String groupName, String ownerEmailId) throws GroupCreationException {
        User user;
        if ((user=userService.retrieveUser(ownerEmailId))==null){
            throw new GroupCreationException("User does not exist");
        }
        if (validateGroupCredentials(user,groupName)){
            Group group=new Group(groupName,user);
            return group;
        }

        return null;
    }

    private boolean validateGroupCredentials(User user, String groupName) {
        return user.getGroup(groupName)==null?true:false;
    }

    @Override
    @Deprecated
    public void addMember(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException {
        User member;
        Group group;
        if ((member=userService.retrieveUser(memberEmailId))==null){
            throw new UserDoesNotExistException(memberEmailId);
        }
        if ((group=retrieveGroup(groupId))==null)
            throw new GroupNotFoundException(groupId+"");
        group.addMember(member);
        member.addGroup(group);


    }

    @Override
    @Deprecated
    public void activateMembership(int groupId, String memberEmailId) throws UserDoesNotExistException, GroupNotFoundException, UserActivationException, UserException {
        User member;
        Group group;
        if ((member=userService.retrieveUser(memberEmailId))==null)
            throw new UserDoesNotExistException(memberEmailId);
        if ((group=retrieveGroup(groupId))==null)
            throw new GroupNotFoundException(groupId+"");
        if (!checkPreconditionsToActivateMembership(member))
            throw new UserActivationException(memberEmailId,group.getGroupName());
        group.activateMember(member);


    }

    @Override
    public Group retrieveGroup(int groupId) {
        Group group=groupDao.findOne(groupId);
        return group;
    }

    @Override
    public Group retrieveGroup(String email, String groupName) throws UserDoesNotExistException, GroupNotFoundException, UserException {
        User user= userService.retrieveUser(email);
        if (user==null){
            throw new UserDoesNotExistException(email);
        }else if(!user.isActive()) {
            throw new UserException("user not active") ;
        }else{
            for (Group group:user.getMemberOfGroup()){
                if (group.getGroupName().equals(groupName))
                    return group;
            }

        }

        throw new GroupNotFoundException(groupName);
    }

    @Override
    public Group addMember(String ownerEmail, String groupName, String memberEmail, String memberName) throws UserDoesNotExistException, GroupNotFoundException, UserException {
        Group group= retrieveGroup(ownerEmail,groupName);
        User user=userService.retrieveUser(memberEmail);
        if (user==null)
            user=userService.createNewUser(memberName,memberEmail);
        group.addMember(user);
        sendNotificationToUser( group,user);
        return group;
    }

    @Override
    public boolean activateMembership(String email, String groupName) throws GroupNotFoundException, UserDoesNotExistException, UserException {
        User user=userService.retrieveUser(email);
        Group group=retrieveGroup(email, groupName);
        if(checkPreconditionsToActivateMembership(group,user)){
            group.activateMember(user);
            return true;
        }
        return false;
    }

    private void sendNotificationToUser(Group group, User user) {
        //todo
    }

    private boolean checkPreconditionsToActivateMembership(User member) {
        return true;
    }

    private boolean checkPreconditionsToActivateMembership(Group group,User member) {
        //todo
        return true;
    }
}
