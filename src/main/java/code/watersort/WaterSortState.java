package code.watersort;

import java.util.Arrays;

import code.generic.FixedSizeStack;
import code.generic.SearchState;

public class WaterSortState extends SearchState {

    public WaterSortState(Bottle[] value) {
        super(value);
    }

    public WaterSortState(WaterSortState state) {
        super(state.getValue());
    }

    public Bottle[] getBottles() {
        return (Bottle[]) getValue();
    }

    @Override
    public boolean equals(Object obj) {
        // Two bottles are equal if they have the same layers
        if (!(obj instanceof WaterSortState other))
            return false;

        Bottle[] bottles = (Bottle[]) getValue();
        Bottle[] otherBottles = (Bottle[]) other.getValue();

        if (bottles.length != otherBottles.length)
            return false;

        for (int i = 0; i < bottles.length; i++)
            if (!bottles[i].equals(otherBottles[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        // Two states are equal if they contain the same bottles array
        return Arrays.hashCode((Bottle[]) getValue());
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean colored) {
        StringBuilder sb = new StringBuilder();
        Bottle[] bottles = (Bottle[]) getValue();

        for (Bottle bottle : bottles)
            sb.append(bottle.toString(colored)).append(";");
        return sb.toString();
    }

    public String getVerticalView(String middleText, boolean colored) throws CloneNotSupportedException {
        String result = "\n";
        Bottle[] bottles = (Bottle[]) getValue();
        Bottle[] bottlesCopy = new Bottle[bottles.length];
        for (int i = 0; i < bottles.length; i++) {
            bottlesCopy[i] = bottles[i].clone();
            while (!bottlesCopy[i].getLayers().isFull()) {
                bottlesCopy[i].getLayers().push(null);
            }
        }
        int bottleMaxSize = bottlesCopy[0].getLayers().getMaxCapacity();
        for (int i = 0; i < bottleMaxSize; i++) {
            for (int j = 0; j < bottlesCopy.length; j++) {
                Color color = bottlesCopy[j].getLayers().pop();
                result += "[ ";
                // result += color == null ? " " : color.toString().toUpperCase();
                result += color == null ? " ".repeat(6) : color.getFullName(true);
                result += " ]";
                result += "   ";
            }
            if (i == bottleMaxSize / 2) {
                result += middleText;
            }
            result += "\n";

        }

        return result;

    }

    // TODO: Remove this main method
    // Test equality of 2 water sort states
    public static void main(String[] args) throws CloneNotSupportedException {
        FixedSizeStack<Color> layers1 = new FixedSizeStack<>(3);
        layers1.push(Color.r);
        layers1.push(Color.g);
        layers1.push(Color.b);
        Bottle bottle1 = new Bottle(layers1);

        FixedSizeStack<Color> layers2 = new FixedSizeStack<>(3);
        layers2.push(Color.y);
        layers2.push(Color.o);
        Bottle bottle2 = new Bottle(layers2);

        Bottle[] bottles = { bottle1, bottle2 };
        WaterSortState state1 = new WaterSortState(bottles);

    }
}
