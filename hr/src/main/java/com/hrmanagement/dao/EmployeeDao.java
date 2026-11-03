package com.hrmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hrmanagement.model.Employee;
import com.hrmanagement.util.DBConnection;

public class EmployeeDao {
    //create
    public void add(Employee emp)throws SQLException{
        String sql ="INSERT INTO employees(employee_id,first_name,last_name,email,phone,role)VALUES(?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getEmployeeId());    
            ps.setString(2, emp.getFirstName());
            ps.setString(3, emp.getLastName());
            ps.setString(4, emp.getEmail());
            ps.setString(5, emp.getPhone());
            ps.setString(6, emp.getRole());
            ps.executeUpdate();
        }
    }
    //READ OPERATIONS
    public List<Employee> getAll() throws SQLException{
        List<Employee> list = new ArrayList<>();
        String sql= "SELECT * FROM employees";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                list.add(new Employee(
                    rs.getInt("Id"),
                    rs.getString("employee_Id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getString("phone_Number"),
                    rs.getString("role")));
            }

        }
        return list;

    }
    //update staff
    public boolean update(Employee emp)throws  SQLException{
        String sql = "UPDATE employees SET first_name=?,last_name=?,email=?,phone=?,role=? WHERE id=?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,emp.getEmployeeId());
            ps.setString(2,emp.getFirstName());
            ps.setString(3,emp.getLastName());
            ps.setString(4,emp.getEmail());
            ps.setString(5,emp.getPhone());
            ps.setString(6,emp.getRole());
            return ps.executeUpdate()>0;

        }
    }
    public boolean delete(int id) throws SQLException{
        String sql ="DELETE FROM employees WHERE id =?";

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,id);
            return ps.executeUpdate()>0;
        }
    }
    public int countAll()throws SQLException{
        String sql = "SELECT COUNT(*) FROM employees";
        try(Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql)){
            return rs.next()? rs.getInt(1):0;
        }
    }
    
}
