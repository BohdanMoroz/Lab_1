package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class FornitureItem extends JPanel{
    private String material;
    private int legsNumber;
    public String form;
    public String subMaterial;
    public boolean checker1;
    public boolean checker2;
    public JFrame mainFrame;

    public FornitureItem() {
        material = "Wood";
        legsNumber = 4;
        form = "Square";
        checker1 = false;
        checker2 = false;
        loadImage();


    };

    String[] itemsMaterial = {
            "Wood",
            "Metal",
            "Plastic"
    };

    String[] itemsForm = {
            "Square",
            "Rectangle",
            "Circle"
    };

    public String[] ik() {
        String[] itemsKind = {
                "None"
        };
        return itemsKind;
    }

    public String varName1(){
        String pole = "";
        return pole;
    };

    public String varName2(){
        String pole = "";
        return pole;
    };

    public String labelText(){
        String pole = "";
        return pole;
    };

    public Image image;


    public void loadImage() {
        try {
            image = ImageIO.read(new File("BM.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 256, 256, null);
    }

    public void buildForm(JFrame frame, JLabel labelMaterial, JLabel labelForm, JButton btn1, JButton btn2, JTextArea textArea, JTextField textField) {
        mainFrame = frame;
        JPanel panel = new JPanel();
        JPanel comboBoxPanel = new JPanel();
        JPanel checkBoxPanel = new JPanel();
        JPanel textFieldPanel = new JPanel();
        JPanel imgPanel = new JPanel();

        JCheckBox checkBox1 = new JCheckBox(varName1());
        JCheckBox checkBox2 = new JCheckBox(varName2());

        JLabel labelExt = new JLabel(labelText());

        JComboBox comboBoxMaterial = new JComboBox(this.itemsMaterial);
        JComboBox comboBoxForm = new JComboBox(this.itemsForm);

        JComboBox comboBoxSubMaterial = new JComboBox(ik());


//        FornitureItem game = new FornitureItem();
//        game.setSize(256, 256);






        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(500, 355);
        mainFrame.setVisible(true);

        imgPanel.setLayout(new GridLayout(1,2));
        imgPanel.add(textArea);
        imgPanel.add(this);

        mainFrame.add(panel, BorderLayout.NORTH);
        panel.setLayout(new GridLayout(2,2));
        panel.add(comboBoxPanel);
        panel.add(checkBoxPanel);
        panel.add(textFieldPanel);
        panel.add(btn1);
        mainFrame.add(imgPanel, BorderLayout.CENTER);
//        mainFrame.add(textArea, BorderLayout.CENTER);

        textFieldPanel.setLayout(new GridLayout(2,1));
        textFieldPanel.add(textField);
        textFieldPanel.add(btn2);

        comboBoxPanel.setLayout(new GridLayout(2,3));
        comboBoxPanel.add(labelMaterial);
        comboBoxPanel.add(labelForm);
        comboBoxPanel.add(labelExt);
        comboBoxPanel.add(comboBoxMaterial);
        comboBoxPanel.add(comboBoxForm);
        comboBoxPanel.add(comboBoxSubMaterial);

        checkBoxPanel.setLayout(new GridLayout(2,1));
        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);

        ActionListener actionListenerMaterial = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMaterial(comboBoxMaterial.getSelectedItem().toString());
            }
        };
        comboBoxMaterial.addActionListener(actionListenerMaterial);

        ActionListener actionListenerForm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setForm(comboBoxForm.getSelectedItem().toString());
            }
        };
        comboBoxForm.addActionListener(actionListenerForm);

        ActionListener actionListenerTC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSubMaterial(comboBoxSubMaterial.getSelectedItem().toString());
            }
        };
        comboBoxSubMaterial.addActionListener(actionListenerTC);

        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setChecker1(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        comboBoxSubMaterial.setEnabled(false);

        setSubMaterial("None");
        checkBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    comboBoxSubMaterial.setEnabled(true);
                    setSubMaterial(comboBoxSubMaterial.getSelectedItem().toString());
                    setChecker2(true);
                } else {
                    comboBoxSubMaterial.setEnabled(false);
                    setSubMaterial("None");
                    setChecker2(false);
                };
            }
        });
    };

    public void destructForm() {
        mainFrame.dispose();
        mainFrame.setVisible(false);
    };

    public String toString(){
        return
                "Material : " +
                        this.getMaterial() +
                        "\n" +
                        "Legs Number : " +
                        Integer.toString(this.getLegsNumber());
    };

    public void setChecker1(boolean checker1){
        this.checker1 = checker1;
    }
    public  boolean getChecker1(){
        return  checker1;
    }

    public void setChecker2(boolean checker2){
        this.checker2 = checker2;
    }
    public  boolean getChecker2(){
        return  checker2;
    }

    public void setForm(String form){
        this.form = form;
    }
    public  String getForm() {
        return  form;
    }

    public void setSubMaterial(String subMaterial){
        this.subMaterial = subMaterial;
    }
    public  String getSubMaterial() {
        return  subMaterial;
    }

    public void setMaterial(String material){
        this.material = material;
    }
    public  String getMaterial() {
        return  material;
    }

    public  void setLegsNumber(int legsNumber) { this.legsNumber = legsNumber; }
    public int getLegsNumber() { return legsNumber; }
}
