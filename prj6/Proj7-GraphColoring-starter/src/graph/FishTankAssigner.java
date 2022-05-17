package graph;

/**
 * This class uses an instance of a UndirectedGraphADT to solve an allocation
 * problem of assigning compatible fish to the minimum number of aquaria.
 * The graph object uses a graph coloring algorithm to determine this number.
 * The edges in the graph represent fish that are not compatible and canot be 
 * assigned to the same aquarium. The colors represent aquaria.
 * All fish that have the same color can safely live in the same aquarium.
 * Note: the relationships betwen fish species used here are not necessarily factual.
 * Please feel free to modify this class to test your code as it is not evaluated.
 */
 
 public class FishTankAssigner {

   public static void main(String[] args) throws Exception{
      
      String[] fish = {"Guppy", "Tetra", "Mollie", "Swordtail", "Wrass", "Grouper", "Snail"};
      int maxVertices = 7;
      int maxColors = 10;
   
      UndirectedGraphADT<String> graph = new UndirectedUnweightedGraph<String> (maxVertices, maxColors);
      // add vertices for compatible fish
      for(String curFish : fish)
         graph.addVertex(curFish);
      /* add edges between uncompatible and all other fish.
         note that a snail is a common inhabitant of an aquarium and can coexist with any fish,
         therefore it does not have an edge to any other fish.  */
      graph.addEdge("Grouper", "Wrass");
      graph.addEdge("Grouper", "Guppy");
      graph.addEdge("Grouper", "Mollie");
      graph.addEdge("Swordtail", "Wrass");
      graph.addEdge("Tetra", "Wrass");
      graph.addEdge("Tetra", "Mollie");
      // number should be 3
      int chromaticNum = graph.getChromaticNumber();
      System.out.print("The number of aquaria required is: "+chromaticNum);
      
   //   ArrayList<String> grouperAdjacencies =  graph.getAdjacentData("Grouper");
   //   ArrayList<String> snailAdjacencies =  graph.getAdjacentData("Snail");
      
  }
}