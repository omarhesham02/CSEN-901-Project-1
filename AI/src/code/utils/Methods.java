package code.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import code.watersort.Color;

public class Methods {

    public static String getColoredCopy(String text, Color Color) { // returns a colored copy of the text
        // ANSI escape codes for colors
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String blue = "\u001B[34m";

        String yellow = "\u001B[38;5;94m"; // dark yellow
        // String yellow = "\u001B[33m"; // Normal yellow

        String orange = "\u001B[38;5;208m"; // Normal orange
        // String orange = "\u001B[38;5;94m"; // bright orange

        String reset = "\u001B[0m";

        switch (Color) {
            case r:
                return red + text + reset;
            case g:
                return green + text + reset;
            case b:
                return blue + text + reset;
            case y:
                return yellow + text + reset;
            case o:
                return orange + text + reset;
            default:
                return "Unknown color value!";
        }

    }

    public static String reportPerformance_Simple(Long startTime, long startMemory, long startCpuTime) {

        System.out.println("\n---------------------------------------------------------- Performance Report - Simple");
        Long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        String runTime = "Runtime: " + (endTime - startTime) + " ms";
        String memory = "RAM: " + (endMemory - startMemory) / 1024 + " KB";
        String cpuTime = "CPU Time: " + (endCpuTime - startCpuTime) / 1000000 + " ms";
        return runTime + " | " + memory + " | " + cpuTime;
    }

    public static String reportPerformance_Complex(long startTime, long startMemory, long startCpuTime) {
        System.out.println("\n---------------------------------------------------------- Performance Report - Complex");
        long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

        // Calculate used resources
        long runtimeDuration = endTime - startTime;
        long memoryUsed = endMemory - startMemory;
        long cpuTimeUsed = endCpuTime - startCpuTime;

        // Calculate total available resources
        long totalMemory = Runtime.getRuntime().totalMemory();
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        int availableProcessors = osBean.getAvailableProcessors();

        // Calculate utilization percentages
        double memoryUtilization = (double) memoryUsed / totalMemory * 100;
        double cpuUtilization = (double) cpuTimeUsed / (runtimeDuration * availableProcessors * 1000000) * 100;

        // Format the results
        String runTime = "Runtime: " + runtimeDuration + " ms";
        String memory = "RAM: " + memoryUsed / 1024 + " KB (" + String.format("%.2f", memoryUtilization) + "%)";
        String cpuTime = "CPU Time: " + cpuTimeUsed / 1000000 + " ms (" + String.format("%.2f", cpuUtilization) + "%)";
        return runTime + " | " + memory + " | " + cpuTime;
    }

}
