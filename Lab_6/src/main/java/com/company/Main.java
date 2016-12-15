package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static WritingTool fi;
    static boolean isFrame = false;
    public static void main(String[] args) {
        String[] classString = {
                "Pen",
                "Pencil"
        };
        JFrame frame = new JFrame("Choose your class");
        JLabel labelMaterial = new JLabel("Material");
        JLabel labelForm = new JLabel("Color");
        JButton btn1 = new JButton("Show all parameters");
        JButton btn2 = new JButton("Set lenght");
        JComboBox comboBoxClass = new JComboBox(classString);
        JTextArea textArea = new JTextArea();
        JTextField textField = new JTextField();
        JPanel panel = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

        frame.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(1,1));
        panel.add(comboBoxClass);

        ActionListener actionListenerComboBoxClass = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2;
                textArea.setText("");
                String varFrame;
                if(isFrame == true){
                    fi.destructForm();
                };
                switch (comboBoxClass.getSelectedItem().toString()) {
                    case "Pen":
                        fi = new Pen();
                        varFrame = "Pen";
                        fi.toString();
                        fi.loadImage();
                        fi.repaint();
                        break;
                    case "Pencil":
                        fi = new Pencil();
                        varFrame = "Pencil";
                        fi.toString();
                        fi.loadImage();
                        fi.repaint();
                        break;
                    default:
                        throw new IllegalStateException("Incorrect writing tool type");
                        //break;
                }
                isFrame = true;
                frame2 = new JFrame(varFrame);
                fi.buildForm(frame2, labelMaterial, labelForm, btn1, btn2, textArea, textField);
            }
        };
        comboBoxClass.addActionListener(actionListenerComboBoxClass);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(fi.toString());
                fi.strToQR(fi.toString());
//                btn2.set
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().equals("") || textField.getText().equals(null)){
                    btn2.setEnabled(false);
                }
                else{
                    btn2.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (textField.getText().equals("") || textField.getText().equals(null)){
                    btn2.setEnabled(false);
                }
                else{
                    btn2.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fi.setLenght(Integer.parseInt(textField.getText()));
            }
        });
        btn2.setEnabled(false);
    };
}
