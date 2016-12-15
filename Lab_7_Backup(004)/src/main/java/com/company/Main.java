package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Main {
    static WritingTool objWritingTool;
    static JPanel object_0;
    static Pen object_1;
    static Pencil object_2;

    public static void main(String[] args) {
        JFrame mainFrame = buildMainFrame();
        mainFrame.setVisible(true);
    }

    private static JFrame buildMainFrame() {
        JFrame mainFrame = initMainFrame();
        JTabbedPane tabbedPane = buildTabbledPane();
        mainFrame.add(tabbedPane);
        return mainFrame;
    }

    private static JTabbedPane buildTabbledPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel mainPanel = buildMainPanel(tabbedPane);
        tabbedPane.add("Main", mainPanel);
        return tabbedPane;
    }

    private static JPanel buildMainPanel(JTabbedPane tabbedPane) {
        JPanel mainPanel = initMainPanel();
        JPanel functionalPanel = buildFunctionalPanel(tabbedPane);
        mainPanel.add(functionalPanel);
        return mainPanel;
    }

    private static JPanel buildFunctionalPanel(JTabbedPane tabbedPane) {
        JPanel functionalPanel = initFunctionalPanel();

        JLabel labelClass = new JLabel("Class:");
        List listClass = new ArrayList(3);

        JComboBox comboBoxClass = buildComboBoxClass(tabbedPane, labelClass, listClass);
        JButton btnRandom = buildBtnRandom(comboBoxClass, listClass);

        functionalPanel.add(labelClass);
        functionalPanel.add(btnRandom);
        functionalPanel.add(comboBoxClass);
        return functionalPanel;
    }

    private static JFrame initMainFrame() {
        JFrame mainFrame = new JFrame("Choose your class");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 400);
        return mainFrame;
    }

    private static JPanel initFunctionalPanel() {
        JPanel functionalPanel = new JPanel();
        functionalPanel.setLayout(new GridLayout(3,1));
        return functionalPanel;
    }

    private static JPanel initMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        return mainPanel;
    }

    private static JComboBox buildComboBoxClass(final JTabbedPane tabbedPane, final JLabel labelClass, final List listClass) {
        JComboBox comboBoxClass = new JComboBox();
        comboBoxClass.setPreferredSize(new Dimension(215,25));

        ActionListener actionListenerLabel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isObject = false;

                labelClass.setText(null);
                labelClass.setText("Class: " + (String) comboBoxClass.getSelectedItem().toString());

                if (tabbedPane.indexOfTab("Class object") == 1)
                    tabbedPane.remove(1);

                WritingTool oldWritingTool = null;
                WritingTool newWritingTool = null;

                switch (comboBoxClass.getSelectedItem().toString()) {
                    case "javax.swing.JPanel" :
                        break;
                    case "com.company.Pen":
                        oldWritingTool = object_1;
                        object_1 = new Pen();
                        newWritingTool = object_1;
                        break;
                    case "com.company.Pencil":
                        oldWritingTool = object_2;
                        object_2 = new Pencil();
                        newWritingTool = object_2;
                        break;
                    default:
                        throw new IllegalStateException("Incorrect type");
                        //break;
                }

                if (oldWritingTool != null) {
                    int index = listClass.indexOf(oldWritingTool);
                    listClass.remove(oldWritingTool);
                    listClass.add(index, newWritingTool);
                    objWritingTool = newWritingTool;
                    objWritingTool.changeImage();
                    isObject = true;
                }

                JPanel panelTab = new JPanel();
                if (isObject) {
                    panelTab = objWritingTool.buildMainPanel();
                }
                else {
                    JButton btnExit = new JButton("Exit");
                    btnExit.setPreferredSize(new Dimension(300,30));
                    btnExit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tabbedPane.remove(1);
                        }
                    });
                    panelTab.add(btnExit);
                }
                tabbedPane.addTab("Class object", panelTab);
            }
        };
        comboBoxClass.addActionListener(actionListenerLabel);
        return comboBoxClass;
    }

    private static JButton buildBtnRandom(final JComboBox comboBoxClass, final List listClass) {
        JButton btnRandom = new JButton("Create and show collection");
        btnRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listRandom = new ArrayList(3);
                Random random = new Random();
                String randomNumber;
                int indexCounter = 0; // Index*

                btnRandom.setEnabled(false);

                for (int i = 0; i < 3; i++) {
                    listRandom.add(i, Integer.toString(i));
                }
                while (listRandom.size()>0)
                {
                    randomNumber = Integer.toString(random.nextInt(3));
                    for (int i = 0; i < listRandom.size(); i++) {
                        if (listRandom.get(i).equals(randomNumber)) {
                            listRandom.remove(randomNumber);
                            switch (randomNumber) {
                                case "0" :
                                    object_0 = new JPanel();
                                    listClass.add(indexCounter, object_0);
                                    break;
                                case "1" :
                                    object_1 = new Pen();
                                    listClass.add(indexCounter, object_1);
                                    break;
                                case "2" :
                                    object_2 = new Pencil();
                                    listClass.add(indexCounter, object_2);
                                    break;
                            }
                            indexCounter++;
                        }
                    }
                }
                for (int i = 0; i<3; i++) {
                    comboBoxClass.addItem(listClass.get(i).getClass().getName());
                }
            }
        });
        return btnRandom;
    }
}
