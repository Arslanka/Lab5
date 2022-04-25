package data;

public enum Country {

    UNITED_KINGDOM("UNITED_KINGDOM"),

    GERMANY("GERMANY"),

    THAILAND("THAILAND");

    Country(String name) {
    }

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (Country country : values()) {
            nameList.append(country.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}