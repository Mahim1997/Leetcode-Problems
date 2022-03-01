/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    private Map<Node, Node> oldToNewMap;
    public Node cloneTree(Node root) {
        this.oldToNewMap = new HashMap<>();
        return dfs(root);
    }
    
    private Node dfs(Node node){
        // contains checking
        if(this.oldToNewMap.containsKey(node))
            return this.oldToNewMap.get(node);
        
        // base case: null checking
        if(node == null)
            return null;
        
        Node newNode = new Node(node.val);
        for(Node child: node.children){
            newNode.children.add(
                dfs(child)
            );
        }
        return newNode;
    }
}


