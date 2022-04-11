package data;

public enum Color {
    GREEN,
    BLUE,
    YELLOW,
    WHITE;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Color color : values()) {
            nameList.append(color.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
