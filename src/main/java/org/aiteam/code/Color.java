package org.aiteam.code;

public enum Color {
    RED("r"),
    GREEN("g"),
    BLUE("b"),
    YELLOW("y"),
    ORANGE("o");

    private final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
