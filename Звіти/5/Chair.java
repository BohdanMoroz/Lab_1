package com.company;

import java.awt.*;

public class Chair extends FornitureItem {
    @Override
    public String[] ik() {
        String[] itemsKind = {
                "Textile",
                "Leather",
                "None"
        };
        return itemsKind;
    }

    @Override
    public String varName1() {
        String pole = "Back";
        return pole;
    };

    @Override
    public String varName2() {
        String pole = "Coating";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Coating";
        return pole;
    };

    @Override
    public String toString() {
        String str;
        String str1 = super.toString();
        String str2 =
                "\n" +
                        "Seat Form : " +
                        this.getForm() +
                        "\n" +
                        "Coating Kind : " +
                        this.getSubMaterial() +
                        "\n" +
                        "Back : " +
                        Boolean.toString(this.getChecker1()) +
                        "\n" +
                        "Coating : " +
                        Boolean.toString(this.getChecker2()) +
                        "\n";
        str = str1 + str2;
        QRCodeGenerator makeQR = new QRCodeGenerator();
        makeQR.codeGen(str);
        loadImage();
        repaint();

        return str1 + str2;
    };

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 256, 256, null);
    }
}
