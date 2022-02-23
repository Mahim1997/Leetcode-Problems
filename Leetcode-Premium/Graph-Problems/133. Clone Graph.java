/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    
    // TO DO
    public Node cloneGraph(Node node) {
        Map<Node, Node> mapOldToNew = new HashMap<>();
        return dfs(node, mapOldToNew);
    }
    
    private Node dfs(Node node, Map<Node, Node> map){
        // base cases
        if(map.containsKey(node) == true)
            return map.get(node);

        if(node == null)
            return node;
        
        // create new node
        Node newNode = new Node(node.val);
        
        // put into map here
        map.put(node, newNode);
        
        // run for each children
        for(Node child: node.neighbors){
            newNode.neighbors.add(dfs(child, map));
        }
        
        return newNode;
    }
    
}