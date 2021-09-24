import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static String FILE_NAME = "Input.txt";

	public static void main(String[] args) throws FileNotFoundException {
		// Read files.
		Scanner sc = new Scanner(new File(FILE_NAME));
//		Scanner sc = new Scanner(System.in);
		int num_vertices = sc.nextInt();
		int num_edges = sc.nextInt();

		int[][] mat = new int[num_vertices][num_vertices];

		// Initially all are zeros.
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				mat[i][j] = 0;
			}
		}

		// Add respective edges.
		for (int i = 0; i < num_edges; i++) {
			int v1 = sc.nextInt();
			v1--;
			int v2 = sc.nextInt();
			v2--;
			mat[v1][v2] = 1;
			mat[v2][v1] = 1;
		}

		// Template Function.
		int src = 1;
		Node maxLevelNode = getHighestLevel(mat, src - 1);
		System.out.println("Src-1 = " + (src - 1) + " maxLevelNode = " + maxLevelNode.toString());
	}

	private static Node getHighestLevel(int[][] mat, int src) {
		// Visited array.
		boolean[] visited = new boolean[mat.length];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}

		// Get highest level using BFS
		/*
		 * MyQueue queue = new MyQueue(); // result node queue.addItem(newNode); while
		 * (!queue.isEmpty()) { queue.printItems();
		 * 
		 * Node temp = queue.getFront(); queue.removeHead(); visited[temp.vertex] =
		 * true;
		 * 
		 * if (temp.level > maxLevelNode.level) { maxLevelNode.level = temp.level;
		 * maxLevelNode.vertex = temp.vertex; }
		 * 
		 * for (int num_cols = 0; num_cols < mat[temp.vertex].length; num_cols++) { if
		 * (mat[temp.vertex][num_cols] == 1) { // for all edges. int v2 = num_cols; if
		 * (visited[v2] == false) { Node node = new Node(v2, temp.level + 1);
		 * queue.addItem(node); System.out.println("Adding node vertex = " + (v2 + 1));
		 * } } } }
		 */
		Node maxLevelNode = new Node(-1, -1); // result node.
		Node newNode = new Node(src, 0); // Add initial node to queue/stack.

		// Get highest level using DFS
		MyStack stack = new MyStack();
		stack.addItem(newNode);
		visited[newNode.vertex] = true;

		while (!stack.isEmpty()) {
//			stack.printItems();
			Node node = stack.getTop();

			stack.removeNode();
			visited[node.vertex] = true;

			if (node.level > maxLevelNode.level) {
				maxLevelNode = node;
			}

			for (int col_vertex = 0; col_vertex < mat[node.vertex].length; col_vertex++) {
				if (mat[col_vertex][node.vertex] == 1) { // Edges
					int v2 = col_vertex;
					if (visited[v2] == false) {
						stack.addItem(new Node(v2, node.level + 1));
					}
				}
			}
		}

		return maxLevelNode;
	}

	static class MyStack {
		int MAX_LIMIT = 1000000;
		Node[] arr = new Node[MAX_LIMIT];
		int current_ptr = 0;

		public void addItem(Node node) {
			arr[current_ptr] = node;
			current_ptr++;
		}

		public void removeNode() {
			current_ptr--;
		}

		public Node getTop() {
			return arr[current_ptr - 1];
		}

		public boolean isEmpty() {
			return (current_ptr == 0);
		}

		public void printItems() {
			System.out.println("-------------------------------------");
			for (int i = 0; i < current_ptr; i++) {
				System.out.print(arr[i].toString() + ", ");
			}
			System.out.println("\n-------------------------------------");
		}
	}

	static class MyQueue {
		int MAX_LIMIT = 1000000;
		Node[] arr = new Node[MAX_LIMIT];
		int head = 0;
		int tail = 0;

		public void addItem(Node node) {
			arr[tail] = node;
			tail++;
		}

		public void removeHead() {
			head++;
		}

		public Node getFront() {
			return arr[head];
		}

		public boolean isEmpty() {
			return (head == tail);
		}

		public void printItems() {
			System.out.println("-------------------------------------");
			for (int i = head; i < tail; i++) {
				System.out.print(arr[i].toString() + ", ");
			}
			System.out.println("\n-------------------------------------");
		}
	}

	static class Node {
		int vertex;
		int level;

		public Node(int v, int l) {
			vertex = v;
			level = l;
		}

		@Override
		public String toString() {
//			return ("Value = " + value + ", level = " + level);
			return ("(v=" + (vertex + 1) + ", l=" + level + ")");
		}
	}

	private static void printMatrix(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + ", ");
			}
			System.out.println();
		}
	}

}

/*
 * 8 7 1 2 1 3 2 4 2 5 3 6 3 7 4 8
 */
