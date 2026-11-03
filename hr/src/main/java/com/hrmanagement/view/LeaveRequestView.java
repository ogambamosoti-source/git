package com.hrmanagement.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.hrmanagement.model.LeaveRequest;

public class LeaveRequestView extends  JFrame {// main window of tha app
    private final JTextField employeeIdField = new JTextField();
    private final JTextField leaveTypeField = new JTextField();
    private final JTextField startDateField = new JTextField();
    private final JTextField endDateField = new JTextField();
    //buttons 
    private final JButton submitRequestButton = new JButton();
    private final JButton clearButton = new JButton();
    private final JButton cancelButton = new JButton();
    //table read omly
    private final DefaultTableModel tableModel= new DefaultTableModel(
        new String[]{"employeeId","leaveTypeId","startDate","endDate"},0){
            @Override 
            public boolean isCellEditable(int row ,int column){
                return false;
            }
        };
    
    private final JTable table = new JTable(tableModel);   
    private final JLabel messageLabel = new JLabel(""); 

    public LeaveRequestView(){
        super("HR_SYSTEM");
        setSize(900,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(4,4,7,7));
        form.add(new JLabel("Employee id"));
        form.add(employeeIdField);
        form.add(new JLabel("Leave type"));
        form.add(leaveTypeField);
        form.add(new JLabel("start date(yyyy-MM-dd)"));
        form.add(startDateField);
        form.add(new JLabel("endDate(yyyy-MM-dd)"));
        form.add(endDateField);

        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(submitRequestButton);
        buttons.add(clearButton);
        buttons.add(cancelButton);

        JPanel top = new JPanel(new BorderLayout());
        top.add(form,BorderLayout.NORTH);
        top.add(buttons,BorderLayout.SOUTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        messageLabel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        add(top,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(messageLabel,BorderLayout.SOUTH);
    }
    public String getEmployeeId(){
        return employeeIdField.getText().trim();
    }
    public String getLeaveType(){
        return leaveTypeField.getText().trim();
    }
    public String getStartDate(){
        return startDateField.getText().trim();
    }
    public String  getEndDate(){
        return endDateField.getText().trim();
    }
    
    //register listeners buttons listeners to perform actions  pressed by the buttons
    public void onSubmitRequest(ActionListener listener){
        submitRequestButton.addActionListener(listener);
    }
    public void onClear(ActionListener listener){
        clearButton.addActionListener(listener);
    }
    public void onCancel(ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    public void displayRecords(List<LeaveRequest> records){
        tableModel.setRowCount(0);
        for(LeaveRequest lr: records){
            tableModel.addRow(new Object[]{
                lr.getLeaveRequestId(),
                lr.getEmployeeId(),
                lr.getLeaveType(),
                lr.getStartDate(),
                lr.getEndDate(),
                lr.getReason(),
                lr.getStatus(),
                lr.getAppliedOn()
            });
        }
    }
    public void clearForm(){
        employeeIdField.setText("");
        leaveTypeField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        table.clearSelection();
        messageLabel.setText("");

    }
    public void showMessage(String message){
        messageLabel.setText(message);
    }
    public void showError(String message){
        JOptionPane.showMessageDialog(this, message,"error",JOptionPane.ERROR_MESSAGE);
    }
}
