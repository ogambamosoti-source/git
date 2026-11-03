package com.hrmanagement.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton; //CREATES BORDERS FOR SWING COMPONENTS THIS IS THE SPACE OR LINE AROUND A COMPONENT.CREATES EMPTY BORDER TOP LEFT RIRHT BOTTOM ADDS INVISIBLE PADING IN PEXELS
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;//OPENS SMALL POP UP DIALOGS SUCH AS ERRORS CONFIRMATION AND INPUT PROMPTS
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;//IT CONTROLS HOW A TABLE OR LIST LETS THE USER SELECT ROWS IT HOLDS CONSTANTS AND SELECTION STATE    SINGLE SELCTION ALLOWS ONLY ONE ROW TO BE SELECTED AT ATIME

import com.hrmanagement.model.Attendance;

public class AttendanceView extends JFrame{
    private final JTextField employeeIdField = new JTextField();
    private final JTextField dateField = new JTextField(LocalDate.now().toString());//yyyy.mm.dd
    //buttons
    private final JButton checkInbButton = new JButton("Check In");
    private final JButton checkoutButton = new JButton("check Out");
    private final JButton filterDateButton = new JButton("Show By Date");
    private final JButton filterEmployeeButton = new JButton("Show By Employee");
    private final JButton showAllButton = new JButton("Show All");
    private final JButton clearButton = new JButton("clear");

    //table reas only 
    private final DefaultTableModel tableModel = new DefaultTableModel(
        new String[]{"employeeId","Id","firstName","lastName","check in","check out"},0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;// records only change through check in and check out
            }
        };

    private final JTable table = new JTable(tableModel);
    private final JLabel messageLabel = new JLabel("");

    public AttendanceView(){
        super("HR SYSTEM");
        setSize(900,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //form:label+fields (2rows and 2columns)
        JPanel form = new JPanel(new GridLayout(2,2,5,5));//GridLayout(int rows, int cols, int hgap, int vgap)
        form.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));//createEmptyBorder(int top, int left, int bottom, int right) : Border
        form.add(new JLabel("Employee id"));
        form.add(employeeIdField);
        form.add(new JLabel("Date(yyyy-mm-dd)"));
        form.add(dateField);

        //butons
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(checkInbButton);
        buttons.add(checkoutButton);
        buttons.add(filterDateButton);
        buttons.add(showAllButton);
        buttons.add(clearButton);

        //top form+buttons
        JPanel top = new JPanel(new BorderLayout());
        top.add(form,BorderLayout.CENTER);
        top.add(buttons,BorderLayout.SOUTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));//createEmptyBorder(int top, int left, int bottom, int right) : Border

        messageLabel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        add(top,BorderLayout.NORTH);
        add(scroll,BorderLayout.CENTER);
        add(messageLabel,BorderLayout.SOUTH);

        table.getSelectionModel().addListSelectionListener(e->{
            if(!e.getValueIsAdjusting()){
                fillFormFromSelectedRow();
            }
        });
    }

        //read the form 
        public String getEmployeeIdText(){
            return employeeIdField.getText().trim();
        }
        public String getDateText(){
            return dateField.getText().trim();
        }

        //register listeners
        public void onCheckIn(ActionListener listener){
            checkInbButton.addActionListener(listener);
        }
        public void onCheckOut(ActionListener listener){
            checkoutButton.addActionListener(listener);
        }
        public void onFilterDate(ActionListener listener){
            filterDateButton.addActionListener(listener);
        }
        public void onFilterEmployee(ActionListener listener){
            filterEmployeeButton.addActionListener(listener);
        }
        public void onShowAll(ActionListener listener){
            showAllButton.addActionListener(listener);
        }
        public void onClear(ActionListener listener){
            clearButton.addActionListener(listener);
        }

        //update the screen 
        public void displayRecords(List<Attendance> records){
            tableModel.setRowCount(0);
            for(Attendance a : records){
                tableModel.addRow(new Object[]{
                    a.getAttendanceId(),
                    a.getEmployeeId(),
                    a.getWorkDate(),
                    a.getCheckIn()== null ? "-" : a.getCheckIn(),
                    a.getCheckOut()== null ? "-" : a.getCheckOut(),
                    a.getStatus()
                });
            }
        }

        public void clearForm(){
            employeeIdField.setText("");
            dateField.setText(LocalDate.now().toString());
            table.clearSelection();
            messageLabel.setText("");
        }

        public void showMessage(String message){
            messageLabel.setText(message);
        }

        public void showError(String message){
            JOptionPane.showMessageDialog(this, message,"error",JOptionPane.ERROR_MESSAGE);
        }

        public void fillFormFromSelectedRow(){
            int row= table.getSelectedRow();
            if(row<0){
                return;
            }
            employeeIdField.setText(String.valueOf(tableModel.getValueAt(row, 1)));
            dateField.setText(String.valueOf(tableModel.getValueAt(row,2)));
        }

    }
