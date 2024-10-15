package code;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.LinkedList;
import java.util.List;

import code.generic.Operator;
import code.generic.QueueingFunctions.QueueingFunction;
import code.watersort.WaterSortProblem;
import code.watersort.WaterSortState;
import code.watersort.WaterSortUtils;
import static code.generic.SearchStrategies.executeSearchStrategy;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class WaterSortSearch extends GenericSearch {

    public static int numberOfBottles;
    public static int bottleCapacity;

    public static String solve(String initialState, String strategy, boolean visualize)
            throws CloneNotSupportedException {

        // performance metrics
        Long startTime = System.currentTimeMillis();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long startCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();

        numberOfBottles = 0;
        bottleCapacity = 0;

        WaterSortState parsedInitialState = WaterSortUtils.parseInitialState(initialState);
        WaterSortProblem waterSortProblem = new WaterSortProblem(parsedInitialState, strategy);

        Node solutionNode = executeSearchStrategy(waterSortProblem, strategy, visualize);

        if (solutionNode == null)
            return "NOSOLUTION";

        // TODO: Why is this done with a linked list?
        // answer : because adding a string to the beginning of a stringBuilder is O(n)
        LinkedList<String> planBuilder = new LinkedList<>();

        String pathCost = solutionNode.getPathCost() + "";

        while (solutionNode.getParent() != null) {
            Operator operator = solutionNode.getOperator();
            planBuilder.addFirst(operator.toString());
            solutionNode = solutionNode.getParent();
        }

        String plan = formPlanString(planBuilder);
        String nodesExpanded = GenericSearch.nodesExpanded + "";

        System.out.println(strategy + " ----> " + reportPerformance_Complex(startTime, startMemory, startCpuTime));
        return plan + ";" + pathCost + ";" + nodesExpanded;
    }

    public static String formPlanString(List<String> list) {
        String result = list.toString();
        result = result.substring(1, result.length() - 1);
        result = result.replace(" ", "");
        return result;
    }

    private static String reportPerformance_Simple(Long startTime, long startMemory, long startCpuTime) {
        Long endTime = System.currentTimeMillis();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long endCpuTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        String runTime = "Runtime: " + (endTime - startTime) + " ms";
        String memory = "RAM: " + (endMemory - startMemory) / 1024 + " KB";
        String cpuTime = "CPU Time: " + (endCpuTime - startCpuTime) / 1000000 + " ms";
        return runTime + " | " + memory + " | " + cpuTime;
    }

    private static String reportPerformance_Complex(long startTime, long startMemory, long startCpuTime) {
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

    // Please dont delete this main method until the last moment
    public static void main(String[] args) {
        String state = "5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
        String strategy = "AS2";

        // ------------------------- below part is to print to BOTH the console and the
        // visualization.txt file -------------------------

        try {
            PrintStream originalOut = System.out;
            PrintStream fileOut = new PrintStream(new FileOutputStream("src/main/java/code/visualization.txt"));
            PrintStream dualOut = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    originalOut.write(b);
                    fileOut.write(b);
                }

                @Override
                public void flush() throws IOException {
                    originalOut.flush();
                    fileOut.flush();
                }

                @Override
                public void close() throws IOException {
                    originalOut.close();
                    fileOut.close();
                }
            });
            System.setOut(dualOut);
            String solution = solve(state, strategy, true);
            System.out.println(solution);
            System.setOut(originalOut);
            System.out.println(solution);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
