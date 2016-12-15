package com.company;

import java.awt.*;

public class Pencil extends WritingTool {

    public Pencil() {
        option = "HB";
    }

    @Override
    public void getAllParam() {
        color = this.getColor();
        option = this.getOption();
        material = this.getMaterial();
        checker1 = this.getChecker1();
        checker2 = this.getChecker2();
        lenght = this.getLenght();
    };

    @Override
    public String[] ik() {
        String[] itemsKind = {
                "B3",
                "B2",
                "B1",
                "HB",
                "H1",
                "H2",
                "H3"
        };
        return itemsKind;
    }

    @Override
    public String varName1() {
        String pole = "Sharpened";
        return pole;
    };

    @Override
    public String varName2() {
        String pole = "Eraser";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Softness";
        return pole;
    };

    @Override
    public String toString(){
        String str;
        String str1 = super.toString();
        String str2 =
                "\n" +
//                        "Color : " +
//                        this.getColor() +
//                        "\n" +
                        "Softness : " +
                        option +
                        "\n" +
                        "Sharpened : " +
                        checker1 +
                        "\n" +
                        "Eraser : " +
                        checker2 +
                        "\n";

        str = str1 + str2;

        return str;
    };

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 256, 256, null);
    }

}
