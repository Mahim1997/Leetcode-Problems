/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // iterative
        List<Integer> list = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode node = root;
        while(true){
            if(node != null){
                stack.push(node);
                node = node.left;  // go until all left subtree is pushed into stack
            }
            else{
                if(stack.isEmpty())
                    break;
                
                node = stack.pop(); // pop from stack
                
                list.add(node.val); // 'visit' this node
                
                node = node.right;  // go to the right child/right subtree
            }
        }
        
        return list;
    }
}