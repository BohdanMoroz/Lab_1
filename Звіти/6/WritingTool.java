package com.company; // How to delete image?

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

public class WritingTool extends JPanel{
    public String material;
    public int lenght;
    public String color;
    public String option;
    public boolean checker1;
    public boolean checker2;
    public JFrame mainFrame;
    public boolean imgLoaded;

    public WritingTool() {
        material = "Wood";
        lenght = 10;
        color = "Black";
        checker1 = false;
        checker2 = false;
        loadImage();
        imgLoaded = false;
    };

    public void getAllParam() {
        color = this.getColor();
        option = this.getOption();
        material = this.getMaterial();
        checker1 = this.getChecker1();
        checker2 = this.getChecker2();
        lenght = this.getLenght();
    };

    String[] itemsMaterial = {
            "Wood",
            "Rubber",
            "Plastic"
    };

    String[] itemsColor = {
            "Black",
            "Blue",
            "Red",
            "Green",
            "Violet"
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

    public void strToQR(String str)
    {
        QRCodeGenerator makeQR = new QRCodeGenerator();
        makeQR.codeGen(str);
        loadImage();
        repaint();
    }

    public void loadImage() {
        if (imgLoaded == false) {
            try {
                image = ImageIO.read(new File("Clear.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgLoaded = true;
        }
        else {
            try {
                image = ImageIO.read(new File("Image.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 256, 256, null);
    }

    public void buildForm(JFrame frame, JLabel labelMaterial, JLabel labelColor, JButton btn1, JButton btn2, JTextArea textArea, JTextField textField) {
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
        JComboBox comboBoxColor = new JComboBox(this.itemsColor);
        JComboBox comboBoxOption = new JComboBox(ik());
        JButton btnGetAllParam = new JButton("GetAllParam");

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

        textFieldPanel.setLayout(new GridLayout(2,1));
        textFieldPanel.add(textField);
        textFieldPanel.add(btn2);

        comboBoxPanel.setLayout(new GridLayout(2,3));
        comboBoxPanel.add(labelMaterial);
        comboBoxPanel.add(labelColor);
        comboBoxPanel.add(labelExt);
        comboBoxPanel.add(comboBoxMaterial);
        comboBoxPanel.add(comboBoxColor);
        comboBoxPanel.add(comboBoxOption);

        checkBoxPanel.setLayout(new GridLayout(2,1));
        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);
        checkBoxPanel.add(btnGetAllParam);

        btnGetAllParam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getAllParam();
            }
        });

        ActionListener actionListenerMaterial = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMaterial(comboBoxMaterial.getSelectedItem().toString());
            }
        };
        comboBoxMaterial.addActionListener(actionListenerMaterial);

        ActionListener actionListenerColor = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setColor(comboBoxColor.getSelectedItem().toString());
            }
        };
        comboBoxColor.addActionListener(actionListenerColor);

        ActionListener actionListenerTC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOption(comboBoxOption.getSelectedItem().toString());
            }
        };
        comboBoxOption.addActionListener(actionListenerTC);

        comboBoxOption.setSelectedItem(option);

        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setChecker1(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        checkBox2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setChecker2(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
    };

    public void destructForm() {
        mainFrame.dispose();
        mainFrame.setVisible(false);
    };

//    public String toString(){
//        return
//                "Material : " +
//                        this.getMaterial() +
//                        "\n" +
//                        "Color : " +
//                        this.getColor() +
//                        "\n" +
//                        "Lenght : " +
//                        Integer.toString(this.getLenght()) +
//                        "cm";
//    };

    public String toString(){
        return
                "Material : " +
                        material +
                        "\n" +
                        "Color : " +
                        color +
                        "\n" +
                        "Lenght : " +
                        lenght +
                        "cm";
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

    public void setColor(String color){
        this.color = color;
    }
    public  String getColor() {
        return  color;
    }

    public void setOption(String option){
        this.option = option;
    }
    public  String getOption() {
        return  option;
    }

    public void setMaterial(String material){
        this.material = material;
    }
    public  String getMaterial() {
        return  material;
    }

    public  void setLenght(int lenght) { this.lenght = lenght; }
    public int getLenght() { return lenght; }
}
