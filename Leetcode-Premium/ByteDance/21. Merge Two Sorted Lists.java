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
    private void printList(ListNode node){
        ListNode temp = node;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println("");
    }
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-100000);
        ListNode temp = head;
        
        ListNode h1 = list1, h2 = list2;
        
        while((h1 != null) && (h2 != null)){
            if(h1.val <= h2.val){ // h1 is smaller, take h1
                head.next = new ListNode(h1.val);
                h1 = h1.next;
            }else{
                head.next = new ListNode(h2.val);
                h2 = h2.next;
            }
            head = head.next;
        }
        
        // remaining elements.
        while(h1 != null){
            head.next = new ListNode(h1.val);
            h1 = h1.next;
            head = head.next;
        }
        while(h2 != null){
            head.next = new ListNode(h2.val);
            h2 = h2.next;
            head = head.next;
        }
        
        
        return temp.next;
    }
}