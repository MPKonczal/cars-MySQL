package pl.edu.utp.wtie.homeworkweek7.model.enumeration;

public enum Color {
    BLACK("Black"),
    BLUE("Blue"),
    BROWN("Brown"),
    GOLDEN("Golden"),
    GREEN("Green"),
    ORANGE("Orange"),
    PINK("Pink"),
    RED("Red"),
    SILVER("Silver"),
    VIOLET("Violet"),
    WHITE("White"),
    YELLOW("Yellow");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
