class Node {
	BoxChar b = new BoxChar();
	private Node[] neighbors = new Node[4];
	private boolean[] edgecases = new boolean[4];
	Node(int row, int col) {}
	Node(boolean edge0, boolean edge1, boolean edge2, boolean edge3) {
		edgecases[0] = edge0;
		edgecases[1] = edge1;
		edgecases[2] = edge2;
		edgecases[3] = edge3;
	}
	Node () {}
	void connect(Node target, int direction) {
		neighbors[direction] = target;
		target.neighbors[direction^2] = this;
	}
	boolean n(int dir) {
		return neighbors[dir] != null;
	}
	int N(int dir) {
		return n(dir) ? 1 : 0;
	}
	int N2(int dir) {
		return n(dir) ? 0 : 2;
	}
	public String toString() {
		String result = "";
		if (neighbors[0] == null &&
			neighbors[1] == null &&
			neighbors[2] == null &&
			neighbors[3] == null) {
			result += b.dot();
		} else {
			result += b.c(N(0),N(1),N(2),N(3));
		}
		return result;
	}
	public char left() {
		int h = N(3);
		return b.c(0,h,0,h);
	}
	public char right() {
		int h = N(1);
		return b.c(0,h,0,h);
	}
	private int u() {
		if (neighbors[0] == null) {
			return 2;
		} else {
			return 0;
		}
	}
	public String wall() {
		int U = u();
		String result = "";
		result += b.c(2,2,2,2);
		result += b.c(0,2,0,2);
		result += b.c(0,2,0,2);
		result += b.c(0,2,0,2);
		if (edgecases[1]) {
			result += b.c(2,0,2,2);
		}
		return result;
	}
	public String hall() {
		String result = "";
		result += b.c(2,0,2,0);
		result += b.c(0,0,0,0);
		result += b.c(N(0),N(1),N(2),N(3));
		// result += b.c(0,0,0,0);
		result += b.c(0,0,0,0);
		if (edgecases[1]) {
			result += b.c(2,0,2,0);
		}
		return result;
	}
	public String floor() {
		String result = "";
		result += b.c(2,2,0,2);
		result += b.c(0,2,0,2);
		result += b.c(0,2,0,2);
		result += b.c(0,2,0,2);
		if (edgecases[1]) {
			result += b.c(2,0,0,2);
		}
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