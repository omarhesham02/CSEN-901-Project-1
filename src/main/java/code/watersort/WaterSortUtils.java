package code.watersort;

import code.WaterSortSearch;
import code.generic.FixedSizeStack;

public class WaterSortUtils {
    /**
     * Parses the initial state of the Water Sort problem from a string
     *
     * @param initialState The string representation of the initial state
     * @return The parsed initial state
     * @throws IllegalArgumentException If the initial state is invalid
     */
    public static WaterSortState parseInitialState(String initialState) throws IllegalArgumentException {
        String[] parts = initialState.split(";");

        // Should have at least a part for the number of bottles,
        // a part for the bottle capacity,
        // and a part for at least ONE bottle.
        if (parts.length < 3) {
            throw new IllegalArgumentException(
                    "Invalid initial state format. Must have at least 3 parts separated by ';'");
        }

        int numberOfBottles;
        int bottleCapacity;

        try {
            numberOfBottles = Integer.parseInt(parts[0]);
            WaterSortSearch.numberOfBottles = numberOfBottles;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number of bottles. Must be an integer.");
        }

        try {
            bottleCapacity = Integer.parseInt(parts[1]);
            WaterSortSearch.bottleCapacity = bottleCapacity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid bottle capacity. Must be an integer.");
        }

        Bottle[] bottles = new Bottle[numberOfBottles];

        for (int i = 2; i < parts.length; i++) {
            String[] strLayers = parts[i].split(",");
            if (strLayers.length != bottleCapacity) {
                throw new IllegalArgumentException(
                        "Invalid bottle " + parts[i] + ". Must be of length " + bottleCapacity);
            }
            FixedSizeStack<Color> colorLayers = new FixedSizeStack<>(bottleCapacity);

            for (int j = strLayers.length - 1; j >= 0; j--) {// reverse order (see note in Node.toString()) )
                // We don't consider 'e' as a color to save spacce
                String colorLetter = strLayers[j];
                if (!colorLetter.equals("e"))
                    colorLayers.push(Color.valueOf(colorLetter));
            }

            bottles[i - 2] = new Bottle(colorLayers);
        }

        WaterSortState state = new WaterSortState(bottles);

        return new WaterSortState(state);
    }
}
