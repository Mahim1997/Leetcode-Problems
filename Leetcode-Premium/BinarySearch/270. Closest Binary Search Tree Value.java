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
    public int closestValue(TreeNode root, double target) {
        // maintain so far closestDistance, closestValue
        
        // if equal, stop: if target > node.val, go right: else go left [if root] stop
        
        // Edge case
        if(root == null)
            return -1;
        
        TreeNode node = root;
        double closestDistance = (double) Integer.MAX_VALUE;
        int closestValue = root.val;
        
        while(node != null){
            double currentDistance = Math.abs(target - node.val);
            
            // update values
            if(currentDistance < closestDistance){
                closestDistance = currentDistance;
                closestValue = node.val;
            }
            
            // go to next child node
            if(node.val < target){
                // go to right child
                node = node.right;
            }
            else{
                // got to left child
                node = node.left;
            }
        }
        
        return closestValue;
    }
}


