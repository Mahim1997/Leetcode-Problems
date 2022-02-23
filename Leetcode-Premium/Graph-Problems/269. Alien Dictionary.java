class Graph{
    // Set because, we need it to be unique
    private Set<Character> vertices;
    private Map<Character, Set<Character>> adjList;
    private Map<Character, Integer> indegrees;
    
    public Graph(){
        this.vertices = new HashSet<>();
        this.adjList = new HashMap<>();
        this.indegrees = new HashMap<>();
    }
    
    // adds to vertices set, also to indegrees set
    public void addVertex(char c){
        this.vertices.add(c);
        
        if(this.adjList.containsKey(c) == false){
            Set<Character> set = new HashSet<>();
            this.adjList.put(c, set);
        }
        
        // also add to indegrees
        this.indegrees.put(c, this.indegrees.getOrDefault(c, 0));
    }
    
    // returns true only if there is a problem
    private boolean addEdge(String word1, String word2){
        int len1 = word1.length(), len2 = word2.length();
        int minLen = Math.min(len1, len2);
        
        for(int i=0; i<minLen; i++){
            char c1 = word1.charAt(i), c2 = word2.charAt(i);
            
            if(c1 != c2){
                Set<Character> thisSet;
                thisSet = this.adjList.getOrDefault(c1, new HashSet<>());
                
                if(thisSet.contains(c2) == false){
                    thisSet.add(c2);
                    this.adjList.put(c1, thisSet);
                    
                    this.indegrees.put(c2,
                        this.indegrees.getOrDefault(c2, 0) + 1);
                } 
                
                // no problem is created
                return false;
            }
        }
        
        // first string can't be bigger
        if(len1 > len2){
            return true;
        }
        
        return false; // by default
    }
    
    public boolean createEdges(String[] words){
        // Create set of vertices
        for(String word: words){
            for(char c: word.toCharArray()){
                this.addVertex(c);
            }
        }
        
        // Create the adjList
        // Pairwise selection
        for(int i=0; i<(words.length - 1); i++){
            for(int j=i+1; j<words.length; j++){
                if(addEdge(words[i], words[j]) == true){
                    return true;
                }
            }
        }
        
        // set remaining indegrees as 0
        for(char c: this.adjList.keySet()){
            if(this.indegrees.containsKey(c) == false){
                this.indegrees.put(c, 0);
            }
        }
        
        return false;
    }
        
    public List<Character> runTopologicalSort(){
        List<Character> list = new ArrayList<>();
        
        // DEBUGGING
        // System.out.println(this.adjList);
        // System.out.println(this.indegrees);
        // System.out.println(this.vertices);
        
        Set<Character> visited = new HashSet<>();
        
        while(true){
            // pick the vertex with indegree = 0
            char chosenVertex = '#';
            
            // System.out.println("PRINTING indegrees: " + this.indegrees);
            
            for(char vertex: this.indegrees.keySet()){
                if((this.indegrees.get(vertex) == 0)
                && (visited.contains(vertex) == false)){
                    chosenVertex = vertex;
                    list.add(vertex);
                    break;
                }
            }
            
            // return empty list
            if(chosenVertex == '#'){
                return new ArrayList<>();
            }
            
            visited.add(chosenVertex);
            
            if(list.size() == this.vertices.size())
                break;
            
            // else, decrement indegrees of edges
            for(char v: this.adjList.get(chosenVertex)){
                this.indegrees.put(v, this.indegrees.get(v) - 1);
            }
            
        }
        
        return list;
    }
}

class Solution {
    public String alienOrder(String[] words) {
        // Edge case: all are equal
        
        // Create a graph
        Graph g = new Graph();
        boolean problem = g.createEdges(words);
        if(problem == true){
            return "";
        }
        
        // Run Kahn's Topological Sort algorithm
        List<Character> order = g.runTopologicalSort();
        
        StringBuilder bld = new StringBuilder();
        for(char c: order)
            bld.append(c);
        return bld.toString();
    }
}