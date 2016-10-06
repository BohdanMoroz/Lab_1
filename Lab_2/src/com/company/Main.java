package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Pencil {
    float sizeOfEraser;
    float sizeOfGraphite;
    float sizeOfPencil;
    float angle;
    float flexibility;
    float power;
    int sec;
    boolean broken;

    @Override
    public String toString() {
        return "Pencil{" +
                "sizeOfEraser=" + sizeOfEraser +
                ", sizeOfGraphite=" + sizeOfGraphite +
                ", sizeOfPencil=" + sizeOfPencil +
                ", angle=" + angle +
                ", flexibility=" + flexibility +
                ", sec=" + sec +
                ", broken=" + broken +

                '}';
    }

    void create (float e, float g, float p, float a,float f){
        sizeOfEraser = e;
        sizeOfGraphite = g;
        sizeOfPencil = p;
        angle = a;
        flexibility = f;
        broken = false;
    }

    void write(int s){
        sec = s;
        if (broken == false) {
            for (int i = 1; i <= sec; i++) {
                if (sizeOfGraphite <= 0) {
                    System.out.println("No graphite");
                    break;
                } else {
                    sizeOfGraphite = sizeOfGraphite - 1;
                    sizeOfPencil = sizeOfPencil - 1;
                    angle = angle + 5;
                }
            }
        }
        else{
            System.out.println("Pencil is broken");
        }
    };

    void erase(int s){
        sec = s;
        if (broken == false) {
            for (int i = 1; i < sec; i++) {
                if (sizeOfEraser < 0) {
                    System.out.println("No eraser");
                    break;
                } else {
                    sizeOfEraser = sizeOfEraser - 1;
                    sizeOfPencil = sizeOfPencil - 1;
                }
            }
        }
        else {
            System.out.println("Pencil is broken");
        }
    }

    void bend(int s, float p){
        sec = s;
        power = p;
        for (int i = 1 ; i < sec ; i++) {
            if (broken == true){
                System.out.println("Pencil is broken");
                break;
            }
            if (sec * power >= flexibility)
                broken = true;
        }
    }

    void shape(int s){
        sec = s;
        if (broken == false) {
            for (int i = 1; i <= sec; i++) {
                if (angle >= 20) {
                    sizeOfPencil = sizeOfPencil - 1;
                    sizeOfGraphite = sizeOfGraphite - 1;
                    angle = angle - 5;
                } else {
                    System.out.println("Shaped");
                    break;
                }
            }
        }
        else{
            System.out.println("Pencil is broken");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Second");
        JButton btn1 = new JButton("Create");
        JButton btn2 = new JButton("Write");
        JButton btn3 = new JButton("Erase");
        JButton btn4 = new JButton("Bend");
        JButton btn5 = new JButton("Shape");
        JPanel panel = new JPanel();
        Pencil pencil = new Pencil();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);
        frame.setVisible(true);

        frame.add(btn1, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        panel.setLayout(new GridLayout(2,2));
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencil.create(25, 50, 1000, 20, 40);
                System.out.println(pencil);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencil.write(5);
                System.out.println(pencil);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencil.erase(5);
                System.out.println(pencil);
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencil.bend(5, 10);
                System.out.println(pencil);
            }
        });

        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pencil.shape(5);
                System.out.println(pencil);
            }
        });
    }
}
