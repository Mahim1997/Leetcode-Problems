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
    public ListNode mergeKLists(ListNode[] lists) {
        // return usingPriorityQueue(lists);
        if(lists.length == 0)
            return null;
        
        return divideAndConquer(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeTwo(ListNode node1, ListNode node2){
        ListNode temp = new ListNode(-1);
        ListNode head = temp;

        // ListNode node1 = lists[start];
        // ListNode node2 = lists[end];

        while((node1 != null) && (node2 != null)){
            if(node1.val < node2.val){
                temp.next = new ListNode(node1.val);
                node1 = node1.next;
            }else{
                temp.next = new ListNode(node2.val);
                node2 = node2.next;
            }
            temp = temp.next;
        }

        // any remanining one
        while(node1 != null){
            temp.next = new ListNode(node1.val);
            node1 = node1.next;
            temp = temp.next;
        }

        while(node2 != null){
            temp.next = new ListNode(node2.val);
            node2 = node2.next;
            temp = temp.next;
        }

        return head.next;
    }
    
    private ListNode divideAndConquer(ListNode[] lists, 
                                      int start, int end){
        if((end - start) == 1){
            return mergeTwo(lists[start], lists[end]);
        }else if((end - start) == 0){
            return lists[start];
        }
        else{ // recursive DnC
            int mid = (start + end)/2;
            return mergeTwo(divideAndConquer(lists, start, mid),
                     divideAndConquer(lists, mid+1, end));
        }
    }
    
    private ListNode usingPriorityQueue(ListNode[] lists){
        if(lists.length == 0)
            return null;
        
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>
            ((ListNode n1, ListNode n2) -> (n1.val - n2.val));
        
        ListNode temp = new ListNode(-1);
        ListNode dummy = temp;
        
        for(ListNode node: lists){ // initial node adding
            if(node != null){
                pq.add(node);
            }
        }
        
        while(pq.isEmpty() == false){
            ListNode node = pq.remove();
            if(node != null){
                temp.next = new ListNode(node.val);
                temp = temp.next;
                if(node.next != null)
                    pq.add(node.next); // ADD next node
            }
        }
        
        return dummy.next; // temp was a dummy node
    }
}