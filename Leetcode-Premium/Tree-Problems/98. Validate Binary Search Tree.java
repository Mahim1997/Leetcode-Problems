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
    private boolean check(
        TreeNode node,
        Integer lowest,
        Integer highest
    ) {
        // Base cases
        if(node == null)
            return true;
        
        // System.out.println("Running for node.val = " + node.val + ", lowest = " + lowest + ", highest = " + highest);
        
        // node.val should be GREATER than lowest
        // node.val should be SMALLER than highest
        if((lowest != null) && (node.val <= lowest)) {
            return false;
        }
        if((highest != null) && (node.val >= highest)) {
            return false;
        }        
        
        // left subtree, update 'highest' [this.node]
        // right subtree, update 'lowest' [this.node]
        
        return (
            check(node.left, lowest, node.val) &&
            check(node.right, node.val, highest)
        );
    }
    
    public boolean isValidBST(TreeNode root) {
        // Base cases
        return check(
            root,
            null,
            null
        );
    }
}

