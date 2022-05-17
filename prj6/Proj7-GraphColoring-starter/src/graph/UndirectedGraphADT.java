package graph;
import java.util.ArrayList;
/**
 * This interface defines general operations on an undirected graph.
 * Vertices contain data elements T. Edges are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers.
 */
 
 public interface UndirectedGraphADT<T>{

   /**
    * Add a vertex containing this data to the graph.
    * Throws Exception if trying to add more than the max number of vertices.
   */
   public void addVertex(T data)throws Exception;
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
   public boolean hasVertex(T data);

   /**
    * Add an edge between the vertices that contain these data.
    * Throws Exception if one or both vertices do not exist.
   */   
   public void addEdge(T data1, T data2)throws Exception;

   /**
    * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
    * contains the data passed in. Returns an array of zero length if no adjacencies exist in the graph.
    * Throws Exception if a vertex containing the data passed in does not exist.
   */    
   public ArrayList<T> getAdjacentData(T data) throws Exception ;

   /**
    * Returns the total number of vertices in the graph.
   */   
   public int getNumVertices();

   /**
    * Returns the total number of edges in the graph.
   */   
   public int getNumEdges();

   /**
    * Returns the minimum number of colors required for this graph as 
    * determined by a graph coloring algorithm.
    * Throws Exception if the maximum number of colors has been exceeded.
   */   
   public int getChromaticNumber() throws Exception;
   
}