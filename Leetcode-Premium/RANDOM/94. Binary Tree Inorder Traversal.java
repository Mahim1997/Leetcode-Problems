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
        if(root == null)
            return new ArrayList<>();
        return iterativeTraversal(root);
    }
    
    private List<Integer> iterativeTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        // stack.push(cur); // NOT NEEDED
        
        while((cur != null) || (stack.isEmpty() == false)){
            // int currentVal = cur.val;

            // traverse left children & keep pushing to stack
            while(cur != null){ 
                stack.push(cur);
                cur = cur.left;
            }
            
            cur = stack.pop();
            list.add(cur.val);
            
            // go to right child.
            cur = cur.right;
        }
        
        return list;
    }
}