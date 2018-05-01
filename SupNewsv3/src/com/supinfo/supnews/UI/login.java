package com.supinfo.supnews.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    private JButton login_bnt;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;

    public login() {
        login_bnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Jeff");
                dashboard dashwindow = new dashboard();
                dashwindow.newDashboard();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login window");
        frame.setContentPane(new login().panel1);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(300,155);
        frame.setResizable(false);


    }
}
