class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // graph[0] -> {4, 3, 1} hence, graph is adjacency list
        
        // dfs style
        List<List<Integer>> answer = new ArrayList<>();
        
        // src = 0
        int src = 0;
        List<Integer> pathSoFar = new ArrayList<>(); 
        
        // Since it is a directed acyclic graph, no need to use 'visited' set
        
        // pathSoFar.add(src);
        dfs(graph, answer, pathSoFar, src);
        
        
        
        return answer;
    }
    
    // Backtracking --> Recursive
    private void dfs(int[][] graph, 
                        List<List<Integer>> answer, 
                        List<Integer> pathSoFar, 
                        int src){

        // set
        pathSoFar.add(src);
        
        // check here for dest node        
        // base case
        int dest = graph.length - 1; // n - 1
        if(src == dest){
            answer.add(new ArrayList<>(pathSoFar));
            // can't return without removing pathSoFar's last node
        }
        else{
            // recurse, for each child node
            for(int child: graph[src]){
                dfs(graph, answer, new ArrayList<>(pathSoFar), child);
            }            
        }
        
        // reset
        // pathSoFar.remove(pathSoFar.size() - 1);
        
    }
}


