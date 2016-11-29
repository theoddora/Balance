package com.balance.security;

import com.balance.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
        return null;
    }
}
