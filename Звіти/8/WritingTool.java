package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    public int lenght;
    public String material;
    public String color;
    public String option;
    public boolean checker1;
    public boolean checker2;
    public boolean imgLoaded;
    public Image image;

    public WritingTool() {
        material = "Wood";
        lenght = 10;
        color = "Black";
        checker1 = false;
        checker2 = false;
        loadImage();
        imgLoaded = false;
    };

    public String toStr(){
        return
                "Material : " +
                        getMaterial() +
                        "\n" +
                        "Color : " +
                        getColor() +
                        "\n" +
                        "Lenght : " +
                        getLenght() +
                        "cm";
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

    public String[] itemsKind() {
        String[] items = {
                "None"
        };
        return items;
    }

    public String nameCheckBox_1(){
        String pole = "";
        return pole;
    };

    public String nameCheckBox_2(){
        String pole = "";
        return pole;
    };

    public String labelText(){
        String pole = "";
        return pole;
    };

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

    public void changeImage() {
        loadImage();
        repaint();
    }

    public JPanel buildMainPanel() {
        JPanel mainPanel = initMainPanel();
        JPanel panel = buildPanel();
        JPanel imgPanel = buildImgPanel();
        mainPanel.add(panel);
        mainPanel.add(imgPanel);
        return mainPanel;
    };

    private JPanel initMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        return mainPanel;
    }

    private JPanel initComboBoxPanel() {
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridLayout(2,3));
        return comboBoxPanel;
    }

    private JPanel initPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,2));
        panel.setPreferredSize(new Dimension(490,100));
        return panel;
    }

    private JPanel initCheckBoxPanel() {
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(2,1));
        return checkBoxPanel;
    }

    private JPanel initImgPanel() {
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new GridLayout(1, 2, 3, 3));
        imgPanel.setPreferredSize(new Dimension(490,256));
        return imgPanel;
    }

    private JPanel initTextFieldPanel() {
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new GridLayout(2,1));
        return textFieldPanel;
    }

    private JPanel buildComboBoxPanel() {
        JPanel comboBoxPanel = initComboBoxPanel();
        JLabel labelExt = new JLabel(labelText());
        JLabel labelMaterial = new JLabel("Material");
        JLabel labelColor = new JLabel("Color");
        JComboBox comboBoxMaterial = new JComboBox(this.itemsMaterial);
        JComboBox comboBoxColor = new JComboBox(this.itemsColor);
        JComboBox comboBoxOption = new JComboBox(itemsKind());

        comboBoxPanel.add(labelMaterial);
        comboBoxPanel.add(labelColor);
        comboBoxPanel.add(labelExt);
        comboBoxPanel.add(comboBoxMaterial);
        comboBoxPanel.add(comboBoxColor);
        comboBoxPanel.add(comboBoxOption);

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

        ActionListener actionListenerOption = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOption(comboBoxOption.getSelectedItem().toString());
            }
        };
        comboBoxOption.addActionListener(actionListenerOption);
        comboBoxOption.setSelectedItem(option);
        return comboBoxPanel;
    }

    private JPanel buildCheckBoxPanel() {
        JPanel checkBoxPanel = initCheckBoxPanel();
        JCheckBox checkBox_1 = new JCheckBox(nameCheckBox_1());
        JCheckBox checkBox_2 = new JCheckBox(nameCheckBox_2());
        checkBoxPanel.add(checkBox_1);
        checkBoxPanel.add(checkBox_2);

        checkBox_1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setChecker1(e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        checkBox_2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                setChecker2(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        return checkBoxPanel;
    }

    private JButton buildBtnSetLenght(JTextField textField) {
        JButton btnSetLenght = new JButton("Set lenght");
        btnSetLenght.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setLenght(Integer.parseInt(textField.getText()));
            }
        });
        btnSetLenght.setEnabled(false);
        return btnSetLenght;
    }

    private JTextField buildTextField() {
        JTextField textField = new JTextField();
        JButton btnSetLenght = buildBtnSetLenght(textField);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().equals("") || textField.getText().equals(null)){
                    btnSetLenght.setEnabled(false);
                }
                else{
                    btnSetLenght.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (textField.getText().equals("") || textField.getText().equals(null)){
                    btnSetLenght.setEnabled(false);
                }
                else{
                    btnSetLenght.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        return textField;
    }

    private JPanel buildTextFieldPanel() {
        JPanel textFieldPanel = initTextFieldPanel();
        JTextField textField = buildTextField();
        JButton btnSetLenght = buildBtnSetLenght(textField);

        textFieldPanel.add(textField);
        textFieldPanel.add(btnSetLenght);
        return textFieldPanel;
    }

    JTextArea textArea = new JTextArea();

    private JButton buildBtnShowAllParameters() {
        JButton btnShowAllParameters = new JButton("Show all parameters");
        btnShowAllParameters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(toStr());
                strToQR(toStr());
            }
        });
        return btnShowAllParameters;
    }

    private JPanel buildPanel() {
        JPanel panel = initPanel();
        JPanel comboBoxPanel = buildComboBoxPanel();
        JPanel checkBoxPanel = buildCheckBoxPanel();
        JPanel textFieldPanel = buildTextFieldPanel();
        JButton btnShowAllParameters = buildBtnShowAllParameters();
        panel.add(comboBoxPanel);
        panel.add(checkBoxPanel);
        panel.add(textFieldPanel);
        panel.add(btnShowAllParameters);
        return panel;
    }

    private JPanel buildImgPanel() {
        JPanel imgPanel = initImgPanel();
        imgPanel.add(textArea);
        imgPanel.add(this);

        return imgPanel;
    }

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
