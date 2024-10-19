package code.generic;

import code.GenericSearch;
import code.Node;
import code.generic.QueueingFunctions.*;

public class SearchStrategies {

  public static Node executeSearchStrategy(Problem problem, String strategy, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException, IllegalArgumentException {

    if (heuristicFunction == null && (strategy.equals("AS1") || strategy.equals("GR1"))) {
      throw new IllegalArgumentException("Heuristic function is required for " + strategy + " strategy");
    }

    return switch (strategy) {
      case "BF" -> BreadthFirstSearch(problem, visualize);
      case "DF" -> DepthFirstSearch(problem, visualize);
      case "UC" -> UniformCostSearch(problem,visualize);
      case "ID" -> IterativeDeepeningSearch(problem,visualize);
      case "DL" -> DepthLimitedSearch(problem, 3, visualize);
      case "AS1", "AS2" -> AStarSearch(problem, heuristicFunction, visualize);
        case "GR1", "GR2" -> GreedySearch(problem, heuristicFunction, visualize);

        default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
    };
  }


  public static Node DepthFirstSearch(Problem problem,boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new EnqueueAtFront(), visualize);
  }

  public static Node BreadthFirstSearch(Problem problem,boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new EnqueueAtEnd(), visualize);
  }

  public static Node UniformCostSearch(Problem problem, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem,new OrderedInsert(), visualize);
  }

  public static Node DepthLimitedSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem, new EnqueueAtFrontWithDepthLimit(depth), visualize);
  }

  public static Node IterativeDeepeningSearch(Problem problem, boolean visualize) throws CloneNotSupportedException {

    for(int depth = 0; depth < Integer.MAX_VALUE; depth++) {
      Node solution = DepthLimitedSearch(problem, depth, visualize);

      if (solution != null) {
        return solution;
      }
    }

    return null;
  }

  public static Node AStarSearch(Problem problem, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem, new AStarQueuingFunction(heuristicFunction), visualize);
  }

    public static Node GreedySearch(Problem problem, HeuristicFunction heuristicFunction, boolean visualize) throws CloneNotSupportedException{
        return GenericSearch.generalSearch(problem, new GreedyBestFirstQueuingFunction(heuristicFunction), visualize);
    }


}
