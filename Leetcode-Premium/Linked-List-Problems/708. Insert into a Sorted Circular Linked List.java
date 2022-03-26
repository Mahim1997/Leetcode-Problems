/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    private Node getMinimum(Node node1, Node node2){
        return (node1.val < node2.val) ? node1 : node2;
    }
    private Node getMaximum(Node node1, Node node2){
        return (node1.val > node2.val) ? node1 : node2;
    }
    
    public Node insert(Node head, int insertVal) {
        // edge case: no nodes
        if(head == null){
            head = new Node(insertVal);
            head.next = head; // circular list
            return head;
        }
        
        // min node, max node, first node
        Node minNode = head, maxNode = head, visitedNode = head;
        boolean foundAnswer = false, isFirstMove = true;

        Node itr = head;
        
        // while(true){ // can use do-while loop
            // if((itr == visitedNode) && (isFirstMove == false))
                // break;
        do{
            // update max & min nodes
            minNode = getMinimum(minNode, itr);
            maxNode = getMaximum(maxNode, itr);
            
            
            // check normal condition
            if((itr.val < insertVal) && (itr.next.val >= insertVal)){

                // create nodes
                Node nextNode = itr.next;
                Node newNode = new Node(insertVal);
                
                // adjust links
                itr.next = newNode;
                newNode.next = nextNode;
                
                foundAnswer = true;
                break;
            }
            
            // update itr
            itr = itr.next;
        
            isFirstMove = false;
        
        }while(itr != visitedNode);
        
        // maxNode -> next = newNode
        if(!foundAnswer){
            // insert BEFORE minNode === insert AFTER maxNode
            Node newNode = new Node(insertVal);
            Node nextNode = maxNode.next;
            
            // adjust links
            maxNode.next = newNode;
            newNode.next = nextNode;
        }
        
        return head;
    }
}