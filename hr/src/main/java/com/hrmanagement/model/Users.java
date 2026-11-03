package com.hrmanagement.model;

public class Users{
    private int id;
    private String username;
    private  String employeeId;
    private String role;

    public Users(int id,String username,String employeeId,String role){
        this.id = id;
        this.username = username;
        this.employeeId = employeeId;
        this.role = role;
    }
    public int getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public String getEmployeeId(){
        return employeeId;
    }
    public String getRole(){
        return role;
    }
    
}
