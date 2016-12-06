package com.balance.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.balance.dao.UserDAO;
import com.balance.model.User;

/**
 * Created by hangelov on 29/11/2016.
 */
public class BalanceUserService implements UserDetailsService {

    private final UserDAO userDao;

    @Autowired
    public BalanceUserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
//        }
//        if(user.getUsername().equals("boncho")){
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new SecurityUser(user, authorities);
    }

}


