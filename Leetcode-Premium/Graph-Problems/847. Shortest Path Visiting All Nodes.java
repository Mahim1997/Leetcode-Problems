class MyClass{
    public int vertex;
    // public Set<Integer> visited;
    public int visitedMask;
    
    public MyClass(int v, int vis){
        this.vertex = v;
        this.visitedMask = vis;
    }
    
    public void addVisited(int v){
        this.visitedMask |= (1 << v); // OR operation
    }
    
    @Override
    public String toString(){
        return this.vertex + ": " + this.visitedMask;
    }
    
    @Override
    public int hashCode(){
        int hash = 13;
        hash = hash + this.vertex*7;
        hash = hash + this.visitedMask;
        // for(int el: this.visited)
        //     hash = hash + el;
        return hash;
    }
    
    @Override
    public boolean equals(Object o){
        MyClass other = (MyClass) o;
        
        if(this.vertex != other.vertex)
            return false;
        if(this.visitedMask != other.visitedMask)
            return false;
        
        return true;
    }
}

class MyPair{
    int vertex;
    int degree;
    
    public MyPair(int v, int d){
        this.vertex = v; 
        this.degree = d;
    }
}

class Solution {
    private int[][] graph;
    private int numVertices;
    
    private int getMinDegreeVertex(int[][] graph){
        int vertexWithMinDegree = -1;
        int minDegree = Integer.MAX_VALUE;
        
        for(int i=0; i<this.numVertices; i++){
            if(graph[i].length < minDegree){
                minDegree = graph[i].length;
                vertexWithMinDegree = i;
            }
        }
        return vertexWithMinDegree;
    }
    
    private int getNewMask(int oldMask, int newVertex){
        return oldMask | (1 << newVertex);
    }
    
    private List<MyPair> getVerticesWithDegreesSorted(int[][] graph){
        int n = graph.length;
        List<MyPair> list = new ArrayList<>();
        
        for(int v=0; v<n; v++){
            list.add(new MyPair(v, graph[v].length));
        }
        
        list.sort((p1, p2) -> (p1.degree - p2.degree));
        
        return list;
    }
    
    public int shortestPathLength(int[][] graph) {
        // edge case
        if(graph.length == 0)
            return 0;
        
        this.graph = graph;
        this.numVertices = graph.length; // num vertices
        
        /////// THIS GIVES BETTER RUNTIME ANSWER
        // return bfs(0, Integer.MAX_VALUE);
        
        List<MyPair> list = getVerticesWithDegreesSorted(graph);
        
        int min = Integer.MAX_VALUE;
        // for(int v=0; v<this.numVertices; v++){
        int minDegree = Integer.MAX_VALUE;
        for(MyPair pair: list){
            int v = pair.vertex;
            int deg = pair.degree;
            
            // only run those with mindegrees
            if(deg > minDegree){
                continue;
            }
            minDegree = Math.min(minDegree, deg);
            
            int ans = bfs(v, min);
            // System.out.println("For v = " + v + ", ans = " + ans);
            min = Math.min(min, ans);
        }
        return min;
    }
    
    // branch-and-bound type bound
    private int bfs(int source, int minAmountSoFar){
        Queue<MyClass> queue = new LinkedList<>();
        Set<MyClass> visited = new HashSet<>();
        
        // all 1's
        int allVisitedMask = (1 << this.numVertices) - 1;
        
        // add initial vertices ALL to queue directly
        // for(int v=0; v<this.numVertices; v++){
        //     MyClass sourceObj = new MyClass(v, 0);
        //     sourceObj.addVisited(v); // add 'v' to the bitMask
        //     queue.add(sourceObj);
        // }
        
        MyClass sourceObj = new MyClass(source, 0);
        sourceObj.addVisited(source);
        queue.add(sourceObj);
        
        int level = 1; // start with '1' due to level++ doing later
        
        
        while(!queue.isEmpty()){
            int qSize = queue.size();
            
            // check if minAmountSoFar is < level, then simply ignore
            if(minAmountSoFar < level){
                return Integer.MAX_VALUE;
            }
            
            for(int i=0; i<qSize; i++){
                MyClass qTop = queue.remove();
                
                if(visited.contains(qTop) == true)
                    continue;
                
                visited.add(qTop);
                
                int v = qTop.vertex;
                int visMask = qTop.visitedMask;
                
                // add child to queue
                for(int child: graph[v]){
                    
                    int newMask = getNewMask(visMask, child);
                    if(newMask == allVisitedMask){
                        // all vertices end up being visited
                        return level;
                    }
                    
                    MyClass newObj = new MyClass(child, newMask);
                    queue.add(newObj);
                }
            
            }
            
            level++;
        }
        
        // should not come to this, as all are connected
        return 0;
    }
}









