package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static WritingTool objWritingTool;
    static JPanel obj0;
    static Pen obj1;
    static Pencil obj2;

    public static void main(String[] args) {
        JFrame frameMain = buildMainFrame();
        frameMain.setVisible(true);
    }

    private static JFrame buildMainFrame() {
        JFrame frameMain = initMainFrame();
        JTabbedPane tabbedPane = buildTabbedPane();
        frameMain.add(tabbedPane);
        return frameMain;
    }

    private static JTabbedPane buildTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panelMain = buildMainPanel(tabbedPane);
        tabbedPane.add("Main", panelMain);
        return tabbedPane;
    }

    private static JPanel buildMainPanel(JTabbedPane tabbedPane) {
        JPanel panelMain = initMainPanel();
        JPanel panelObIt = buildPanelObIt(tabbedPane);
        panelMain.add(panelObIt);
        return panelMain;
    }

    private static JPanel buildPanelObIt(JTabbedPane tabbedPane) {
        JPanel panelObIt = initPanelObIt();

        JLabel labelClass = new JLabel("Class:");
        List listClass = new ArrayList(3);

        JComboBox comboBoxClass = buildComboBoxClass(tabbedPane, labelClass, listClass);
        JButton btnRandom = buildBtnRandom(comboBoxClass, listClass);

        panelObIt.add(labelClass);
        panelObIt.add(comboBoxClass);
        panelObIt.add(btnRandom);
        return panelObIt;
    }

    private static JPanel initPanelObIt() {
        JPanel panelObIt = new JPanel();
        panelObIt.setLayout(new GridLayout(3, 1));
        return panelObIt;
    }

    private static JPanel initMainPanel() {
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new FlowLayout());
        return panelMain;
    }

    private static JComboBox buildComboBoxClass(final JTabbedPane tabbedPane, final JLabel labelClass, final List listClass) {
        JComboBox comboBoxClass = new JComboBox();
        comboBoxClass.setPreferredSize(new Dimension(215, 25));
        
        ActionListener actionListenerLabel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index;
                boolean objFS = false;

                labelClass.setText(null);
                labelClass.setText("Class: " + (String) comboBoxClass.getSelectedItem().toString());

                if (tabbedPane.indexOfTab("Class object") == 1)
                    tabbedPane.remove(1);
                switch (comboBoxClass.getSelectedItem().toString()) {
                    case "javax.swing.JPanel":
                        break;
                    case "com.company.Pen":
                        index = listClass.indexOf(obj1);
                        listClass.remove(obj1);
                        obj1 = new Pen();
                        listClass.add(index, obj1);
                        objWritingTool = obj1;
                        objWritingTool.changeImage();
                        objFS = true;
                        break;
                    case "com.company.Pencil":
                        index = listClass.indexOf(obj2);
                        listClass.remove(obj2);
                        obj2 = new Pencil();
                        listClass.add(index, obj2);
                        objWritingTool = obj2;
                        objWritingTool.changeImage();
                        objFS = true;
                        break;
                    default:
                        throw new IllegalStateException("Incorrect type");
                        //break;
                }
                JPanel panelTab = new JPanel();
                if (objFS) {
                    panelTab = objWritingTool.buildForm();
                } else {
                    JButton btnExit = new JButton("Exit");
                    btnExit.setPreferredSize(new Dimension(300, 30));
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
                Random random = new Random();
                List<String> listRandom = new ArrayList(3);
                int counterRandom;
                int counterSwitch = 0; // Index*
                String counterString;

                btnRandom.setEnabled(false);

                for (int i = 0; i < 3; i++) {
                    listRandom.add(i, Integer.toString(i));
                }
                while (listRandom.size() > 0) {
                    counterRandom = random.nextInt(3);
                    counterString = Integer.toString(counterRandom);
                    for (int i = 0; i < listRandom.size(); i++) {
                        if (listRandom.get(i).equals(counterString)) {
                            listRandom.remove(counterString);
                            switch (counterString) {
                                case "0":
                                    obj0 = new JPanel();
                                    listClass.add(counterSwitch, obj0);
                                    break;
                                case "1":
                                    obj1 = new Pen();
                                    listClass.add(counterSwitch, obj1);
                                    break;
                                case "2":
                                    obj2 = new Pencil();
                                    listClass.add(counterSwitch, obj2);
                                    break;
                            }
                            counterSwitch++;
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    comboBoxClass.addItem(listClass.get(i).getClass().getName());
                }
            }
        });
        return btnRandom;
    }

    private static JFrame initMainFrame() {
        JFrame frameMain = new JFrame("Choose your class");
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(500, 400);
        return frameMain;
    }

    ;
}