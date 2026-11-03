package com.hrmanagement.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event .ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.hrmanagement.model.Employee;

public class EmployeeView extends JPanel {

    // 5 input fields attributes of the emplyeeView this is like the input text field
    private final JTextField idField = new JTextField(20);
    private final JTextField employeeIdField = new JTextField(20);
    private  final JTextField firstNameField = new JTextField(20);
    private  final JTextField lastNameField  = new JTextField(20);
    private final JTextField emailField    = new JTextField(20);
    private  final JTextField phoneField     = new JTextField(20);
    private  final JTextField roleField      = new JTextField(20);

    //buttons field
    private final JButton addButton   = new JButton("Add");
    private final JButton updateButton = new JButton("Update");
    private final JButton deleteButton = new JButton("Delete by IdNumber");
    private final JButton clearButton = new JButton("clear");
    private final JButton showAllButton  = new JButton("Show All");

    private final DefaultTableModel tableModel = new DefaultTableModel(
        new String[]{"Id","employee id","first Name","last Name","email","phone","role"},0){
            @Override 
            public boolean  isCellEditable(int row,int column){
                return false; //table is read only editing is done in form
            }
        };

    // Output area and message
    private final JTable table = new JTable(tableModel);
    private final JLabel messageLabel  = new JLabel();

    //pages
    private final JPanel formPanel = new JPanel(new BorderLayout());
    private final JPanel listPanel = new JPanel(new BorderLayout());
    private final JLabel totalLabel = new JLabel();

    public EmployeeView() {
        //super("HR SYSTEM");// are not needed bcz since a panel does not have them
        //position of the frame like justify content
        setSize(900,600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));//flowlayout is for panel and borderlayout for JFame

        idField.setEditable((false));

        //  form page
        JPanel fields = new JPanel(new GridLayout(8,8,10,10));
        fields.setBorder(new EmptyBorder(20,20,20,20));
        fields.add(new JLabel("EmployeeId:"));
        fields.add(employeeIdField);
        fields.add(new JLabel("First Name:"));
        fields.add(firstNameField);
        fields.add(new JLabel("Last Name:"));
        fields.add(lastNameField);
        fields.add(new JLabel("Email:"));
        fields.add(emailField);
        fields.add(new JLabel("Phone:"));
        fields.add(phoneField);
        fields.add(new JLabel("Role:"));
        fields.add(roleField);
        fields.add(new JLabel("Id(for delete"));
        fields.add(idField);

        // JButton panel
        JPanel formButtons = new JPanel(new FlowLayout());
        formButtons.add(addButton);
        formButtons.add(updateButton);
        formButtons.add(clearButton);
        formButtons.add(showAllButton);
        formButtons.add(deleteButton);

        formPanel.add(fields,BorderLayout.NORTH);
        formPanel.add(formButtons,BorderLayout.SOUTH);

        // Top = form + buttons
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(totalLabel);
        top.add(showAllButton);

        /*table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));*/

        messageLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

        listPanel.add(top,BorderLayout.NORTH);
        listPanel.add(new JScrollPane(table),BorderLayout.CENTER);
        listPanel.add(messageLabel,BorderLayout.SOUTH);

        tableModel.addTableModelListener(e->
            totalLabel.setText("total employees:0"+tableModel.getRowCount()));
    }

    public JPanel getFormPanel(){
        return formPanel;
    }
    public JPanel getListPanel(){
        return listPanel;
    }
    public DefaultTableModel getTableModel(){
        return tableModel;
    }
    public JTable getTable (){
        return table;
    }
    //read the form
    public String getIdText(){
        return idField.getText().trim();
    }
    public String getEmployeeId(){
        return employeeIdField.getText().trim();
    }
    public String getFirstName(){
         return firstNameField.getText().trim();
         }
    public String getLastName(){
         return lastNameField.getText().trim();
         }
    public String getEmail(){ 
        return emailField.getText().trim();
     }
    public String getPhone(){
         return phoneField.getText().trim(); 
        }
    public String getRole(){ 
        return roleField.getText().trim(); 
    }

    // buttons and tables for controller
    public JButton getAddButton(){
         return addButton;
         }
    public JButton getClearButton(){
         return clearButton;
         }
    public JButton getShowButton(){
         return showAllButton;
         }
    public JButton getDeleteButton(){
        return deleteButton;
    }  
    public JButton getShowAllButton(){
        return showAllButton;
    }

    public void addTableSelectionListener(ListSelectionListener listener){
        table.getSelectionModel().addListSelectionListener(listener);
    }

    //show or display employees
    public void displayEmployees(List<Employee>employees){
        tableModel.setRowCount(0);
        for(Employee emp : employees){
            tableModel.addRow(new Object[]{
                 emp.getId(),
                 emp.getEmployeeId(),
                 emp.getFirstName(),
                 emp.getLastName(),
                 emp.getEmail(),
                 emp.getPhone(),
                 emp.getRole()
                });
          }
        }

        public void onAdd(ActionListener listener){
            addButton.addActionListener(listener);
        }
        public void onUpdate(ActionListener listener){
            updateButton.addActionListener(listener);
        }
        public void onClear(ActionListener listener){
            clearButton.addActionListener(listener);
        }
        public void onDelete(ActionListener listener){
            deleteButton.addActionListener(listener);
        }
        public void onShowAll(ActionListener listener){
            showAllButton.addActionListener(listener);
        }
    // Show results
    public void showMessage(String message) {
        messageLabel.setText(message);
    }

    public void clearFields() {
        idField.setText("");
        employeeIdField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        roleField.setText("");
    }
 }

