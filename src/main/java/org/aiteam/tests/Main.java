package org.aiteam.tests;

import org.aiteam.code.watersort.WaterSortSearch;

public class Main {
    public static void main(String[] args) {
        String init = "5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
        System.out.println(WaterSortSearch.solve(init, "BF", true));
    }
}