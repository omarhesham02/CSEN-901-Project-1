package code.watersort;

public enum Color {
    r,
    g,
    b,
    y,
    o;
    // We don't consider 'e' as a color to save space

    public String getName() {
        switch (this) {
            case r:
                return "RED   ";
            case g:
                return "GREEN ";
            case b:
                return "BLUE  ";
            case y:
                return "YELLOW";
            case o:
                return "ORANGE";
            default:
                return "Unknown color value ! ";
        }

    }

}
