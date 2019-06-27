import java.util.*;
import Graph_Algorithms.*;
import Graph_Algorithms.Graph.*;

public class Tester {
  public static void main(String args[]) {
    Graph graph = new Graph();
    Scanner scan = new Scanner(System.in);

    System.out.println("Please input 3 numbers: \n startNode endNode Capacity");
    while (true) {

      String str = scan.nextLine();
      if ("ok".compareTo(str) == 0) break;

      String[] inputs = str.split("\\s+");
      if(inputs.length != 3) {
        System.out.println("Need 3 parametres."); continue;
      }
      String start = inputs[0];
      String end = inputs[1];
      int capacity = Integer.parseInt(inputs[2]); 

      //System.out.println(start + end + capacity);

      graph.addNode(start);
      graph.addNode(end);
      graph.addEdge(graph.getNode(start), graph.getNode(end) , capacity);

      System.out.println("To selected source node and sink node, input 'ok'.");
    }
    String source = "";
    String sink = "";
    System.out.println("Please input 2 numbers: \n sourceNode sinkNode");
    while (true) {
      
      String str = scan.nextLine();
      if ("calculate".compareTo(str) == 0) break;

      String[] inputs = str.split("\\s+");
      if(inputs.length != 2) {
        System.out.println("Need 2 parametres."); continue;
      }

      source = inputs[0];
      sink = inputs[1];

      System.out.println("To calculate max flow, input 'calculate'.");
    }

    Ford_Fulkerson f = new Ford_Fulkerson();
    System.out.println("The max flow is: " + f.fordFulkerson(graph, source, sink) );

  }
}