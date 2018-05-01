package com.supinfo.supnews.UI;

import javax.swing.*;

public class dashboard {
    private JPanel dash_panel;

    public static void newDashboard() {
        JFrame frame = new JFrame("dashboard");
        frame.setContentPane(new dashboard().dash_panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
