package com.company;

public class Pen extends WritingTool {
    public Pen() {
        option = "Medium";
    };

    @Override
    public String toStr() {
        String stringResult;
        String stringWritingTool = super.toStr();
        String stringPen =
                "\n" +
                        "Thickness : " +
                        getOption().toString() +
                        "\n" +
                        "Ink : " +
                        Boolean.toString(getChecker1()) +
                        "\n" +
                        "On : " +
                        Boolean.toString(getChecker2()) +
                        "\n";
        stringResult = stringWritingTool + stringPen;
        return stringResult;
    };

    @Override
    public String[] itemsKind() {
        String[] items = {
                "Low",
                "Medium",
                "High"
        };
        return items;
    }

    @Override
    public String nameCheckBox_1() {
        String pole = "Ink";
        return pole;
    };

    @Override
    public String nameCheckBox_2() {
        String pole = "On";
        return pole;
    };

    @Override
    public String labelText(){
        String pole = "Thickness";
        return pole;
    };
}
