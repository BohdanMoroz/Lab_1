package com.company;

import java.awt.*;

public class Table extends FornitureItem {


    @Override
    public String[] ik() {
        String[] itemsKind = {
                "Glossiness",
                "Mate",
                "None"
        };
        return itemsKind;
    }

    @Override
    public String varName1() {
        String pole = "Drawer";
        return pole;
    };

    @Override
    public String varName2() {
        String pole = "Painting";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Painting";
        return pole;
    };

    @Override
    public String toString(){
        String str;
        String str1 = super.toString();
        String str2 =
                "\n" +
                        "Table Form : " +
                        this.getForm() +
                        "\n" +
                        "Painting Kind : " +
                        this.getSubMaterial() +
                        "\n" +
                        "Drawer : " +
                        Boolean.toString(this.getChecker1()) +
                        "\n" +
                        "Painting : " +
                        Boolean.toString(this.getChecker2()) +
                        "\n";

        str = str1 + str2;
        QRCodeGenerator qrcg = new QRCodeGenerator();
        qrcg.maqin(str);
        loadImage();
        repaint();

        return str;
    };

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, 256, 256, null);
    }

}
