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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // exactly same checking
        
        if(p == null && q == null)
            return true;
        
        if(p == null)
            return false;
        if(q == null)
            return false;
        
        boolean valuesChecking = (p.val == q.val);
        boolean leftCheckingRecursive = isSameTree(p.left, q.left);
        boolean rightCheckingRecursive = isSameTree(p.right, q.right);
        
        return valuesChecking && leftCheckingRecursive && rightCheckingRecursive;
    }
}