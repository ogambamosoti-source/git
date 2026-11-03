package com.hrmanagement.model;

public class Employee implements Person{
    private int id;
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;

    //new employee no id yet(database creates the id)
    public Employee(String employeeId,String firstName,String lastName,String email,String phone,String role){
        this(0,employeeId,firstName,lastName,email,phone,role);
    }

    //Existing employee id comes from the database
    public Employee(int id,String employeeId,String firstName,String lastName,String email,String phone,String role){
        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
    //input the gettersr required by person
    @Override
    public int getId(){
        return id;
    }
    @Override
    public String getFirstName(){
        return firstName;
    }
    @Override
    public String getLastName(){
        return lastName;
    }
    @Override
    public String getEmail(){
        return email;
    }
    @Override
    public String getPhone(){
        return phone;
    }
    public String getRole(){
        return role;
    }
    public String getEmployeeId(){
        return employeeId;
    }
    // input the setters
    public void setid(int id){
        this.id = id;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
@Override 
public String toString(){
    return "Employee{id= '" + id
    +"'firstName='" + firstName 
    + "', lastname='" + lastName 
    + "', email='" + email 
    + "', phone='" + phone 
    +"' ,role='" + role 
    + "',employeeId'" + employeeId
    +"'}";
    
}
}