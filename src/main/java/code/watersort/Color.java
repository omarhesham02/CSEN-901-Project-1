package code.watersort;

import code.utils.Methods;

public enum Color {
    r, g, b, y, o;

    public String toString(boolean colored) {
        if (colored)
            return Methods.getColoredCopy(this.toString(), this);
        return this.toString();
    }

    public String getFullName() {
        return switch (this) {
            case r -> "RED   ";
            case g -> "GREEN ";
            case b -> "BLUE  ";
            case y -> "YELLOW";
            case o -> "ORANGE";
        };
    }

    public String getFullName(boolean colored) {
        return Methods.getColoredCopy(getFullName(), this);
    }

    public static void main(String[] args) {
        Color yellow = Color.y;
        System.out.println("e " + yellow.toString(true) + " e");
        System.out.println("e " + yellow.toString(false));
    }
}