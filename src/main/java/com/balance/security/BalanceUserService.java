package com.balance.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.balance.dao.UserDAO;
import com.balance.dao.UserDAOImpl;
import com.balance.model.User;

/**
 * Created by hangelov on 29/11/2016.
 */
public class BalanceUserService implements UserDetailsService {

    private final UserDAO userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    public BalanceUserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("in loadUserByUsername() method with username - " + username);
        User user = null;
        try {
            user = userDao.findByUsername(username);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        logger.info("end loadUserByUsername() method");
        return new SecurityUser(user, authorities);
    }

}


