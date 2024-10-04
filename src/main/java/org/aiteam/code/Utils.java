package org.aiteam.code;

public class Utils {
    public static InitialState parseInitialState(String initialState) throws IllegalArgumentException {
        String[] parts = initialState.split(";");

        // Should have at least a part for the number of bottles,
        // a part for the bottle capacity,
        // and a part for at least ONE bottle
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid initial state format. Must have at least 3 parts separated by ';'");
        }

        return null;
    }
}
