package graph;

import java.util.ArrayList;

/**
 * This class serves as a vertex in a graph. It contains data, of generic type
 * T, a color (an integer) assigned by a graph coloring algorithm, and a boolean
 * variable to record if this vertex has been visited. Note that a color value
 * of -1 indicated the Vertex is not colored.
 */
 
 public class Vertex<T>{
   
   T data;
   int color;
   boolean visited; // for convenience- may be useful for some algorithms.
   ArrayList<Vertex<T>> edges;
   
   public Vertex(T data){
      this.data = data;
      color = -1;
      visited = false;
      edges = new ArrayList<Vertex<T>>();
   }
   
   public T getData() {return data;}
   
   public int getColor(){return color;}
   
   public void setColor(int color){this.color = color;}
   
   public boolean isVisited(){return visited;}
   
   public void setVisited(boolean visited){this.visited = visited;}

   public ArrayList<Vertex<T>> getAllAdjacentVertices(){
      return edges;
   }

}