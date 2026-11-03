/*package com.hrmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hrmanagement.model.*;
import com.hrmanagement.util.*;

public class AttendanceDao {
    //create
    public void insert(Attendance a){
        String sql = "INSERT INTO attendance(employee_id,work_date,check_in,check_out,status)VALUES(?,?,?,?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,a.getEmployeeId());
                ps.setDate(2,Date.valueOf(a.getWorkDate()));
                ps.setTimestamp(3,toTs(a.getCheckIn()));
                ps.setTimestamp(4,toTs(a.getCheckOut()));
                ps.setString(5,a.getStatus());
                ps.executeUpdate();
                try(ResultSet keys = ps.getGeneratedKeys()){
                    return keys.next() ? keys.getInt(1):0;
                }

        }
    }
    //update =clock out
    public boolean clockOut(String employeeId,LocalDate date,LocalDateTime out){
        String sql = "UPDATE attendance SET clock_out =? WHERE emp_id =? AND work_date=?";
        
    }
    









































    private Timestamp toTs(LocalDateTime t){
        return t == null? null : Timestamp.valueOf(t);
    }
}
*/