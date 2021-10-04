import java.io.*;
import java.util.*;

public class Main {

	private static void printArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ", ");
			}
			System.out.println("");
		}
	}

	private static void calculateAndPrintAnswer(List<Treasure> treasures, int timeTotal) {
		treasures.sort((t1, t2) -> Double.compare(t1.time, t2.time));

		// recursive OR dfs

		// Work like Knapsack problem.
		int[][] dp = new int[treasures.size() + 1][timeTotal + 1];

		// Rows -> 0, 1, 2, 3, ..., n
		// Columns -> 1, 2, ...., totalTime/W

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = 0;
			}
		}

//		printArray(dp);

		for (int row = 1; row < dp.length; row++) { // first row i.e. taking NONE, will always lead to 0
			int timeTreasure = treasures.get(row - 1).time;
			int quantityTreasure = treasures.get(row - 1).quantity;

			for (int colTime = 1; colTime < dp[row].length; colTime++) {
				if (timeTreasure <= colTime) {
					// without treasure i.e. same as the previous row.
					int qWithoutTreasure = dp[row - 1][colTime];

					// with treasure i.e. previous row, but time subtract from treasureTime
					int qWithTreasure = dp[row - 1][colTime - timeTreasure] + quantityTreasure;

					// take the max.
					int qTake = Math.max(qWithTreasure, qWithoutTreasure);
					dp[row][colTime] = qTake;
				} else {
					// just copy/paste the previous row.
					dp[row][colTime] = dp[row - 1][colTime];
				}
			}
		}

//		System.out.println("------------------------------------------------------------------------");

//		printArray(dp);

		// Now backtrack to get the solution.
		int rowIter = dp.length - 1; // last row
		int colIter = dp[0].length - 1; // last col
		int quantityCurrent = dp[rowIter][colIter];

		List<Integer> indicesTaken = new ArrayList<Integer>();

		while ((quantityCurrent != 0) && (rowIter >= 0) && (colIter >= 0)) {

			if (dp[rowIter][colIter] == dp[rowIter - 1][colIter]) {
				// same as the previous one, no need to take this.
				rowIter--;
			} else {
				indicesTaken.add(rowIter - 1); // since row has extra items.
				colIter -= treasures.get(rowIter - 1).time; // reduce the column of time by THIS treasure's time
				rowIter--; // decrement row's iter
			}
			quantityCurrent = dp[rowIter][colIter];
		}
		
//		treasures.sort((t1, t2) -> (t1.id - t2.id));
//		indicesTaken.sort((t1, t2) -> (t1 - t2));

		List<Treasure> takenTreasures = new ArrayList<>();
		for(int idx: indicesTaken) {
			takenTreasures.add(treasures.get(idx));
		}

		takenTreasures.sort((t1, t2) -> (t1.id - t2.id));
		
		// Results printing.
		System.out.println(dp[dp.length - 1][dp[0].length - 1]);
		System.out.println(indicesTaken.size());
		for (Treasure treasure: takenTreasures) {
//			Treasure treasure = treasures.get(idx);
			System.out.println(treasure.depth + " " + treasure.quantity);
		}

	}

	public static void main(String[] args) throws FileNotFoundException {
//		System.out.println("REMOVE THIS LINE.");
//		Scanner sc = new Scanner(new File("Input.txt"));
		Scanner sc = new Scanner(System.in);

		int t, w;
		int num_treasures;

		while (sc.hasNext()) {
			t = sc.nextInt();
			w = sc.nextInt();
			num_treasures = sc.nextInt();
//			Treasure[] treasures = new Treasure[num_treasures];
			List<Treasure> treasures = new ArrayList<>();

			for (int i = 0; i < num_treasures; i++) {
				int d = sc.nextInt();
				int q = sc.nextInt();
				treasures.add(new Treasure(i, d, w, q));
			}

//			System.out.println("Getting answer: ");
			calculateAndPrintAnswer(treasures, t);
			System.out.println();
		}

		sc.close();

	}
}

class Treasure {
	int id;
	int depth;
	int time;
	int quantity;
	double rateInverse;
//	double rate;

	public Treasure(int id, int d, int w, int q) {
		this.id = id;
		this.depth = d;
		this.quantity = q;
		this.calculateTime(d, w);
	}

	private void calculateTime(int d, int w) {
		this.time = 3 * w * d; // (w * d) + (2 * w * d)
		this.rateInverse = (double) this.time / (double) this.quantity;
//		this.rate = 1.0 / this.rateInverse;
	}

	@Override
	public String toString() {
		return "Treasure(id = " + id + ", time = " + time + ", quantity = " + quantity + ", rateInverse = "
				+ rateInverse + ")";
	}
}
