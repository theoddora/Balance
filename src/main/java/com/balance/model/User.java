package com.balance.model;


import com.balance.exceptions.UserException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

public class User {

    private long id;

    @NotBlank(message = "{username.empty}")
    @Size(min = 4, max = 20, message = "{username.size}")
    private String username;

    @NotBlank(message = "{email.empty}")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "{email.valid}")
    @Email()
    private String email;

    @NotBlank(message = "{password.empty}")
    @Size(min = 5, max = 100, message = "{password.size}")
    private String password;

    @NotBlank(message = "{name.empty}")
    @Size(min = 2, max = 20, message = "{name.size}")
    private String name;

    public User() {
    }

    public User(String username, String email, String name, String password) throws UserException {
        setUsername(username);
        setEmail(email);
        setName(name);
        setPassword(password);
    }

    public void setUsername(String username) throws UserException {
        this.username = username;

    }

    public void setPassword(String password) throws UserException {
        this.password = password;
    }

    public void setEmail(String email) throws UserException {
        this.email = email;
    }

    public void setName(String name) throws UserException {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    //getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
