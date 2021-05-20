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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        
        return hasSum(root, 0, targetSum);
    }
    
    private boolean hasSum(TreeNode node, int sumSoFar, int target){
        
        // base cases
        
        // node null case
        if(node == null){
            return false;
        }
        // leaf case
        if((node.left == null) && (node.right == null)){
            return ( (sumSoFar+node.val) == target);
        }
        
        // recursion
        return  hasSum(node.left, sumSoFar+node.val, target)||
                hasSum(node.right, sumSoFar+node.val, target);
    }

}