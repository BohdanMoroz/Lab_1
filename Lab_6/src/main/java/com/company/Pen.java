package com.company;

import java.awt.*;

public class Pen extends WritingTool {
    
    public Pen() {
        option = "Medium";
//        getAllParam();
    };

    @Override
    public String[] ik() {
        String[] itemsKind = {
                "Low",
                "Medium",
                "High"
        };
        return itemsKind;
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
    public String varName1() {
        String pole = "Ink";
        return pole;
    };

    @Override
    public String varName2() {
        String pole = "On";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Thickness";
        return pole;
    };

    @Override
    public String toString() {
        String str;
        String str1 = super.toString();
        String str2 =
                "\n" +
//                        "Color : " +
//                        this.getColor() +
//                        "\n" +
                        "Thickness : " +
                        option +
                        "\n" +
                        "Ink : " +
                        checker1 +
                        "\n" +
                        "On : " +
                        checker2 +
                        "\n";
        str = str1 + str2;

        return str;
    };

}
