/*
1971. Find if Path Exists in Graph
class Solution {
    public boolean validPath(int n, int[][] edges, 
                             int source, int destination) {
        
        // using BFS style
        // edges[i] = [u_i, v_i]
        
        
        // build adjacent list
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        // System.out.println("Printing adjList: " + adjList);
        
        // edge cases
        if(n == 1)
            return (source == destination);
        
        if(edges.length == 0)
            return false;
        
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(source);
        
        // DONOT add to visited right here, as inside while loop, this will be affected
        ////// visited.add(source);
        
        while(!queue.isEmpty()){
            int v = queue.remove();
            // System.out.println("From queue, v = " + v);
            
            if(v == destination)
                return true;
            
            if(visited.contains(v))
                continue;
            visited.add(v);
            
            for(int child: adjList.get(v)){
                // System.out.println("child = " + child);
                queue.add(child);
            }
        }
        
        
        return false;
    }
}



*/


class Graph{
    int numVertices;
    List<List<Integer>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        this.adjList = new ArrayList<>();
        
        for(int i=0; i<n; i++){
            this.adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest){
        this.adjList.get(src).add(dest);
        this.adjList.get(dest).add(src);
    }
    
    public boolean doesPathExist(int src, int dest){
        ///// DFS Style
        
//         Set<Integer> whiteSet = new HashSet<>();
//         Set<Integer> graySet = new HashSet<>();
//         Set<Integer> blackSet = new HashSet<>();
        
//         for(int v=0; v<this.numVertices; v++){
//             whiteSet.add(v);
//         }
        
//         dfs(src, whiteSet, graySet, blackSet, dest);
        
//         return blackSet.contains(dest);
        
        ///// BFS Style
        Set<Integer> visited = new HashSet<>();
        bfs(src, visited, dest);
        return visited.contains(dest);
    }
    
    private void bfs(int src, Set<Integer> visited, int destination){
        Queue<Integer> queue = new LinkedList<>();
        
        visited.add(src);
        queue.add(src);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int queueItem = queue.remove();
                for(int child: this.adjList.get(queueItem)){
                    if(child == destination){
                        visited.add(child);
                        return;
                    }
                    if(visited.contains(child)){
                        continue;
                    }
                    
                    queue.add(child);
                    visited.add(child);
                }
            }
        }
    }
    
    private void dfs(int src, 
                    Set<Integer> whiteSet,
                    Set<Integer> graySet,
                    Set<Integer> blackSet,
                    int dest){

        changeSets(whiteSet, graySet, src);
    
        // for each children
        for(int child: this.adjList.get(src)){
            
            if(blackSet.contains(child)){
                continue;
            }
            if(graySet.contains(child)){
                continue;
            }
            
            if(child == dest){
                blackSet.add(dest);
                return;
            }
            
            if(whiteSet.contains(child)){
                dfs(child, whiteSet, graySet, blackSet, dest);
            }
            
        }
        
        changeSets(graySet, blackSet, src);
    }
    
    private void changeSets(Set<Integer> set1,
                           Set<Integer> set2,
                           int v){
        set1.remove(v);
        set2.add(v);
    }
}


class Solution {
    public boolean validPath(int n, int[][] edges, 
                             int source, int destination) {
        Graph g = new Graph(n);
        
        for(int[] edge: edges){
            g.addEdge(edge[0], edge[1]);        
        }
        
        return g.doesPathExist(source, destination);
    
    }
}


