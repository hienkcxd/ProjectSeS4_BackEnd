package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Role;
import com.example.BachEnd_Ses4.model.System.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void addRoleToUser(String userName, String roleName);
    public User getUser(String userName);
    public List<User> getUsers();

    public List<Role> getRole();
}
