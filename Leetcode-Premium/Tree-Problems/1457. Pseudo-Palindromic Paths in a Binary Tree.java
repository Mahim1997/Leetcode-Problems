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
    // 0: even, 1: odd
    private int flipBit(int number, int index) {
        // mask: all 0's, just 1 in that pos
        return number ^ (1 << index);
    }

    private int countOnes(int number) {
        int count = 0;
        while(number > 0) {
            if((number & 1) == 1)
                count++;
            number = number >> 1;
        }
        return count;
    }
    
    private boolean isPseudoPalindrome(int number) {
        // Check if power of 2
        return ((number & (number - 1)) == 0);
    }
    
    // API
    private int globalCount = 0;
    public int pseudoPalindromicPaths (TreeNode root) {
        if(root == null)    // edge case
            return 0;

        dfs(root, 0);
        return this.globalCount;
    }
    
    private boolean isLeaf(TreeNode node) {
        return (
            (node.left == null) &&
            (node.right == null)
        );
    }
    
    private void dfs(TreeNode root, int bitSoFar) {
        // base case: null
        if(root == null)
            return;
        
        int newBit = flipBit(bitSoFar, root.val);
        
        // edge case: leaf
        if(isLeaf(root)) {
            if(isPseudoPalindrome(newBit))
                this.globalCount++;
            return;
        }
        
        // recursive case
        dfs(root.left, newBit);
        dfs(root.right, newBit);
    }
}
