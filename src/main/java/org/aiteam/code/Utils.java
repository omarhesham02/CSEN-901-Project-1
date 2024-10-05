package org.aiteam.code;

import java.util.ArrayList;

public class Utils {

    public static int BOTTLE_CAPACITY;
    public static int NUMBER_OF_BOTTLES;

    public static WaterSortSearchState parseInitialState(String initialState) throws IllegalArgumentException {
        String[] parts = initialState.split(";");

        // Should have at least a part for the number of bottles,
        // a part for the bottle capacity,
        // and a part for at least ONE bottle
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid initial state format. Must have at least 3 parts separated by ';'");
        }

        // Parse the number of bottles
        BOTTLE_CAPACITY = Integer.parseInt(parts[0]);
        NUMBER_OF_BOTTLES = Integer.parseInt(parts[1]);

        // Remaining parts are for the bottles
        Bottle[] bottles = new Bottle[NUMBER_OF_BOTTLES];

        for (int i = 2; i < parts.length; i++) {
            String[] layers = parts[i].split(",");
            ArrayList<Color> colors = new ArrayList<>();
            for (String layer : layers) {
                colors.add(Color.valueOf(layer));
            }
            bottles[i - 2] = new Bottle(colors, BOTTLE_CAPACITY);
        }

        return new WaterSortSearchState(bottles);

    }
}
