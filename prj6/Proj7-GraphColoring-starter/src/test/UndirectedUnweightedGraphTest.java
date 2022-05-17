package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.Assert;
import java.util.ArrayList;

import graph.UndirectedGraphADT;
import graph.UndirectedUnweightedGraph;
@SuppressWarnings("unchecked")


public class UndirectedUnweightedGraphTest {

   private int res;
   UndirectedGraphADT<Integer> graph;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
      int maxVertices = 8;
      int maxColors = 10;
      graph = new UndirectedUnweightedGraph<Integer> (maxVertices, maxColors);
   }

   @Test 
   public void getNumVerticesTest1() {
      int numVerts = graph.getNumVertices();
      Assert.assertEquals("Test 1: getNumVertices returns 0 on empty graph.", 0, numVerts);
   }

   @Test 
   public void getNumVerticesTest2() {
      int numVerts = -1;
      boolean exceptionThrown = false;
      try{
         graph.addVertex(100);
         numVerts = graph.getNumVertices();
      }
      catch(Exception ex){exceptionThrown = true;}
      Assert.assertEquals("Test 2: An exception was incorrectly thrown adding a vetex.", false, exceptionThrown);
      Assert.assertEquals("Test 2: getNumVertices with 1 vertex in graph.", 1, numVerts);
   }

   @Test 
   public void getNumVerticesTest3() {
      int numVerts = -1;
      boolean exceptionThrown = false;
      try{
         graph.addVertex(100);
         graph.addVertex(200);
         graph.addVertex(300);
         graph.addVertex(400);
         graph.addVertex(500);
         numVerts = graph.getNumVertices();
      }
      catch(Exception ex){exceptionThrown = true;}
      Assert.assertEquals("Test 3: An exception was incorrectly thrown adding a vetex.", false, exceptionThrown);
      Assert.assertEquals("Test 3: getNumVertices with 5 verticies in graph.", 5, numVerts);
   }

   @Test
   public void addVertexTest1() {
      int numVerts = -1;
      graph = new UndirectedUnweightedGraph<Integer> (4, 10);
      boolean exceptionThrown = false;
      try{
         graph.addVertex(100);
         graph.addVertex(200);
         graph.addVertex(300);
         graph.addVertex(400);
         graph.addVertex(500);
         numVerts = graph.getNumVertices();
      }
      catch(Exception ex){exceptionThrown = true;}
      Assert.assertEquals("Test 5: An exception was correctly thrown adding more than max verticies.", true, exceptionThrown);
   }

   @Test 
    public void getNumEdgesTest1() {
       int numVerts = graph.getNumEdges();
       Assert.assertEquals("Test 6: getNumEdges returns 0 on empty graph.", 0, numVerts);
    }

    @Test 
     public void getNumEdgesTest2() {
      try{
         graph.addVertex(100);
         graph.addVertex(200);
         graph.addVertex(300);
         graph.addVertex(400);
         graph.addVertex(500);
      }
      catch(Exception ex){}
        int numEdges = graph.getNumEdges();
        Assert.assertEquals("Test 7: getNumEdges returns 0 on graph with no edges.", 0, numEdges);
     }

     @Test 
      public void getNumEdgesTest3() {
       boolean addEdgeExceptionThrown = false;
       int numEdges = -1;
       try{
          graph.addVertex(100);
          graph.addVertex(200);
          graph.addVertex(300);
          graph.addVertex(400);
          graph.addVertex(500);
       }
       catch(Exception ex){}
       try{
         graph.addEdge(100, 500);
         numEdges = graph.getNumEdges();
      }
      catch(Exception ex){addEdgeExceptionThrown = true;}
       Assert.assertEquals("Test 8: An exception was incorrectly thrown adding an edge.", false, addEdgeExceptionThrown);
       Assert.assertEquals("Test 8: getNumEdges returns 1 on graph with 1 edge.", 1, numEdges);
      }

      @Test(timeout = 1000000L)
       public void hasVertexTest1() {
        boolean exceptionThrown = false;
        boolean hasVertex = false;
        try{
           graph.addVertex(100);
           graph.addVertex(200);
           graph.addVertex(300);
           graph.addVertex(400);
           graph.addVertex(500);
           hasVertex = graph.hasVertex(300);
        }
       catch(Exception ex){exceptionThrown = true;}
        
        Assert.assertEquals("Test 9: An exception was incorrectly thrown adding a vartex.", false, exceptionThrown);
        Assert.assertEquals("Test 9: hasVertex returns true on existing vertex.", true, hasVertex);
       }

       @Test 
        public void hasVertexTest2() {
         boolean exceptionThrown = false;
         boolean hasVertex = true;
         try{
            graph.addVertex(100);
            graph.addVertex(200);
            graph.addVertex(300);
            graph.addVertex(400);
            graph.addVertex(500);
            hasVertex = graph.hasVertex(600);
         }
        catch(Exception ex){exceptionThrown = true;}
         
         Assert.assertEquals("Test 10: An exception was incorrectly thrown adding a vartex.", false, exceptionThrown);
         Assert.assertEquals("Test 10: hasVertex returns false on non-existing vertex.", false, hasVertex);
      }

      @Test 
       public void getAdjacentDataTest() {
        boolean addEdgeExceptionThrown = false, addVertexExceptionThrown = false, getAdjExceptionThrown = false;
        boolean correctAdj = false;
        ArrayList<Integer> adjData;
        try{
           graph.addVertex(100);
           graph.addVertex(200);
           graph.addVertex(300);
           graph.addVertex(400);
           graph.addVertex(500);
        }
        catch(Exception ex){addVertexExceptionThrown = true;}
        try{
          graph.addEdge(100, 200);
          graph.addEdge(100, 400);
          graph.addEdge(100, 500);
       }
       catch(Exception ex){addEdgeExceptionThrown = true;}
       try{
          adjData = graph.getAdjacentData(100);
          correctAdj = (adjData.size()==3 && adjData.contains(200)&& adjData.contains(400)&& adjData.contains(500));
       }
       catch(Exception ex){getAdjExceptionThrown = true;}
       
       Assert.assertEquals("Test 11: An exception was incorrectly thrown adding a vertex.", false, addVertexExceptionThrown);
       Assert.assertEquals("Test 11: An exception was incorrectly thrown adding an edge.", false, addEdgeExceptionThrown);
       Assert.assertEquals("Test 11: An exception was incorrectly thrown calling getAdjacentData.", false, getAdjExceptionThrown);
       Assert.assertEquals("Test 11: getAdjacentData returns correct data.", true, correctAdj);
       }

       @Test 
        public void  getChromaticNumberTest() {
         boolean addEdgeExceptionThrown = false, addVertexExceptionThrown = false, getCNExceptionThrown = false;
         int chromaticNum = 0;
         try{
            graph.addVertex(1);
            graph.addVertex(2);
            graph.addVertex(3);
            graph.addVertex(4);
         }
         catch(Exception ex){addVertexExceptionThrown = true;}
         try{
           graph.addEdge(1, 2);
           graph.addEdge(1, 3);
           graph.addEdge(1, 4);
           graph.addEdge(3, 4);
        }
        catch(Exception ex){addEdgeExceptionThrown = true;}
        try{
         chromaticNum = graph.getChromaticNumber();
        }
        catch(Exception ex){getCNExceptionThrown = true;}
        
        Assert.assertEquals("Test 12: An exception was incorrectly thrown adding a vertex.", false, addVertexExceptionThrown);
        Assert.assertEquals("Test 12: An exception was incorrectly thrown adding an edge.", false, addEdgeExceptionThrown);
        Assert.assertEquals("Test 12: An exception was incorrectly thrown calling getChromaticNumber.", false, getCNExceptionThrown);
        Assert.assertEquals("Test 12:  getChromaticNumber returns correct number.", 3, chromaticNum);
        }

      @Test 
      public void  getChromaticNumberTest2() {
            graph = new UndirectedUnweightedGraph<Integer> (4, 2);
          boolean addEdgeExceptionThrown = false, addVertexExceptionThrown = false, getCNExceptionThrown = false;
          int chromaticNum = 0;
          try{
             graph.addVertex(100);
             graph.addVertex(200);
             graph.addVertex(300);
             graph.addVertex(400);
          }
          catch(Exception ex){addVertexExceptionThrown = true;}
          try{
            graph.addEdge(100, 200);
            graph.addEdge(100, 300);
            graph.addEdge(100, 400);
            graph.addEdge(300, 400);
         }
         catch(Exception ex){addEdgeExceptionThrown = true;}
         try{
          chromaticNum = graph.getChromaticNumber();
         }
         catch(Exception ex){getCNExceptionThrown = true;}
         
         Assert.assertEquals("Test 13: An exception was incorrectly thrown adding a vertex.", false, addVertexExceptionThrown);
         Assert.assertEquals("Test 13: An exception was incorrectly thrown adding an edge.", false, addEdgeExceptionThrown);
         Assert.assertEquals("Test 13: An exception was correctly thrown calling getChromaticNumber: not enough colors.", true, getCNExceptionThrown);
         }
}
   