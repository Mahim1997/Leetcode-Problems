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
    private int totalCount;
    
    public int goodNodes(TreeNode root) {
        if(root == null)
            return 0;
        
        this.totalCount = 0;
        dfs(root, root.val);
        
        return this.totalCount;
    }
    
    private void dfs(TreeNode node, int maxValueSoFar){
        // Base Case -> null node.
        if(node == null)
            return;
        
        int currentValue = node.val;
        if(currentValue < maxValueSoFar){ // current is NOT a good node
            // do nothing.
        }else{ // current IS a good node
            this.totalCount++;
            maxValueSoFar = Math.max(maxValueSoFar, currentValue);
        }
       
        // Repeat for left child and right child first
        dfs(node.left, maxValueSoFar);
        dfs(node.right, maxValueSoFar);
    
    }
}