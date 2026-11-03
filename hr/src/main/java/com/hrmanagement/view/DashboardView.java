package com.hrmanagement.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardView extends JFrame {
    private final EmployeeView employeeView;

    private final CardLayout cardLayout= new CardLayout();
    private final JPanel content = new JPanel(cardLayout); 

    private final JButton dashBtn = new JButton("Dashboard");
    private final JButton addBtn = new JButton("Add employees");
    private final JButton empBtn = new JButton("Employees");
    private final JButton logOutBtn = new JButton("logout");

    private final JLabel totalLabel = new JLabel("total employees:0");

    public DashboardView(EmployeeView employeeView){
        this.employeeView = employeeView;
        setTitle("HR Dashboard");
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //sidebar
        JPanel sideBar = new JPanel(new GridLayout(4,1,0,5));
        sideBar.add(dashBtn);
        sideBar.add(addBtn);
        sideBar.add(empBtn);
        sideBar.add(logOutBtn);

        //home page
        JPanel home = new JPanel(new BorderLayout());
        home.add(totalLabel,BorderLayout.CENTER);
        //cards
        content.add(home,"HOME");
        content.add(employeeView.getFormPanel(),"FORM");
        content.add(employeeView.getListPanel(),"LIST");
                

        add(sideBar,BorderLayout.WEST);
        add(content,BorderLayout.CENTER);

        //BUTTONS ACTIONS
        dashBtn.addActionListener(e-> cardLayout.show(content,"HOME"));
        empBtn.addActionListener(e-> {
            cardLayout.show(content,"LIST");
            employeeView.getShowAllButton().doClick();//loads all employes
        });
        addBtn.addActionListener(e-> cardLayout.show(content,"LIST"));
        logOutBtn.addActionListener(e->{
            //close everything close the app
            dispose();
            System.exit(0);
        });

    }
    public void setTotal(int total){
        totalLabel.setText("Total employees:"+total);
    }
    private JPanel cardLayout(String title,JLabel value){
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder(title));
        value.setFont(new Font("Arial",Font.BOLD,36));
        p.add(value,BorderLayout.CENTER);
        return p;
    }
    public void onLogout(ActionListener listener){
        logOutBtn.addActionListener(listener);
    }
}