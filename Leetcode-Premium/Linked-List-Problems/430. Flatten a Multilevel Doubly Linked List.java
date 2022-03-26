/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        // edge case
        if(head == null){return null;}
        
        Stack<Node> stack = new Stack<>();
        Node itr = head;
        
        while(true){
            if(itr.child != null){   // has a child
                Node nextNode = itr.next;
                stack.add(nextNode); // add to stack
                
                Node childNode = itr.child;
                
                itr.child = null; // set child == null
                
                itr.next = childNode; // adjust links
                childNode.prev = itr;
                
                itr = itr.next; // move to next position
            }
            else if(itr.next == null){ // itr has finished, check stack
                if(stack.isEmpty()) // full processing done
                    break;
                
                // TODO: stack is non empty
                Node stackTop = stack.pop();
                if(stackTop == null){
                    continue; // will fall back
                }
                else{
                    itr.next = stackTop; // adjust links
                    stackTop.prev = itr;
                    
                    itr = itr.next;
                }
            }
            else if(itr.child == null){ // has no child
                itr = itr.next; // already pointers are to the next
            }
        }
        
        // cancel each child pointers
        // itr = head;
        // while(itr != null){
        //     itr.child = null;
        //     itr = itr.next;
        // }
        
        return head;
    }
}