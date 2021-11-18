class Node {
	BoxChar b = new BoxChar();
	int r;
	int c;
	public static String coordinates = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Node[] neighbors = new Node[4];
	private boolean[] edgecases = new boolean[4];
	Node(int row, int col) {}
	Node(boolean edge0, boolean edge1, boolean edge2, boolean edge3) {
		edgecases[0] = edge0;
		edgecases[1] = edge1;
		edgecases[2] = edge2;
		edgecases[3] = edge3;
	}
	Node(int row, int col, boolean edge0, boolean edge1, boolean edge2, boolean edge3) {
		r = row;
		c = col;
		edgecases[0] = edge0;
		edgecases[1] = edge1;
		edgecases[2] = edge2;
		edgecases[3] = edge3;
	}
	Node () {}

	private boolean alreadyAccessed(Node node, Node[] already) {
		for (int a=0; a<already.length; a++) {
			if (already[a] == node) {
				return true;
			}
		}
		return false;
	}
	// boolean canAccess(Node target, Node[] already) {
	// 	System.out.println(this);
	// 	if (this == target) {
	// 		return true;
	// 	}
	// 	Node[] alreadyMe = new Node[already.length+1];
	// 	for (int a=0; a<already.length; a++) {
	// 		alreadyMe[a] = already[a];
	// 	}
	// 	alreadyMe[already.length] = this;
	// 	for (int dir=0; dir<4; dir++) {
	// 		Node check = neighbors[dir];
	// 		if (check != null) {
	// 			if (!alreadyAccessed(check, alreadyMe)) {
	// 				if (check.canAccess(target)) {
	// 					return true;
	// 				}
	// 			}
	// 		}
	// 	}
	// 	return false;
	// }
	void printArr(Node[] arr) {
		System.out.print('[');
		for (int i=0; i<arr.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(arr[i]);
		}
		System.out.println(']');
	}
	boolean canAccess(Node target, Node[] already) {
		// if (already.length > 20) {
		// 	System.exit(1);
		// }
		// System.out.print(this);
		if (this == target) {
			return true;
		}
		for (int dir=0; dir<4; dir++) {
			if (n(dir)) {
				Node check = neighbors[dir];
				if (!alreadyAccessed(check,already)) {
					Node[] alreadyMe = new Node[already.length+1];
					for (int a=0; a<already.length; a++) {
						alreadyMe[a] = already[a];
					}
					alreadyMe[already.length] = this;
					// printArr(alreadyMe);
					if (check.canAccess(target, alreadyMe)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	boolean canAccess(Node target) {
		return canAccess(target, new Node[0]);
	}
	int nConnections() {
		return N(0) + N(1) + N(2) + N(3);
	}
	boolean connect(Node target, int direction) {
		if (!canAccess(target)) {
			neighbors[direction] = target;
			target.neighbors[direction^2] = this;
			return true;
		}
		return false;
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
		result += coordinates.charAt(r);
		result += coordinates.charAt(c);
		// if (neighbors[0] == null &&
		// 	neighbors[1] == null &&
		// 	neighbors[2] == null &&
		// 	neighbors[3] == null) {
		// 	result += b.dot();
		// } else {
		// 	result += b.c(N(0),N(1),N(2),N(3));
		// }
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