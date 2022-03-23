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
    private int getLengthOfLinkedList(ListNode head){
        ListNode tail = head;
        int len = 0;
        while(tail != null){
            tail = tail.next;
            len++;
        }
        return len;
    }
    
    private ListNode reverseLinkedList(ListNode head){
        // edge case: 0 nodes
        if((head == null) || (head.next == null)){return head;}
        
        // add each next node to the head
        ListNode itr = head;
        ListNode givenHead = head;
        
        
        while(itr != null){
            ListNode next = itr.next;
    
            itr.next = givenHead;
    
            givenHead = itr;
            itr = next;
        }
        
        // assign null to the initial head as it becomes tail
        head.next = null;
        
        return givenHead;
    }
    
    public boolean isPalindrome(ListNode head) {
        // edge case
        if((head == null) || (head.next == null))
            return true;
        
        // Step 1. Get the length of linked list
        int len = getLengthOfLinkedList(head);
        
        // Step 2: Get the middle head 
        // THIS CAN BE OBTAINED USING 2 pointers [slow & fast method]
        // [odd -> ignore middle, ignored in comparison step]
        ListNode l1 = head, l2 = head;
        for(int i=0; i<(len/2); i++){
            l2 = l2.next;
        }
        if(len%2 != 0) // odd number, skip the middle element
            l2 = l2.next;
            
        
        // System.out.println("len = " + len + ", reversing ...");
        // printLinkedList(l2);

        // Step 3: Reverse l2
        l2 = reverseLinkedList(l2);
        
        // Step 4: Compare
        boolean answer = true;
        
        while((l1 != null) && (l2 != null)){
            if(l1.val != l2.val){
                answer = false;
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        
        // Step 5: Reverse back and add to initial list
        l2 = reverseLinkedList(l2);
        
        return answer;
    }
    
    private void printLinkedList(ListNode head){
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println("");
    }
}