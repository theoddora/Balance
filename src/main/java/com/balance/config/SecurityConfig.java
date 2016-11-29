package com.balance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by hangelov on 24/11/2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int FOUR_WEEKS = 2419200;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
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
                .logoutUrl("/logout")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/product").authenticated()
                .antMatchers("/log_in", "/register").anonymous()
                .antMatchers("/product", "/register").anonymous()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/denied")
                .and()
                .rememberMe()
                .tokenValiditySeconds(FOUR_WEEKS);

    }
}