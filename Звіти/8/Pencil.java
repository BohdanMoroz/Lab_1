package com.company;

public class Pencil extends WritingTool {
    public Pencil() {
        option = "HB";
    }

    @Override
    public String toStr(){
        String stringResult;
        String stringWritingTool = super.toStr();
        String stringPencil =
                "\n" +
                        "Softness : " +
                        getOption().toString() +
                        "\n" +
                        "Sharpened : " +
                        Boolean.toString(getChecker1()) +
                        "\n" +
                        "Eraser : " +
                        Boolean.toString(getChecker2()) +
                        "\n";
        stringResult = stringWritingTool + stringPencil;
        return stringResult;
    };

    @Override
    public String[] itemsKind() {
        String[] items = {
                "B3",
                "B2",
                "B1",
                "HB",
                "H1",
                "H2",
                "H3"
        };
        return items;
    }

    @Override
    public String nameCheckBox_1() {
        String pole = "Sharpened";
        return pole;
    };

    @Override
    public String nameCheckBox_2() {
        String pole = "Eraser";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Softness";
        return pole;
    };
}
