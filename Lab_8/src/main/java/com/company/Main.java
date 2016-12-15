package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static WritingTool objWritingTool;
    static Pencil object_0;
    static Pen object_1;

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
        PencilCase pencilCase = new PencilCase();
        JComboBox comboBoxClass = initComboBoxClass();
        JComboBox comboBoxCase = initComboBoxCase();
        JButton btnTake = buildBtnTake(tabbedPane, comboBoxCase, pencilCase);
        JButton btnRemoveItem = buildBtnRemoveItem(comboBoxCase, pencilCase, btnTake, tabbedPane);
        JButton btnRandom = buildBtnAddItem(comboBoxClass, comboBoxCase, btnRemoveItem, btnTake);

        functionalPanel.add(btnRemoveItem);
        functionalPanel.add(btnTake);
        functionalPanel.add(comboBoxCase);
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
        functionalPanel.setLayout(new GridLayout(5,1));
        return functionalPanel;
    }

    private static JPanel initMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        return mainPanel;
    }

    private static JComboBox initComboBoxClass() {
        String[] classItems = {
                "Pencil",
                "Pen"
        };
        JComboBox comboBoxClass = new JComboBox(classItems);
        comboBoxClass.setPreferredSize(new Dimension(215,25));
        return comboBoxClass;
    }

    private static JComboBox initComboBoxCase() {
        JComboBox comboBoxClass = new JComboBox();
        comboBoxClass.setPreferredSize(new Dimension(215,25));
        return comboBoxClass;
    }

    public static void deleteTab(JTabbedPane tabbedPane) {
        if (tabbedPane.indexOfTab("com.company.Pencil") == 1 || tabbedPane.indexOfTab("com.company.Pen") == 1)
            tabbedPane.remove(1);
    }

    private static JButton buildBtnRemoveItem(JComboBox comboBoxCase, PencilCase pencilCase, JButton btnTake, JTabbedPane tabbedPane) {
        JButton btnRemoveItem = new JButton("Remove current item");
        btnRemoveItem.setEnabled(false);
        btnRemoveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencilCase.removeElement(comboBoxCase.getSelectedIndex());
                comboBoxCase.removeItemAt(comboBoxCase.getSelectedIndex());
                EnableBtn(comboBoxCase, btnRemoveItem);
                EnableBtn(comboBoxCase, btnTake);
                deleteTab(tabbedPane);
            }
        });
        return btnRemoveItem;
    }

    private static JButton buildBtnTake(JTabbedPane tabbedPane, JComboBox comboBoxCase, PencilCase pencilCase) {
        JButton btnTake = new JButton("Take");
        btnTake.setEnabled(false);
        btnTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBoxCase.getSelectedItem().toString()) {
                    case "com.company.Pencil" :
                        objWritingTool = (Pencil) pencilCase.getObject(comboBoxCase.getSelectedIndex());
                        break;
                    case "com.company.Pen" :
                        objWritingTool = (Pen) pencilCase.getObject(comboBoxCase.getSelectedIndex());
                        break;
                }
                try {
                    objWritingTool = objWritingTool.getClass().newInstance();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }

//                objWritingTool.imgLoaded = false;

                deleteTab(tabbedPane);

                JPanel panelTab;
                panelTab = objWritingTool.buildMainPanel();
                objWritingTool.changeImage();
                tabbedPane.add(comboBoxCase.getSelectedItem().toString(), panelTab);
            }
        });

        return btnTake;
    }

    private static JButton buildBtnAddItem(final JComboBox comboBoxClass, final JComboBox comboBoxCase, JButton btnRemoveItem, JButton btnTake) {
        JButton btnAddItem = new JButton("Add item");
        PencilCase pencilCase = new PencilCase();

        btnAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBoxClass.getSelectedItem().toString()) {
                    case "Pencil" :
                        objWritingTool = new Pencil();
                        break;
                    case "Pen" :
                        objWritingTool = new Pen();
                        break;
                    default:
                        throw new IllegalStateException("Incorrect type");
                }
                pencilCase.addElement(objWritingTool);
                comboBoxCase.removeAllItems();
                for (int index=0; index< pencilCase.listclass.size(); index++) {
                    comboBoxCase.addItem(pencilCase.listclass.get(index).getClass().getName());
                }
                comboBoxCase.setSelectedIndex(0);
                EnableBtn(comboBoxCase, btnRemoveItem);
                EnableBtn(comboBoxCase, btnTake);
            }
        });
        return btnAddItem;
    }

    private static void EnableBtn(JComboBox comboBoxCase, JButton button) {
                if (comboBoxCase.getItemCount() > 0)
                    button.setEnabled(true);
                else
                    button.setEnabled(false);
    }
}
