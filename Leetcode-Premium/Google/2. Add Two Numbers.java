/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        ListNode ans = new ListNode();
        ListNode head = ans;
        
        int carryIn = 0;
        int sum = 0;
        
        // loop over both nodes.
        while((node1 != null) && (node2 != null)){
            // sum and carry computations.
            sum = node1.val + node2.val + carryIn;
            carryIn = sum/10;
            sum = sum%10;
            
            // insert into answer node.
            ListNode temp = new ListNode(sum, null);
            ans.next = temp;
            ans = ans.next;
            
            // iterate to next node.
            node1 = node1.next;
            node2 = node2.next;
        }
        
        // individually check each node
        while(node1 != null){
            sum = node1.val + carryIn;
            carryIn = sum/10;
            sum = sum%10;
            
            // insert into answer node.
            ListNode temp = new ListNode(sum, null);
            ans.next = temp;
            ans = ans.next;
            
            // iterate to next node.
            node1 = node1.next;
        }
        while(node2 != null){
            sum = node2.val + carryIn;
            carryIn = sum/10;
            sum = sum%10;
            
            // insert into answer node.
            ListNode temp = new ListNode(sum, null);
            ans.next = temp;
            ans = ans.next;
            
            // iterate to next node.
            node2 = node2.next;
        }
        
        // final carry check.
        if(carryIn > 0){
            // insert into answer node.
            ListNode temp = new ListNode(1, null);
            ans.next = temp;
            ans = ans.next;
        }
        
        
        return head.next;
    }
}