package com.example.BachEnd_Ses4.service.system;

import com.example.BachEnd_Ses4.model.System.Role;
import com.example.BachEnd_Ses4.model.System.User;
import com.example.BachEnd_Ses4.repositories.system.RoleRepo;
import com.example.BachEnd_Ses4.repositories.system.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final  RoleRepo roleRepo;
    private final  PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if (user == null){
            log.error("UserServiceImpl line 32 - User not found in the database");
            throw  new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("UserServiceImpl line 35 - User found in the database: {}", username);
            log.info("UserServiceImpl line 36 - password found in the database: {}", user.getPassword());
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("UserServiceImpl line 42- authorities: {}", authorities);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("UserServiceImpl line 48 - saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("UserServiceImpl line 55 - saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("UserServiceImpl line 61 - Adding role {} to user {} ", roleName, userName);
        User user = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("UserServiceImpl line 69 - Fetching user {}", userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("UserServiceImpl line 75 - Fetching users");
        return userRepo.findAll();
    }

    @Override
    public List<Role> getRole() {
        return roleRepo.findAll();
    }


}
