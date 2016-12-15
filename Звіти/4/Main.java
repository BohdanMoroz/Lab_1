package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

class Chair {
    private String material;
    private String seatForm;
    private String coatingKind;
    private boolean back;
    private boolean coating;
    private int legsNumber;

    public String toString(){
      return
              "Material : " +
        this.getMaterial() +
        "\n" +
        "Seat Form : " +
        this.getSeatForm() +
        "\n" +
        "Coating Kind : " +
        this.getCoatingKind() +
        "\n" +
        "Back : " +
        Boolean.toString(this.getBack()) +
        "\n" +
        "Coating : " +
        Boolean.toString(this.getCoating()) +
        "\n" +
        "Legs Number : " +
        Integer.toString(this.getLegsNumber());
    };

    public void setMaterial(String material){
        this.material = material;
    }
    public  String getMaterial() {
        return  material;
    }

    public void setSeatForm(String seatForm){
        this.seatForm = seatForm;
    }
    public  String getSeatForm(){
        return seatForm;
    }

    public void setCoatingKind(String coatingKind) { this.coatingKind = coatingKind; }
    public  String getCoatingKind() { return coatingKind; }

    public void setBack(boolean back){
        this.back = back;
    }
    public  boolean getBack(){
        return  back;
    }

    public void setCoating(boolean coating) { this.coating = coating; }
    public boolean getCoating() { return  coating; }

    public  void setLegsNumber(int legsNumber) { this.legsNumber = legsNumber; }
    public int getLegsNumber() { return legsNumber; }
}

public class Main {

    public static void main(String[] args) {
        String[] itemsMaterial = {
                "Wood",
                "Metal",
                "Plastic"
        };
        String[] itemsSeatForm = {
                "Square",
                "Rectangle",
                "Circle"
        };
        String[] itemsCoatingKind = {
                "Textile",
                "Leather",
                "None"
        };

        Chair chair = new Chair();

        JFrame frame = new JFrame("Chair");

        JPanel panel = new JPanel();
        JPanel comboBoxPanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();

        JButton btn1 = new JButton("Show all parameters");
        JButton btn2 = new JButton("Set legs number");

        JComboBox comboBoxMaterial = new JComboBox(itemsMaterial);
        JComboBox comboBoxSeatForm = new JComboBox(itemsSeatForm);
        JComboBox comboBoxCoatingKind = new JComboBox(itemsCoatingKind);

        JTextArea textArea = new JTextArea();

        JCheckBox checkBoxBack = new JCheckBox("Back");
        JCheckBox checkBox1Coating = new JCheckBox("Coating");

        JTextField textField = new JTextField();

        JLabel labelMaterial = new JLabel("Material:");
        JLabel labelSeatForm = new JLabel("Seat Form:");
        JLabel labelCoatingKind = new JLabel("Coating Kind:");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);

        frame.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2,2));
        panel.add(comboBoxPanel);
        panel.add(checkBoxPanel);
        panel.add(textFieldPanel);
        panel.add(btn1);
        frame.add(textArea, BorderLayout.CENTER);

        textFieldPanel.setLayout(new GridLayout(2,1));
        textFieldPanel.add(textField);
        textFieldPanel.add(btn2);

        comboBoxPanel.setLayout(new GridLayout(2,3));
        comboBoxPanel.add(labelMaterial);
        comboBoxPanel.add(labelSeatForm);
        comboBoxPanel.add(labelCoatingKind);
        comboBoxPanel.add(comboBoxMaterial);
        comboBoxPanel.add(comboBoxSeatForm);
        comboBoxPanel.add(comboBoxCoatingKind);

        checkBoxPanel.setLayout(new GridLayout(2,1));
        checkBoxPanel.add(checkBoxBack);
        checkBoxPanel.add(checkBox1Coating);

        chair.setMaterial("Wood");
        chair.setSeatForm("Square");
        chair.setCoatingKind("None");
        chair.setLegsNumber(4);

        ActionListener actionListenerMaterial = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chair.setMaterial(comboBoxMaterial.getSelectedItem().toString());
            }
        };
        comboBoxMaterial.addActionListener(actionListenerMaterial);

        ActionListener actionListenerSeatForm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chair.setSeatForm(comboBoxSeatForm.getSelectedItem().toString());
            }
        };
        comboBoxSeatForm.addActionListener(actionListenerSeatForm);

        ActionListener actionListenerCoatingKind = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chair.setCoatingKind(comboBoxCoatingKind.getSelectedItem().toString());
            }
        };
        comboBoxCoatingKind.addActionListener(actionListenerCoatingKind);
        comboBoxCoatingKind.setEnabled(false);

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
            public void changedUpdate(DocumentEvent e) {

            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    chair.setLegsNumber(Integer.parseInt(textField.getText()));
            }
        });
        btn2.setEnabled(false);

        checkBoxBack.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                chair.setBack(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        checkBox1Coating.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    comboBoxCoatingKind.setEnabled(true);
                    chair.setCoatingKind("Textile");
                    chair.setCoating(true);
                } else {
                    comboBoxCoatingKind.setEnabled(false);
                    chair.setCoatingKind("None");
                    chair.setCoating(false);
                };
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(chair.toString());
            }
        });
    }
}
