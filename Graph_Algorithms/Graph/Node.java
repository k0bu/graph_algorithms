package Graph_Algorithms.Graph;

public class Node {
	private String _label;
	private boolean _visited = false;
	private Edge _searchInEdge;

	public Node(String label) {
		this._label = label;
	}

	public void reset() {
		this._visited = false;
	}

	public String getLabel() {
		return this._label;
	}
	public Edge getEdgeInNode(){
		return this._searchInEdge;
	}

	public String outputNodeStatus() {
		return String.format("Label: %s.", this.getLabel());
	}

	public boolean isVisited() {
		return this._visited;
	}

	public void setEdgeInNode(Edge edge){
		this._searchInEdge = edge;
	}

	public void setVisited(boolean visited) {
		this._visited = visited;
	}

	/**
	 * 
	 * @param n
	 * @return if the {@code label} of the {@code n} is equal to the one from
	 *         instance true, else false
	 */
	public boolean isEqual(Node n) {
		return this.getLabel().compareTo(n.getLabel()) == 0;
	}

}