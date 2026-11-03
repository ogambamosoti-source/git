package com.hrmanagement.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
public class LeaveRequest {
    private final int leaveRequestId;
    private final String employeeId;
    private final String leaveType; //ANNUAL,SICK,PENDING
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String reason;
    private final String status;  //PENDING,aPPROVED,REJECTED
    private final LocalDateTime appliedOn;

    //the constructor for a row read by database
    public LeaveRequest(String employeeId,String leaveType,LocalDate startDate,LocalDate endDate,String reason,String status,LocalDateTime appliedOn){
        this(0,employeeId,leaveType,startDate,endDate,reason,"PENDING",LocalDateTime.now());
    }
    //the constuctor 
    public LeaveRequest(int leaveRequestId,String employeeId,String leaveType,LocalDate startDate,LocalDate endDate,String reason,String status,LocalDateTime appliedOn){
        this.leaveRequestId = leaveRequestId;
        this.employeeId = employeeId;
        this.leaveType = leaveType; 
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "PENDING";
        this.appliedOn = appliedOn;
    }
    public int getLeaveRequestId(){
        return leaveRequestId;
    }
    public String getEmployeeId(){
        return employeeId;
    }
    public String getLeaveType(){
        return leaveType;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public LocalDate getEndDate(){
        return endDate;
    }
    public String getReason(){
        return reason;
    }
    public String getStatus(){
        return status;
    }
    public LocalDateTime getAppliedOn(){
        return appliedOn;
    }

    
}
