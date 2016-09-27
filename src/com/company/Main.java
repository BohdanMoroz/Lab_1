package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        JButton btn = new JButton("Start");
        JFrame frame = new JFrame("First");
        JTextField tfield1 = new JTextField("Here is your text");
        JTextField tfield2 = new JTextField("");
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.add(panel, BorderLayout.NORTH);

        panel.setLayout(new BorderLayout());
        panel.add(tfield1, BorderLayout.CENTER);
        panel.add(tfield2, BorderLayout.SOUTH);
        panel.add(btn, BorderLayout.EAST);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tfield1.getText();
               // String textUpperCase;
               // int counter = 0;
              // for (char element : text.toCharArray())
               // {
               //     counter ++;
               // }
                // text = new Integer(counter).toString();
               // textUpperCase = text.substring(0,counter/2);
                tfield2.setText(text.substring(0,text.length()/2).toUpperCase() + text.substring(text.length() / 2,text.length()));
            }
        });
    }
}
