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
	int horz (int r, int c) {
		if (r < 0 || c < 0) {
			return 0;
		} else if (r >= nRows) {
			return nodes[nRows-1][c].n(2) ? 0 : 2;
		} else {
			return nodes[r][c].n(0) ? 0 : 2;
		}
	}
	int vert (int r, int c) {
		if (r < 0 || c < 0) {
			return 0;
		} else if (c >= nCols) {
			return nodes[r][nCols-1].n(1) ? 0 : 2;
		} else {
			return nodes[r][c].n(3) ? 0 : 2;
		}
	}
	String pnWall(int r, int c) {
		String result = "";
		result += b.c(vert(r-1,c),horz(r,c),vert(r,c),horz(r,c-1));
		result += b.c3(0,horz(r,c),0,horz(r,c));
		if (c >= nCols-1) {
			result += b.c(
				r == 0 ? 0 : vert(r,nCols),
				0,
				vert(r,nCols),
				horz(r,c)
			);
		}
		return result;
	}
	String pnHall(int r, int c) {
		String result = "";
		// System.out.print(nodes[r][c].hall());
		result += b.c(vert(r,c),0,vert(r,c),0);
		result += ' ';
		result += ' ';
		// System.out.print(nodes[r][c].left());
		// result += nodes[r][c];
		// System.out.print(nodes[r][c].right());
		result += ' ';
		if (c >= nCols-1) {
			result += b.c(
				vert(r,nCols),
				0,
				vert(r,nCols),
				0
			);
		}
		return result;
	}
	String pnFloor(int r, int c) {
		String result = "";
		result += b.c(vert(r,c),horz(nRows,c),0,horz(nRows,c-1));
		result += b.c3(0,horz(nRows,c),0,horz(nRows,c));
		if (c >= nCols-1) {
			result += b.c(
				vert(r,nCols),
				0,
				0,
				horz(r+1,c)
			);
		}
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
		maze.printNeg();
	}
}