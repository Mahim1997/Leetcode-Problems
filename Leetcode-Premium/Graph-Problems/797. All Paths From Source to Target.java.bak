class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // graph[0] -> {4, 3, 1} hence, graph is adjacency list
        
        // dfs style
        List<List<Integer>> answer = new ArrayList<>();
        
        // src = 0
        int src = 0;
        int dest = graph.length - 1;
        List<Integer> pathSoFar = new ArrayList<>(); 
        
        // Since it is a directed acyclic graph, no need to use 'visited' set
        
        pathSoFar.add(src);
        dfs(graph, answer, pathSoFar, src, dest);
        
        
        
        return answer;
    }
    
    // Backtracking --> Recursive
    private void dfs(int[][] graph, 
                        List<List<Integer>> answer, 
                        List<Integer> pathSoFar, 
                        int src,
                        int dest){

        // check here for dest node        
        // base case
        if(src == dest){
            answer.add(new ArrayList<>(pathSoFar));
            return;
        }
        
        for(int child: graph[src]){
            
            // set
            pathSoFar.add(child);
            
            // recurse
            dfs(graph, answer, pathSoFar, child, dest);
            
            // reset
            pathSoFar.remove(pathSoFar.size() - 1);
        }
        
    }
}


