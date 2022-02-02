class Value{
    int id;
    double weight;
    
    public Value(int i, double w){this.id=i; this.weight=w;}
    
    @Override
    public String toString(){
        return "id = " + id + ", weight = " + weight;
    }
}

class Graph{
    public int numVertices;
    private List<List<Value>> adjList;
    
    public Graph(int n){
        this.numVertices = n;
        this.adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            this.adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest, double val){
        List<Value> srcList = this.adjList.get(src);
        if(srcList == null){
            srcList = new ArrayList<>();
        }
        srcList.add(new Value(dest, val));
        
        List<Value> destList = this.adjList.get(dest);
        if(destList == null){
            destList = new ArrayList<>();
        }
        double reciprocal = (double)1.0/val;
        destList.add(new Value(src, reciprocal));
    }
    
    
    private double dfs(boolean[] visited,
                      int src,
                      int dest,
                      double accProduct){
        
        // mark this vertex
        visited[src] = true;
        // System.out.println("Calling for src = " + src + ", dest = " + dest);
        if(src == dest){
            return accProduct;
        }
        double ret = -1.0;
        
        for(Value childrenVal: this.adjList.get(src)){
            int vertex = childrenVal.id;
            double weight = childrenVal.weight;
            
            if(visited[vertex] == false){
                // System.out.println("childrenVal = " + childrenVal);
                // visited[vertex] = true;
                // return weight*dfs(visited, vertex, dest);
                ret = dfs(visited, vertex, dest, 
                         weight*accProduct);

                // goal is reached, need to break
                if(ret != -1.0)
                    break;
            }
        }
        
        // unmark this vertex
        visited[src] = false;
        return ret;
    }
    
    public double getValueFromPath(int src, int dest){
        boolean[] visited = new boolean[this.numVertices];
        // initially, it is false
        
        return dfs(visited, src, dest, 1.0);
    }
}

class Solution {    
    public double[] calcEquation(List<List<String>> equations, 
                                 double[] values, 
                                 List<List<String>> queries) {
        
        int numQueries = queries.size();
        double[] ans = new double[numQueries];
        Arrays.fill(ans, -1.0);
        
        // if 'same' then +1.0
        
        int vertexID = 0;
        Map<String, Integer> mapStringInt = new HashMap<>();
        Map<Integer, String> mapIntString = new HashMap<>();

        
        for(int i=0; i<values.length; i++){
            List<String> eq = equations.get(i);
            for(String var: eq){
                if(mapStringInt.containsKey(var) == false){
                    mapStringInt.put(var, vertexID);
                    mapIntString.put(vertexID, var);
                    vertexID++;
                }
            }
        }
        
        Graph g = new Graph(vertexID);
        // g.numVertices = vertexID;
        for(int i=0; i<values.length; i++){
            List<String> eq = equations.get(i);
            double val = values[i];
            int src = mapStringInt.get(eq.get(0));
            int dest = mapStringInt.get(eq.get(1));
            
            g.addEdge(src, dest, val);
        }
        
        
        for(int i=0; i<ans.length; i++){
            List<String> query = queries.get(i);
            double answerToQuery = getAnswer(query, mapStringInt, g);
            ans[i] = answerToQuery;
        }
        
        
        return ans;
    }
    
    private double getAnswer(List<String> query,
                            Map<String, Integer> mapStringInt,
                            Graph g){
        
        for(String var: query){
            if(mapStringInt.containsKey(var) == false){
                return -1.0;
            }
        }
        String numeratorStr = query.get(0);
        String denominatorStr = query.get(1);
    
        int numeratorInt = mapStringInt.get(numeratorStr);
        int denominatorInt = mapStringInt.get(denominatorStr);
        
        // same eg. 'a/a'
        if(numeratorInt == denominatorInt){
            return 1.0;
        }
        
        return g.getValueFromPath(numeratorInt, denominatorInt);
    }
}