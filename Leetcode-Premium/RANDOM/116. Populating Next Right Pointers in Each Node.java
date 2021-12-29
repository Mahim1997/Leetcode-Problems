/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
    
    @Override
    public String toString(){
        return "Node val = " + val;
    }
};
*/

class Solution {
    private void addChildren(Queue<Node> queue, Node node){
        if(node.left != null)
            queue.add(node.left);
        if(node.right != null)
            queue.add(node.right);
    }
    
    
    public Node connect(Node root) {
        if(root == null)
            return null;
        
        // BFS traversal
        Node temp = root;
        // be default next pointers point to null
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(queue.isEmpty() == false){
            int qSize = queue.size();
            
            for(int i=0; i<qSize; i++){
                temp = queue.remove();
                if(i == (qSize - 1)){ // last index
                    addChildren(queue, temp);
                }else{
                    Node queueTopNode = queue.peek();
                    temp.next = queueTopNode;
                    addChildren(queue, temp);
                }
            }
                
        }
        
        
        return root;
    }
}