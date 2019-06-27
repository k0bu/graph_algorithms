package Graph_Algorithms;
import java.util.*;
import Graph_Algorithms.Graph.*;

/**
 * For Fulkerson have to use undirected graph, in order to push back flow.
 */
public class Ford_Fulkerson {
	public boolean bfsPathSearch(Graph graph, String source, String sink) {
		// Resets all the visits
		//System.out.println("RESET VISIT");
		graph.resetVisit();
		//graph.getNode(source).setVisited(true);
		Queue<Node> bfsQueue = new ArrayDeque<Node>();
		bfsQueue.add( graph.getNode(source) );

		while (bfsQueue.size() != 0) {
			//System.out.println("bfsQueue size: " + bfsQueue.size());
			Node searchingNode = bfsQueue.poll();
			searchingNode.setVisited(true);
			

			graph.getEdges(searchingNode).forEach(e -> {
				//System.out.println("edge capacity: " + e.getCapacity());
				//System.out.println("edge used capacity: " + e.getUsedCap());

				Node nextNode = e.getEdgeNodes()[1];
				if(e.getCapacity() > e.getUsedCap() && !nextNode.isVisited()){
					bfsQueue.add(nextNode);
					nextNode.setEdgeInNode(e);
				}
			
			});
			if(searchingNode.isEqual(graph.getNode(sink))){
				break;
			}
			
		}

		return graph.getNode(sink).isVisited();
	}

	public int fordFulkerson(Graph graph, String source, String sink) {
		int maxFlow = 0;
		while(bfsPathSearch(graph, source, sink)){
			int pathFlow = Integer.MAX_VALUE;
			
			Node sourceNode = graph.getNode(source);
			Node sinkNode = graph.getNode(sink);

			Node itr = sinkNode;
			while(!itr.isEqual(sourceNode)){
				Edge connectedEdge = itr.getEdgeInNode();
				pathFlow = Math.min(
					pathFlow, 
					connectedEdge.getCapacity() - connectedEdge.getUsedCap()
				);
				
				itr = connectedEdge.getEdgeNodes()[0];
			}

			//System.out.println("PATHFLOW: " + pathFlow);
			itr = sinkNode;
			while(!itr.isEqual(sourceNode)){
				//System.out.println("SET USED CAP");
				
				Node prevNode = itr.getEdgeInNode().getEdgeNodes()[0];
				graph.getEdge(prevNode, itr).addUsedCap( pathFlow);
				graph.getEdge(itr, prevNode).addUsedCap( -1 * pathFlow);
			

				itr = prevNode;
			}

			maxFlow += pathFlow;
		}
	
		return maxFlow;

	}

}