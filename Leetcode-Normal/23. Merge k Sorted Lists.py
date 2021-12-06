# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

## USING DIVIDE AND CONQUER APPROACH.
class Solution:
    def merge2Lists(self, node1, node2):
        head = ListNode()
        dummy = head
        
        while True:
            new_node = None
            if (node1 and node2): ## Both nodes exist.
                if node1.val < node2.val:
                    new_node = ListNode(node1.val)
                    node1 = node1.next
                else:
                    new_node = ListNode(node2.val)
                    node2 = node2.next
            else:
                if node1:
                    new_node = ListNode(node1.val)
                    node1 = node1.next
                if node2:
                    new_node = ListNode(node2.val)
                    node2 = node2.next
            
            dummy.next = new_node ## Assign and Increment.
            dummy = dummy.next
            
            if ((not node1) and (not node2)):
                break
        
        # print("In merge2Lists. newList: ", end=' ')
        # self.printList(head.next)
        
        return head.next
    
    def printList(self, head):
        node = head
        while node:
            print(node.val, end=' ')
            node = node.next
        print("")
    
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        if(len(lists) == 0): return None
        
        new_lists = lists ## Keep reference here
        while True: ## Need breaking conditions.
            # print("<WHILE>: ", new_lists)
            if(len(new_lists) == 1): return new_lists[0]
            if(len(new_lists) == 2): return self.merge2Lists(new_lists[0], new_lists[1])
            # new_lists = [self.merge2Lists(new_lists[0], new_lists[1])]

            brand_new_lists = []
            for i in range(0, len(new_lists)):
                if((i%2 == 1)): ## for the even number.
                    merged_head = self.merge2Lists(new_lists[i-1], new_lists[i]) ## merge the two lists.
                    brand_new_lists.append(merged_head)
                else:
                    if (i == (len(new_lists) - 1)): ## only the last element condition checking.
                        brand_new_lists.append(new_lists[-1]) ## append the last element.
            
            ## END OF FOR LOOP
            new_lists = brand_new_lists ## re-assign in the new param.
            
        
        
        return None