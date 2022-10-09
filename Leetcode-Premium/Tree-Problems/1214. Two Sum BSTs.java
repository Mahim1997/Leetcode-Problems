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
    // O(log n) search
    private boolean doesExist(TreeNode root, int target) {
        if(root == null)
            return false;
        
        if(root.val == target)
            return true;
        
        if(root.val < target) { // search in the right side ?
            return doesExist(root.right, target);
        }
        else {
            return doesExist(root.left, target);
        }
    }
    
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        // For each node in tree1, search using O(log n) above function in tree2 using the complement target
        // Simply do a preorder search
        return postOrder(root1, root2, target);
    }
    
    private boolean postOrder(TreeNode root1, TreeNode root2, int target) {
        if(root1 == null)
            return false;
        
        int newTarget = target - root1.val;
        
        // Visit 'self'
        if(doesExist(root2, newTarget) == true)
            return true;
        
        // Recurse left child
        if(postOrder(root1.left, root2, target) == true)
            return true;
        
        // Recurse right child
        if(postOrder(root1.right, root2, target) == true)
            return true;
        
        return false;
    }
}

