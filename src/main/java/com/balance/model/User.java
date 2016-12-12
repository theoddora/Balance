package com.balance.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import cz.jirutka.validator.spring.SpELAssert;

@SpELAssert(
    value = "password.equals(passwordRepeat)",
    applyIf = "!password.isEmpty() || !passwordRepeat.isEmpty()",
    message = "{password.match}"
)
public class User {

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private long id;

    @NotBlank(message = "{username.empty}")
    @Size(min = 4, max = 20, message = "{username.size}")
    @Pattern(regexp = "^[\\w]+$", message = "{username.valid}")
    private String username;

    @NotBlank(message = "{email.empty}")
    @Pattern(regexp = EMAIL_REGEX, message = "{email.valid}")
    @Email()
    private String email;

    @NotBlank(message = "{password.empty}")
    @Size(min = 5, max = 100, message = "{password.size}")
    private String password;

    private String passwordRepeat;

    @NotBlank(message = "{name.empty}")
    @Size(min = 2, max = 20, message = "{name.size}")
    private String name;

    private boolean isAdmin;

    public User() {
        this.isAdmin = false;
    }

    public User(String username, String email, String name, String password) {
        setUsername(username);
        setEmail(email);
        setName(name);
        setPassword(password);
        this.isAdmin = false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    //getters
    public boolean isAdmin() {
        return isAdmin;
    }

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
