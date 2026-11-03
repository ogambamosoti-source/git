package com.hrmanagement;

import java.sql.SQLException;

import javax.swing.SwingUtilities;

import com.hrmanagement.controller.EmployeeController;
import com.hrmanagement.dao.EmployeeDao;
import com.hrmanagement.view.DashboardView;
import com.hrmanagement.view.EmployeeView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            try {
                EmployeeView view = new EmployeeView();
                EmployeeDao dao = new EmployeeDao();
                new EmployeeController(view,dao);

                DashboardView dashboard = new DashboardView(view);
                dashboard.setTotal(dao.countAll());
                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);
            
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
} 
