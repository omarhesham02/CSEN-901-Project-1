package code.generic;

import code.GenericSearch;
import code.Node;
import code.generic.QueueingFunctions.*;

public class SearchStrategies {

  public static Node executeSearchStrategy(Problem problem, String strategy, int depth, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException, IllegalArgumentException {

    if (heuristicFunction == null && (strategy.equals("AS1") || strategy.equals("GR1"))) {
      throw new IllegalArgumentException("Heuristic function is required for " + strategy + " strategy");
    }

    return switch (strategy) {
      // Generic strategy names
      case "BF" -> BreadthFirstSearch(problem, depth, visualize);
      case "DF" -> DepthFirstSearch(problem, depth, visualize);
      case "UC" -> UniformCostSearch(problem, depth, visualize);
      case "ID" -> IterativeDeepeningSearch(problem,depth, visualize);
      case "DL" -> DepthLimitedSearch(problem, depth, visualize);
      case "AS" -> AStarSearch(problem, depth, heuristicFunction, visualize);
      case "GR" -> GreedySearch(problem, depth, heuristicFunction, visualize);

      // Strategy names needed to match the water sort problem test cases
      case "AS1", "AS2" -> AStarSearch(problem, depth, heuristicFunction, visualize);
      case "GR1", "GR2" -> GreedySearch(problem, depth, heuristicFunction, visualize);

      default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
    };
  }


  public static Node DepthFirstSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new DFSQueuingFunction(), depth, visualize);
  }

  public static Node BreadthFirstSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new BFSQueuingFunction(), depth, visualize);
  }

  public static Node UniformCostSearch(Problem problem, int depth,  boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new UCSQueuingFunction(), depth, visualize);
  }

  public static Node DepthLimitedSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new DLSQueuingFunction(), depth, visualize);
  }

  public static Node IterativeDeepeningSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException {

    for(int i = 0; i < depth; i++) {
      Node solution = DepthLimitedSearch(problem, i, visualize);

      if (solution != null) {
        return solution;
      }
    }

    return null;
  }

  public static Node AStarSearch(Problem problem, int depth, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem, new AStarQueuingFunction(heuristicFunction), depth, visualize);
  }

    public static Node GreedySearch(Problem problem, int depth, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException{
        return GenericSearch.generalSearch(problem, new GreedyBestFirstQueuingFunction(heuristicFunction), depth, visualize);
    }


}
