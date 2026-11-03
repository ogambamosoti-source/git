package com.hrmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {
    private final int attendanceId;
    private final String EmployeeId;
    private final LocalDate workDate;
    private final LocalDateTime checkIn;
    private final LocalDateTime checkOut;
    private final String status;

    //new record (no id yet the database generates id)
public Attendance(String employeeId,LocalDate workDate,LocalDateTime checkIn,LocalDateTime checkOut,String status){
    this(0,employeeId,workDate,checkIn,checkOut,status);
}
public Attendance(int attendanceId,String EmployeeId,LocalDate workDate,LocalDateTime checkIn,LocalDateTime checkOut,String status){
    this.attendanceId = attendanceId;
    this.EmployeeId = EmployeeId;
    this.workDate = workDate;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.status = status;

}

public int getAttendanceId(){
    return attendanceId;
}
public String getEmployeeId(){
    return EmployeeId;
}
public LocalDate getWorkDate(){
    return workDate;
}
public LocalDateTime getCheckIn(){
    return checkIn;
}
public LocalDateTime getCheckOut(){
    return checkOut;
}
public String getStatus(){
    return status;
}
    
}
