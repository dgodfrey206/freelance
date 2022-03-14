// stb package graphdfs;

import java.util.Scanner;

/**
 * Date Written:    11/13/2019
 *
 * @author Linda J. Yang - mucked up by Brower
 * Initially this was:  Create a graph and print it using a depth first
 * search algorithm.
 * Changed to Breadth First Search
 */
public class Proj02GraphBreadthFirstSearchS21 {
 public static void main(String[] args) {
    // create graph here with 9 vertices
    Graph myGraph = new Graph(9);

    // add 9 labels
    for (int i = 0; i < myGraph.size(); i++)
      myGraph.setLabel(i, "Vertex " + i);

    // Add each edge (this directed Graph has 11 edges,
    //                  so we add 11 edges)
    myGraph.addEdge(0, 1);
    myGraph.addEdge(0, 2);
    myGraph.addEdge(0, 3);
    myGraph.addEdge(0, 4);
    myGraph.addEdge(1, 6);
    myGraph.addEdge(2, 7);
    myGraph.addEdge(3, 8);
    myGraph.addEdge(5, 4);
    myGraph.addEdge(6, 7);
    myGraph.addEdge(8, 7);
    myGraph.addEdge(8, 5);

    System.out.println("Breadth First Print of Graph");

    for (int i = 0; i < myGraph.size(); i++) {
      System.out.println("\nStarting from " + myGraph.getLabel(i));
      // make call to breadthFirstPrint()
      breadthFirstPrint(myGraph, i);
    }
  }

 public static void breadthFirstRecurse(Graph myGraph,
                                  Queue<Integer> vertexQueue,
                                  boolean[] marked) {
    if (vertexQueue.size() == 0) {
      return;
    }

    try {
      Integer removedVertex = vertexQueue.remove();

      System.out.println("Vertex: " + removedVertex);

      for (int i : myGraph.neighbors(removedVertex)) {
        if (!marked[i]) {
          marked[i] = true;
          vertexQueue.add(i);
        }
      }
      breadthFirstRecurse(myGraph, vertexQueue, marked);
    } catch (EmptyQueue e) {
      System.out.println(e);
    }
  }

 public static void breadthFirstPrint(Graph myGraph, int start) {
    boolean[] marked = new boolean[myGraph.size()];

    // declare Queue of Integer
    Queue<Integer> vertexQueue = new Queue<Integer>();
    // add starting vertex
    vertexQueue.add(start);

    // call recursive method
    breadthFirstRecurse(myGraph, vertexQueue, marked);
  }
}
