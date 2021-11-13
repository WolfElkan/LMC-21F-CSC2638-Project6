class Maze {
	private Node[][] nodes;
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
				nodes[r][c] = new Node();
				if (r > 0 && Math.random() < connectionProbability) {
					connect(r, c, 0);
				}
				if (c > 0 && Math.random() < connectionProbability) {
					connect(r, c, 3);
				}
			}
		}
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
			}
			System.out.println("");
		}		
	}
	public static void main(String[] args) {
		Maze maze = new Maze(8,8,0.5);
		maze.connect(4,5,2);
		maze.printMap();
	}
}