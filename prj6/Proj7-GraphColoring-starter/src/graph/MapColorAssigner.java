package graph;

/**
 * This class uses an instance of an UndirectedGraphADT to find the minimum number of 
 * colors to assign to states in a map coloring scenario. The edges in the graph represent 
 * stes that are adjacent to each other.
 * The graph object uses a graph coloring algorithm to determine the chromatic number.
 * Please feel free to modify this class to test your code as it is not evaluated.
 */
 
 public class MapColorAssigner {

   public static void main(String[] args) throws Exception{
       
      String[] states = {"Wisconsin", "Michigan", "Minnesota", "Illinois", "Iowa", "Indiana", "North Dakota", "South Dakota"};

      int maxVertices = 8;// must be >= number of vertices added
      int maxColors = 10;
   
      UndirectedGraphADT<String> graph = new UndirectedUnweightedGraph<String> (maxVertices, maxColors);
      // add vertices to the graph
      for(String curState : states)
         graph.addVertex(curState);

      // add edges between states
      graph.addEdge("Wisconsin", "Michigan");
      graph.addEdge("Wisconsin", "Minnesota");
      graph.addEdge("Wisconsin", "Iowa");
      graph.addEdge("Wisconsin", "Illinois");
      graph.addEdge("Minnesota", "Iowa");
      graph.addEdge("Michigan", "Indiana");
      graph.addEdge("Illinois", "Indiana");
      graph.addEdge("Illinois", "Iowa");
      graph.addEdge("Minnesota", "North Dakota");
      graph.addEdge("Minnesota", "South Dakota");
      graph.addEdge("North Dakota", "South Dakota");
      graph.addEdge("Iowa", "South Dakota");
      // chromatic number on the above graph = 4
      int chromaticNum = graph.getChromaticNumber();
      System.out.print("The number of colors required is: "+chromaticNum);
  }
}