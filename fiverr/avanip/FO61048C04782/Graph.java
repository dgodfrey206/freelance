//stb package graphdfs;

/**
 * Trimmed by Stephen Brower
 * Date Modified:   11/26/2012 - removed package, clone, depth/breadth search
 *                  11/13/2016 - NetBeanafied
 *                  4/12/2017 - Comments added
 * Updated by Linda Yang 
 * @author Michael Main
 */

public class Graph
{
   private boolean[ ][ ] edges;
   private Object[ ] labels;

   /**
    * Constructor
    * @param n the number of nodes for the graph being created
    */
   public Graph(int n)
   {
      edges = new boolean[n][n];  // All values initially false
      labels = new Object[n];     // All values initially null
   }

   /**
    * addEdge method receives 2 locations.  The second location is added
    * as a neighbor to the first Location passed.
    * @param source Location to receive a neighbor
    * @param target Location to be added as a neighbor
    */
   public void addEdge(int source, int target)
   {
      edges[source][target] = true;
   }

   /**
    * getLabel returns the label associated with the vertex passed
    * @param vertex the vertex that we want the label for
    * @return the label for the vertex
    */
   public Object getLabel(int vertex)
   {
      return labels[vertex];
   }

   /**
    * isEdge method receives 2 Locations.  the second Location is checked
    * to see if it is a neighbor of the first Location
    * @param source Location to check for a neighbor
    * @param target other Location that is being checked to see if it is a neighbor
    * @return boolean that indicates if the second Location is a neighbor of the first Location
    */
   public boolean isEdge(int source, int target)
   {
      return edges[source][target];
   }

   /**
    * the neighbors method builds an array of Location which are the neighbors of
    * the Location passed
    * @param vertex the Location to get the neighbors from
    * @return an array of int that contains the neighbors of vertex
    */
   public int[ ] neighbors(int vertex)
   {
      int i;
      int count;
      int[ ] answer;

      // First count how many edges have the vertex as their source
      count = 0;
      for (i = 0; i < labels.length; i++)
      {
         if (edges[vertex][i])
            count++;
      }

      // Allocate the array for the answer
      answer = new int[count];

      // Fill the array for the answer
      count = 0;
      for (i = 0; i < labels.length; i++)
      {
         if (edges[vertex][i])
            answer[count++] = i;
      }

      return answer;
   }


   /**
    * removeEdge method receives 2 locations.  The second location is removed
    * as a neighbor to the first Location passed.
    * @param source Location to lose a neighbor
    * @param target Location to be removed as a neighbor
    */
   public void removeEdge(int source, int target)
   {
      edges[source][target] = false;
   }


   /**
    * setLabel assigns a label associated with the vertex passed
    * @param vertex the vertex that we want to set the label for
    * @param newLabel the label for the vertex
    */
   public void setLabel(int vertex, Object newLabel)
   {
      labels[vertex] = newLabel;
   }


   /**
    * size method returns the number of vertices in the graph
    * @return number of vertices
    */
   public int size( )
   {
      return labels.length;
   }

}

