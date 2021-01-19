/**
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

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
    private void printNode(ListNode node){
        ListNode itr = node;
        while(itr != null){
            System.out.println(itr.val);
            itr = itr.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0, rem = 0, carry = 0; // sum, remainder, carry-over
        
        ListNode head = new ListNode();
        // head.next = null;
        
        int v1, v2;
        
        ListNode node = head; // use as ref
        
        while(true){
            if(l1 == null){
                v1 = 0;
            }else{
                v1 = l1.val;
                l1 = l1.next;
            }
            if(l2 == null){
                v2 = 0;
            }else{
                v2 = l2.val;
                l2 = l2.next;
            }
            
            sum = v1 + v2 + carry;
            rem = sum % 10;
            carry = sum/10; //Math.max(sum - 10, 0);
            
            node.val = rem;
            
            
            if((l1 == null) && (l2 == null) && (carry == 0)){
                break;
            }
            
            // Create temp. null node for node.next
            node.next = new ListNode();
            node = node.next;
        }
        
        return head;
    }
}