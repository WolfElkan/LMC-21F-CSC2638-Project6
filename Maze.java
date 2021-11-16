class Maze {
	BoxChar b = new BoxChar();
	private Node[][] nodes;
	Node start = new Node();
	Node end = new Node();
	private int[] dirR = {-1, 0, 1, 0};
	private int[] dirC = { 0, 1, 0,-1};
	int nRows;
	int nCols;
	Maze(int nRows, int nCols, double connectionProbability) {
		this.nRows = nRows;
		this.nCols = nCols;
		nodes = new Node[nRows][nCols];
		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				nodes[r][c] = new Node(
					r == 0,
					c == nCols-1,
					r == nRows-1,
					c == 0
				);
				if (r > 0 && Math.random() < connectionProbability) {
					connect(r, c, 0);
				}
				if (c > 0 && Math.random() < connectionProbability) {
					connect(r, c, 3);
				}
			}
		}
		nodes[0][0].connect(start,0);
		nodes[nRows-1][nCols-1].connect(end,2);
	}
	void connect(int row, int col, int dir) {
		Node nodeA = nodes[row][col];
		row += dirR[dir];
		col += dirC[dir];
		Node nodeB = nodes[row][col];
		nodeA.connect(nodeB, dir);
	}
	void printMap() {
		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				System.out.print(nodes[r][c]);
				System.out.print(nodes[r][c].right());
			}
			System.out.println("");
		}		
	}
	String pnWall(int r, int c) {
		String result = "";
		// int A = (r == 0 || c == 0) ? 0 : nodes[r-1][c].n(3) ? 0 : 2;
		// int A;
		System.out.print(nodes[r][c].wall());
		// if (r == 0 || c == 0) {
		// 	A = 0;
		// } else {
			// A = nodes[r][c].N2(3);
		// }
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		return result;
	}
	String pnHall(int r, int c) {
		String result = "";
		System.out.print(nodes[r][c].hall());
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		// result += b.c(0,0,0,0);
		return result;
	}
	String pnFloor(int r, int c) {
		String result = "";
		System.out.print(nodes[r][c].floor());
		return result;
	}
	void printNeg() {
		for (int r=0; r<nRows; r++) {
			for (int c=0; c<nCols; c++) {
				System.out.print(pnWall(r, c));
			}
			System.out.println("");
			if (r < nRows) {
				for (int c=0; c<nCols; c++) {
					System.out.print(pnHall(r, c));
				}
				System.out.println("");
			}
		}	
		for (int c=0; c<nCols; c++) {
			System.out.print(pnFloor(nRows-1, c));
		}
		System.out.println("");
	}
	public static void main(String[] args) {
		Maze maze = new Maze(8,8,0.5);
		maze.connect(4,5,2);
		// maze.printMap();
		maze.printNeg();
	}
}