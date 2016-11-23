package com.balance.model;


import com.balance.exceptions.UserException;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;
    private String name;

    public User(){

    }

    public User(int id, String userName, String email, String name, String password) throws UserException {
        setId(id);
        setUserName(userName);
        setEmail(email);
        setName(name);
        setPassword(password);
    }

    public void setUserName(String userName) throws UserException {
        if(userName != null && !userName.isEmpty())
            this.userName = userName;
        else
            throw new UserException("Invalid username!");
    }

    public void setPassword(String password) throws UserException {
        if(password != null && !password.isEmpty())
            this.password = password;
        else
            throw new UserException("Invalid password!");
    }

    public void setEmail(String email) throws UserException {
        if(email != null && !email.isEmpty())
            this.email = email;
        else
            throw new UserException("Invalid email!");
    }

    public void setName(String name) throws UserException {
        if(name != null && !name.isEmpty())
            this.name = name;
        else
            throw new UserException("Invalid name!");
    }

    public void setId(int id){
        this.id = id;
    }



}
