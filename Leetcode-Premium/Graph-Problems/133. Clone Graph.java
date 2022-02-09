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
    private Map<Node, Node> mapOldToNew;
    
    public Node cloneGraph(Node root) {
        if(root == null){
            return root;
        }
        this.mapOldToNew = new HashMap<>();
        
        return dfs(root);
    }

    private Node dfs(Node root){
        // check map
        if(this.mapOldToNew.containsKey(root)){
            return this.mapOldToNew.get(root);
        }
        
        // create new node
        Node copyNode = new Node(root.val);
        // put into map [done before recursive call]
        this.mapOldToNew.put(root, copyNode);
        
        // for each child
        for(Node neighbor: root.neighbors){
            // recursive call to dfs
            copyNode.neighbors.add(dfs(neighbor));
        }
        
        return copyNode;
    }
}






















