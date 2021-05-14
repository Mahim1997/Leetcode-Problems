
class Solution {
    private void printNode(ListNode head){
        ListNode temp = head;
        while(temp.next != null){
            System.out.print(temp.val + ", ");
        }
        System.out.println("");
    }
    
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        
        ListNode t1 = new ListNode(-1);
        ListNode t2 = new ListNode(-2);
        
        // smaller than in t1
        ListNode newHead = t1;
        ListNode newHead2 = t2;
        
        ListNode temp = head;
        while(temp != null){
            if(temp.val < x){
                t1.next = new ListNode(temp.val);
                t1 = t1.next;
            }
            temp = temp.next;
        }
        
        // >= in t2
        temp = head;
        while(temp != null){
            if(temp.val >= x){
                t2.next = new ListNode(temp.val);
                t2 = t2.next;
            }
            temp = temp.next;
        }
        
        
        // merge both
//        Utils.printListNode(newHead);
//        Utils.printListNode(newHead2);
        
        
        newHead = newHead.next;
        
        newHead2 = newHead2.next;
        
        if(newHead == null){ // first Node doesn't exist.
            return newHead2;
        }
        
        t1.next = newHead2;

        return newHead;
    }
}