package Graph_Algorithms.Graph;

import java.util.*;

/**
 * The {@code Graph} class can be either nweighted graph or weighted graph.
 * Also, the graph can be either directed or undirected.
 */
public class Graph {
	// It might be important to think how to encapsulate the nodeMap
	// since it's structure is used by many methods.

	/**
	*
	*/
	private Map<Node, ArrayList<Edge>> nodeMap;
	private boolean weighted = false;

	public Graph() {
		nodeMap = new HashMap<>();
	}

	public void resetVisit() {
		nodeMap.forEach((n, edges) -> {
			n.reset();
		});
	}

	public void resetAll() {
		nodeMap.forEach((n, edges) -> {
			n.reset();
			edges.forEach(e -> {
				e.reset();
			});
		});
	}

	public void addNode(String label) {
		if(!hasNode(label)){
			nodeMap.putIfAbsent(new Node(label), new ArrayList<>());
		}
	}

	/**
	*
	*/
	public void removeNode(String label) {
		try {
			// Removing the "label" node from every edge.
			nodeMap.values().stream().forEach(edges -> {
				edges.forEach(e -> {
					if (e.nodeIsInEdge(getNode(label))) {
						edges.remove(e);

					}
				});
			});
			// Removing the "label" node.
			nodeMap.remove(getNode(label));
		} catch (Exception e) {
			System.out.println("Error while removing the node...\n		" + e.toString());
		}

	}

	/**
	 * @param if {@code label_1} or {@code label_2} is not in the map, exception
	 *           will be emitted.
	 */
	public void addEdge(Node node_1, Node node_2) {
		try {
			// Node node_1 = getNode(label_1);
			// Node node_2 = getNode(label_2);
			getEdges(node_1).add(new Edge(node_1, node_2));
			getEdges(node_2).add(new Edge(node_2, node_1));
		} catch (Exception e) {
			System.out.println("Error while adding the edge...\n		" + e.toString());
		}

	}

	public void addEdge(Node node_1, Node node_2, int capacity) {
		try {
			// Node node_1 = getNode(label_1);
			// Node node_2 = getNode(label_2);
			getEdges(node_1).add(new Edge(node_1, node_2, capacity));
			getEdges(node_2).add(new Edge(node_2, node_1, capacity));
		} catch (Exception e) {
			System.out.println("Error while adding the edge...\n		" + e.toString());
		}

	}

	public void addEdge(String label_1, String label_2, boolean directed) {
		try {
			Node node_1 = getNode(label_1);
			Node node_2 = getNode(label_2);
			getEdges(node_1).add(new Edge(node_1, node_2));
		} catch (Exception e) {
			System.out.println("Error while adding the edge...\n		" + e.toString());
		}
	}

	public void addEdge(String label_1, String label_2, int capacity, boolean directed) {
		try {
			Node node_1 = getNode(label_1);
			Node node_2 = getNode(label_2);
			getEdges(node_1).add(new Edge(node_1, node_2, capacity));
		} catch (Exception e) {
			System.out.println("Error while adding the edge...\n		" + e.toString());
		}

	}

	/**
	 * @param if {@code label_1} or {@code label_2} is not in the map, exception
	 *           will be emitted.
	 */
	public void removeEdge(String label_1, String label_2) {
		try {
			Node node_1 = getNode(label_1);
			Node node_2 = getNode(label_2);
			getEdges(node_1).remove(getEdge(node_1, node_2));
			getEdges(node_2).remove(getEdge(node_2, node_1));

		} catch (Exception e) {
			System.out.println("Error while removing the edge...\n		" + e.toString());
		}

	}

	/**
	 * @return Returns {@code null} if the {@code label} node cannot be found.
	 */
	public Node getNode(String label) {
		// The searching node will be assigned if it can be found.
		Node node = null;
		try {

			node = nodeMap.keySet().stream().filter(n -> n.isEqual(new Node(label))).findFirst().orElse(null);

		} catch (Exception e) {
			System.out.println("Error while searching for the node...\n		" + e.toString());
		}

		// if(node == null) System.out.println("getNode is null");

		return node;
	}

	/**
	 * @return Returns {@code null} if the {@code label} node cannot be found in the
	 *         graph.
	 */
	public ArrayList<Edge> getEdges(Node node) {
		return nodeMap.get(node);
	}

	public Edge getEdge(Node start, Node end) {
		Edge edge = null;

		try {
			edge = getEdges(start).stream().filter(e -> (e.isEqual(new Edge(start, end)))).findFirst().orElse(null);
		} catch (Exception e) {
			System.out.println("Error while getting edge...\n	" + e.toString());
		}

		return edge;
	}

	public boolean hasNode(String label) {
		return nodeMap.keySet().stream().filter(n -> n.isEqual(new Node(label))).count() != 0;
	}

	public boolean hasEdge(Node start, Node end) {
		if (getEdge(start, end) == null) {
			return false;
		} else {
			return true;
		}
	}

	public void printGraph() {
		nodeMap.forEach((n, e) -> {
			e.forEach(edge -> {
				String printingStr = weighted ? String.format("%s Weight: %d\n", edge.outputEdgeStatus(), edge.getWeight())
						: String.format("%s\n", edge.outputEdgeStatus());
				System.out.println(printingStr);
			});
		});
	}

	public List<String> outputGraph() {
		List<String> outputStr = new ArrayList<String>();
		nodeMap.forEach((n, e) -> {
			e.forEach(edge -> {
				String printingStr = weighted ? String.format("%s Weight: %d\n", edge.outputEdgeStatus(), edge.getWeight())
						: String.format("%s\n", edge.outputEdgeStatus());
				outputStr.add(printingStr);
			});
		});
		return outputStr;

	}

}