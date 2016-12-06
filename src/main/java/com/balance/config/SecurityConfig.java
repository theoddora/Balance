package com.balance.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.balance.security.BalanceUserService;

/**
 * Created by hangelov on 24/11/2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int FOUR_WEEKS = 2419200;
    private static final int ENCODER_STRENGTH = 12;

    @Autowired
    DataSource datasource;

    @Autowired
    BalanceUserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin()
            .loginPage("/log_in")
            .defaultSuccessUrl("/", true)
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .logoutUrl("/log_out")
            .and()
            .authorizeRequests()
            .antMatchers("/log_in", "/register").anonymous()
            .antMatchers("/register").anonymous()
            .antMatchers(HttpMethod.POST, "/product").access("isAuthenticated() and hasRole('ROLE_USER')")
            .anyRequest().permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/denied")
            .and()
            .rememberMe()
            .tokenValiditySeconds(FOUR_WEEKS)
            .key("balanceKye");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        try {
            return new BCryptPasswordEncoder(ENCODER_STRENGTH, SecureRandom.getInstance("SHA1PRNG"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(
                "Failed to instantiate specified BCryptPasswordEncoder, reverting to deafult BCryptPasswordEncoder.");
            return new BCryptPasswordEncoder();
        }
    }
}
