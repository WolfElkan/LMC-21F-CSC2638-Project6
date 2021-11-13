class Node {
	BoxChar bp = new BoxChar();
	private Node[] neighbors = new Node[4];
	void connect(Node target, int direction) {
		neighbors[direction] = target;
		target.neighbors[direction^2] = this;
	}
	public String toString() {
		String result = "";
		if (neighbors[0] == null &&
			neighbors[1] == null &&
			neighbors[2] == null &&
			neighbors[3] == null) {
			result += bp.dot();
		} else {
			result += bp.c(
				neighbors[0] != null ? 1 : 0,
				neighbors[1] != null ? 1 : 0,
				neighbors[2] != null ? 1 : 0,
				neighbors[3] != null ? 1 : 0
			);
		}
		result += bp.c(
			0,
			neighbors[1] != null ? 1 : 0,
			0,
			neighbors[1] != null ? 1 : 0
		);
		return result;
	}
	public static void main(String[] args) {
		Node a1 = new Node();
		Node b1 = new Node();
		a1.connect(b1, 2);
		System.out.println(a1.neighbors[2]);
		System.out.println(b1);
		System.out.println(a1);
		System.out.println(b1.neighbors[1]);
	}
}