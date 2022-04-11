package data;

public enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE;

    public static String nameList() {
        StringBuilder nameList = new StringBuilder();
        for (DragonType dragonType : values()) {
            nameList.append(dragonType.name()).append(", ");
        }
        return nameList.toString().substring(0, nameList.length() - 2);
    }
}