package graph;

import java.util.ArrayList;

/**
 * This class implements general operations on a graph as specified by UndirectedGraphADT.
 * It implements a graph where data is contained in Vertex class instances.
 * Edges between verticies are unweighted and undirected.
 * A graph coloring algorithm determines the chromatic number. 
 * Colors are represented by integers. 
 * The maximum number of vertices and colors must be specified when the graph is instantiated.
 * You may implement the graph in the manner you choose. See instructions and course material for background.
 */
 
  public class UndirectedUnweightedGraph<T> implements UndirectedGraphADT<T>{
    // private class variables here.
   
    private int MAX_VERTICES;
    private int MAX_COLORS;
    // TODO: Declare class variables here.
    private ArrayList<Vertex<T>> vertices;
    // private ArrayList<T> list;
    private int edges;
    /**
      * Initialize all class variables and data structures. 
    */   
    public UndirectedUnweightedGraph (int maxVertices, int maxColors){
      MAX_VERTICES = maxVertices;
      MAX_COLORS = maxColors; 
      // TODO: Implement the rest of this method.
      vertices = new ArrayList<Vertex<T>>();
      // list = new ArrayList<T>();
    }

    /**
      * Add a vertex containing this data to the graph.
      * Throws Exception if trying to add more than the max number of vertices.
    */
    public void addVertex(T data) throws Exception {
      // TODO: Implement this method.
      if (vertices.size()+1 > MAX_VERTICES)
        throw new Exception();
      vertices.add(new Vertex<T>(data));
    }
   
   /**
    * Return true if the graph contains a vertex with this data, false otherwise.
   */   
    @SuppressWarnings("unchecked")
    public boolean hasVertex(T data){
      // TODO: Implement this method.
      for (Vertex<T> vetex: vertices){
        vetex.setVisited(true);
        if(((Comparable<T>) vetex.getData()).compareTo(data) == 0) {
          return true;
        }
      }
      return false;
    } 
    @SuppressWarnings("unchecked")
    private Vertex<T> getVertex(T data){
      // TODO: Implement this method.
      for (Vertex<T> vetex: vertices){
        vetex.setVisited(true);
        if(((Comparable<T>) vetex.getData()).compareTo(data) == 0) {
          return vetex;
        }
      }
      return null;
    } 

    /**
      * Add an edge between the vertices that contain these data.
      * Throws Exception if one or both vertices do not exist.
    */   
    public void addEdge(T data1, T data2) throws Exception{
      // TODO: Implement this method.
      if (data1 == null || data2 == null)
        throw new Exception();
      Vertex<T> vertex1 = getVertex(data1);
      Vertex<T> vertex2 = getVertex(data2);
      vertex1.getAllAdjacentVertices().add(vertex2);
      vertex2.getAllAdjacentVertices().add(vertex1);
      edges++;
    }

    /**
      * Get an ArrayList of the data contained in all vertices adjacent to the vertex that
      * contains the data passed in. Returns an array of zero length if no adjacencies exist in the graph.
      * Throws Exception if a vertex containing the data passed in does not exist.
    */   
    public ArrayList<T> getAdjacentData(T data) throws Exception{
      // TODO: Implement this method.
      if (hasVertex(data) == false)
        throw new Exception();
      ArrayList<T> arr = new ArrayList<T>();
      Vertex<T> vertex = getVertex(data);
      for (Vertex<T> vertexData: vertex.edges){
        arr.add(vertexData.getData());
      }
      return arr;
    }
    
    /**
      * Returns the total number of vertices in the graph.
    */   
    public int getNumVertices(){
      return vertices.size();
    }

    /**
      * Returns the total number of edges in the graph.
    */   
    public int getNumEdges(){
      // TODO: Implement this method.
        return edges;
    }

    /**
      * Returns the minimum number of colors required for this graph as 
      * determined by a graph coloring algorithm.
      * Throws an Exception if more than the maximum number of colors are required
      * to color this graph.
    */   
    public int getChromaticNumber() throws Exception{
      // TODO: Implement this method.
      int highestColorUsed = -1;
      for (Vertex<T> v: vertices){
        if (v.getColor() == -1){
          ArrayList<Vertex<T>> adj = v.getAllAdjacentVertices();
          int colorToUse = getLowestUnusedColor(adj);
          v.setColor(colorToUse);
          highestColorUsed = Math.max(highestColorUsed, colorToUse);

        }
      }
      return highestColorUsed+1;
    }

    private int getLowestUnusedColor(ArrayList<Vertex<T>> adj) throws Exception{

      ArrayList<Integer> arr = new ArrayList<Integer>();
      for (int i =0; i <= MAX_COLORS-1;++i){
        arr.add(i);
      }

      for (Vertex<T> vertexColor:adj){
        if(vertexColor.getColor() != -1)
          arr.remove((Object)vertexColor.getColor());
      }

      if (arr.size() == 0)
        throw new Exception();
      return arr.get(0);
    }

}