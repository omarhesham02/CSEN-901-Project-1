package org.aiteam.code.watersort;

public class WaterSortUtils {

    public static WaterSortState parseInitialState(String initialState) throws IllegalArgumentException {
        String[] parts = initialState.split(";");

        // Should have at least a part for the number of bottles,
        // a part for the bottle capacity,
        // and a part for at least ONE bottle.
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid initial state format. Must have at least 3 parts separated by ';'");
        }

        int numberOfBottles;
        int bottleCapacity;

        try {
            numberOfBottles = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number of bottles. Must be an integer.");
        }

        try {
            bottleCapacity = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid bottle capacity. Must be an integer.");
        }

        Bottle[] bottles = new Bottle[numberOfBottles];

        for (int i = 2; i < parts.length; i++) {
            String[] strLayers = parts[i].split(",");
            Color[] colorLayers = new Color[bottleCapacity];
            for (int j = 0; j < strLayers.length; j++) {
                colorLayers[j] = Color.valueOf(strLayers[j]);
            }
            bottles[i - 2] = new Bottle(colorLayers);
        }

        WaterSortState state = new WaterSortState(bottles);

        return new WaterSortState(state);
    }
}
