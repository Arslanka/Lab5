package data;

public enum Color {
    GREEN("GREEN"),

    BLUE("BLUE"),

    YELLOW("YELLOW"),

    WHITE("WHITE");

    Color(String green) {
    }

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Color color : values()) {
            nameList.append(color.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
