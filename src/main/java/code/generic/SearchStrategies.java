package code.generic;

import code.GenericSearch;
import code.Node;
import code.generic.QueueingFunctions.*;

public class SearchStrategies {

  public static Node executeSearchStrategy(Problem problem, String strategy, boolean visualize) throws CloneNotSupportedException, IllegalArgumentException {
    return switch (strategy) {
      case "BF" -> BreadthFirstSearch(problem, visualize);
      case "DF" -> DepthFirstSearch(problem, visualize);
      case "UC" -> UniformCostSearch(problem,visualize);
      case "ID" -> IterativeDeepeningSearch(problem,visualize);
      case "GR1" -> Greedy1Search(problem,visualize);
      case "GR2" -> Greedy2Search(problem,visualize);
      case "AS1" -> AStar1Search(problem,visualize);
      case "AS2" -> AStar2Search(problem,visualize);
      default -> throw new IllegalArgumentException("Invalid strategy: " + strategy);
    };
  }



  public static Node DepthFirstSearch(Problem problem,boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem, new EnqueueAtFront(), visualize);
  }

  public static Node BreadthFirstSearch(Problem problem,boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem, new EnqueueAtEnd(), visualize);
  }

  public static Node UniformCostSearch(Problem problem, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem,new OrderedInsert(), visualize);
  }

  public static Node LimitedDepthSearch(Problem problem, int depth, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem, new EnqueueAtFrontWithDepthLimit(depth), visualize);
  }

  public static Node IterativeDeepeningSearch(Problem problem, boolean visualize) throws CloneNotSupportedException{
    int infinity  = Integer.MAX_VALUE;

    for(int depth = 0; depth < infinity;depth++){
      Node solution = LimitedDepthSearch(problem, depth, visualize);
      if(solution != null){
        return solution;
      }
    }

    return null;
  }

  public static Node Greedy1Search(Problem problem, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem,new GREEDY1QueuingFunction(), visualize);
  }

  public static Node Greedy2Search(Problem problem, boolean visualize) throws CloneNotSupportedException{
    return GenericSearch.generalSearch(problem,new GREEDY2QueuingFunction(), visualize);
  }

  public static Node AStar1Search(Problem problem, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem,new AStar1QueuingFunction(), visualize);
  }

  public static Node AStar2Search(Problem problem, boolean visualize) throws CloneNotSupportedException {
    return GenericSearch.generalSearch(problem,new AStar2QueuingFunction(), visualize);
  }


}
