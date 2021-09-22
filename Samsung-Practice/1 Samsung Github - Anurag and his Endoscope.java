import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {
	static String FILE_NAME = "Input.txt";

	public static void main(String[] args) throws FileNotFoundException {
		// Read files.
//		Scanner sc = new Scanner(new File(FILE_NAME));
		Scanner sc = new Scanner(System.in);

		// Write your code here
		int N, M, R, C, L;

		while (sc.hasNextLine()) {
			int T = sc.nextInt();

			for (int testCases = 0; testCases < T; testCases++) {

				N = sc.nextInt();
				M = sc.nextInt();
				R = sc.nextInt();
				C = sc.nextInt();
				L = sc.nextInt();

				int[][] arr = new int[N][M];

				for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[i].length; j++) {
						arr[i][j] = sc.nextInt();
					}
				}
//				System.out.println("Hello World !!!");

				int result = getNumberOfPipes(N, M, R, C, L, arr);
				System.out.println(result);

			}

		}
	}

	private static boolean isValidIndex(int row, int col, int maxRow, int maxCol) {
		return ((row >= 0) && (row < maxRow) && (col >= 0) && (col < maxCol));
	}

	private static boolean isLeftOpening(int item) {
		return ((item == 1) || (item == 3) || (item == 6) || (item == 7));
	}

	private static boolean isRightOpening(int item) {
		return ((item == 1) || (item == 3) || (item == 4) || (item == 5));
	}

	private static boolean isTopOpening(int item) {
		return ((item == 1) || (item == 2) || (item == 4) || (item == 7));
	}

	private static boolean isBottomOpening(int item) {
		return ((item == 1) || (item == 2) || (item == 5) || (item == 6));
	}

	private static int getNumberOfPipes(int heightMap, int widthMap, int verticalLocation, int horizontalLocation,
			int lengthEndoscope, int[][] arr) {

		// Start at a given index (verticalLocation, horizontalLocation).
		// Maintain a visited map/array (can modify existing array ??)
		// Have a length of endoscope
		// BFS -> Use Queue to store the indices (row, col)
		// Find children i.e., new indices.
		// If current is at level == 1, then no need to increment counter.
		// If current is at a '0', no need to increment counter.

		// Queue Testing.
		MyQueue queue = new MyQueue();

		int level = lengthEndoscope;
		int result = 0; // 0 or 1 ??

		boolean[][] visited = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false; // initially, none are visited.
			}
		}

		Node current = new Node(verticalLocation, horizontalLocation, level);
		queue.addItem(current); // add current node to queue
		visited[current.row][current.col] = true; // mark current as visited

		while (!queue.isEmpty()) {
//			queue.printElements();

			Node temp = queue.front();
			queue.removeItem(); // remove current item.

//			System.out.println(">> RESULT = " + result + ", removing temp node from queue => " + temp.toString());

			// It has no pipe, just continue.
			if (arr[temp.row][temp.col] == 0) {
				continue;
			}
			// LEVEL CHECKING !!.
			if (temp.level <= 0) {
				continue;
			}

			// Mark node as visited.
			visited[temp.row][temp.col] = true;

			result++; // increment result.
			level--; // decrement level.

			// Get children.
			int row = temp.row;
			int col = temp.col;

			// Left movement possible check.
			if (isValidIndex(row, col - 1, heightMap, widthMap) && isLeftOpening(arr[row][col])
					&& isRightOpening(arr[row][col - 1]) && (visited[row][col - 1] == false)) {

				Node newNode = new Node(row, col - 1, temp.level - 1); // form new node
				queue.addItem(newNode); // add this item to queue, for further processing.
				visited[row][col - 1] = true; // mark as visited
			}

			// Right movement possible check.
			if (isValidIndex(row, col + 1, heightMap, widthMap) && (isRightOpening(arr[row][col]))
					&& (isLeftOpening(arr[row][col + 1])) && (visited[row][col + 1] == false)) {
				Node newNode = new Node(row, col + 1, temp.level - 1);
				queue.addItem(newNode);
				visited[row][col + 1] = true;
			}

			// Up movement possible check.
			if (isValidIndex(row - 1, col, heightMap, widthMap) && (isTopOpening(arr[row][col]))
					&& (isBottomOpening(arr[row - 1][col])) && (visited[row - 1][col] == false)) {
				Node newNode = new Node(row - 1, col, temp.level - 1);
				queue.addItem(newNode);
				visited[row - 1][col] = true;
			}

			// Down movement possible check.
			if (isValidIndex(row + 1, col, heightMap, widthMap) && (isBottomOpening(arr[row][col]))
					&& (isTopOpening(arr[row + 1][col])) && (visited[row + 1][col] == false)) {
				Node newNode = new Node(row + 1, col, temp.level - 1);
				queue.addItem(newNode);
				visited[row + 1][col] = true;
			}

		}

		return result;
	}

	static class Node {
		int row;
		int col;
		int level;

		public Node(int r, int c, int l) {
			this.row = r;
			this.col = c;
			this.level = l;
		}

		@Override
		public String toString() {
			return ("(" + this.row + ", " + this.col + ") " + this.level + "\n");
		}
	}

	static class MyQueue {
		int MAX_SIZE = 1000000;
		Node[] arr = new Node[MAX_SIZE];
		int head = 0;
		int tail = 0;

		public MyQueue() {
			head = 0;
			tail = 0;
		}

		public void addItem(Node newItem) {
			this.arr[tail] = newItem;
			tail++;
		}

		public boolean isEmpty() {
			return (this.head == this.tail);
		}

		public Node front() {
			return this.arr[head];
		}

		public void removeItem() {
			this.head++;
		}

		public void printElements() {
			System.out.println("---------------------------------------------");
			for (int i = head; i < tail; i++) {
				System.out.print(this.arr[i]);
			}
			System.out.println("---------------------------------------------");
		}

	}

}

/*
2
5 6 2 1 3
0 0 5 3 6 0
0 0 2 0 2 0
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 1 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1

*/