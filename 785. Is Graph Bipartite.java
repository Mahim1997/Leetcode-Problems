class Solution {
	static int RED = 1;
	static int BLUE = 2;
	static int UNASSIGNED = 3;

	private int getOppositeColor(int color) {
		return (color == RED) ? BLUE : RED;
	}

	private boolean colorVertexUsingBFS(int[][] graph, int[] color, boolean[] visited, int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        // visited[source] = true;
        
        while(!queue.isEmpty()){
            int vertex = queue.remove(); // also removes.
            visited[vertex] = true;
            
            for (int v : graph[vertex]) { // for all edges connected to 'vertex'
                if (visited[v] == false) { // the new vertex is NOT visited
                    color[v] = getOppositeColor(color[vertex]);
                    visited[v] = true;
                    queue.add(v); // add the 'child' to queue
                } else { // the new vertex IS visited
                    // check if now to assign color is same as before.
                    if ((color[v] != UNASSIGNED) && (getOppositeColor(color[vertex]) != color[v])) {
                        return false;
                    }
                }
            }
        }

		return true;
	}

	public boolean isBipartite(int[][] graph) {
		// Check if graph is connected or not.
		// Run BFS -> if all vertices are not visited, then not connected.
//		if (isConnected(graph) == false) {
//			return false;
//		}

		boolean[] visited = new boolean[graph.length];
		Arrays.fill(visited, false);
		int[] color = new int[graph.length];
		Arrays.fill(color, UNASSIGNED);

		// Two coloring -> blue and red
		for (int i = 0; i < graph.length; i++) { // check all vertices.
			if (visited[i] == false) { // not visited.
				boolean bfsCheckThisVertex = colorVertexUsingBFS(graph, color, visited, i);
				if (bfsCheckThisVertex == false) {
					return false;
				}
			}
		}

		return true;
	}
}