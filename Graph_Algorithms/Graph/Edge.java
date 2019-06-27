package Graph_Algorithms.Graph;

public class Edge {
	private Node _nodes[] = new Node[2];
	private int _weight;
	private int _capacity;
	private int _usedCap;

	public Edge(Node start, Node end) {
		this._nodes[0] = start;
		this._nodes[1] = end;
		this._usedCap = 0;
	}

	public Edge(Node start, Node end, int capacity) {
		this._nodes[0] = start;
		this._nodes[1] = end;
		this._capacity = capacity;
		this._usedCap = 0;
		
	}

	public Edge(Node start, Node end, int capacity, int weight) {
		this._nodes[0] = start;
		this._nodes[1] = end;
		
		this._capacity = capacity;
		this._weight = weight;
		this._usedCap = 0;

	}

	public void reset() {
		this._usedCap = 0;
	}

	public Node[] getEdgeNodes() {
		return this._nodes;
	}

	// public Node getEndNode(){
	// 	return this._nodes[1];
	// }

	// public Node getStartNode(){
	// 	return this._nodes[0];
	// }

	public int getWeight() {
		return this._weight;
	}

	public int getCapacity() {
		return this._capacity;
	}

	public int getUsedCap() {
		return this._usedCap;
	}

	public void addUsedCap(int used) {
		this._usedCap += used;
	}

	public boolean nodeIsInEdge(Node node) {
		for (var n : this.getEdgeNodes()) {
			if (n.getLabel() == node.getLabel()) {
				return true;
			}
		}
		return false;
	}

	public boolean isEqual(Edge e) {
		return e.nodeIsInEdge(this.getEdgeNodes()[0]) && e.nodeIsInEdge(this.getEdgeNodes()[1]);
	}

	public String outputEdgeStatus() {
		return String.format("Edge: Start Node [%s], End Node [%s].", this.getEdgeNodes()[0].getLabel(),
				this.getEdgeNodes()[1].getLabel());
	}
}