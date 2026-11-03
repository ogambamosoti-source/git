package com.hrmanagement.controller;

import java.sql.SQLException;
import java.util.List;

import com.hrmanagement.dao.EmployeeDao;
import com.hrmanagement.model.Employee;
import com.hrmanagement.view.EmployeeView;
public class EmployeeController {
    private final EmployeeView view;
    private final EmployeeDao dao;
    
    public EmployeeController(EmployeeView view,EmployeeDao dao){
        this.view = view;
        this.dao = dao;

        view.onAdd(e-> addEmployee());
        view.onUpdate(e-> updateEmployee());
        view.onDelete(e-> deleteEmployee());
        view.onShowAll(e-> showAll());

        showAll();

    }
    private boolean fieldsFilled(){
        return !(view.getEmployeeId().isEmpty() || view.getFirstName().isEmpty() || view.getLastName().isEmpty() || view.getEmail().isEmpty() || view.getPhone().isEmpty() || view.getRole().isEmpty());
    }
    private void addEmployee(){
        if(!fieldsFilled()){
            view.showMessage("please fill in all 6 fields");
            return;
        }
        try {
            dao.add(new Employee(
                    view.getEmployeeId(),
                    view.getFirstName(),
                    view.getLastName(),
                    view.getEmail(),
                    view.getPhone(),
                    view.getRole()
            ));
            view.clearFields();
            view.showMessage("Employee added successfully");
            showAll();
        } catch (SQLException e) {
            view.showMessage("Error adding employee: " + e.getMessage());
        }
    }

    private void updateEmployee(){
        if(view.getIdText().isEmpty() || !fieldsFilled()){
            view.showMessage("select an employee and fill all fields");
            return;
        }
        try {
            int id = Integer.parseInt(view.getIdText());
            Employee emp = new Employee(view.getEmployeeId(),view.getFirstName(),view.getLastName(),view.getEmail(),view.getPhone(),view.getRole());
            if(dao.update(emp)){
                view.showMessage("employee updated");
                view.clearFields();
                showAll();
            }else{
                view.showMessage("employee not found");
            }
        } catch (SQLException e){
            view.showMessage("employee with that id in not found");
        }
    }
    private void showAll(){
        try {
            List<Employee> list = dao.getAll();
            view.displayEmployees(list);
        } catch (SQLException e) {
            view.showMessage("Database error:" + e.getMessage());
        }
    }
    private void deleteEmployee(){
        try {
            int id = Integer.parseInt(view.getIdText());//i should be able to read id from the form
            if(dao.delete(id)){ //ask dao to delete it
                view.showMessage("Employee deleted.");
                showAll();
            } else {
                view.showMessage("No employee with that idNumber");
            }
        } catch (SQLException e) {
            view.showMessage("database error:" +e.getMessage());
        }
    }
}
