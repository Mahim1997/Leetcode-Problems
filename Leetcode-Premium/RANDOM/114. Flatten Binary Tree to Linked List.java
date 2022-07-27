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
    public void flatten(TreeNode root) {
        if(root == null)    return; // edge case
        TreeNode rootAdjusted = root;
        TreeNode[] pair = dfs(rootAdjusted);
        root = pair[0];
    }
    
    private boolean isLeafNode(TreeNode node) {
        return (
            (node.left == null) &&
            (node.right == null)
        );
    }
    
    private TreeNode[] dfs(TreeNode node) {
        // Base cases
        // System.out.println("For dfs node.val = " + node.val);
        
        // 1. Leaf node
        if(isLeafNode(node)) {
            // System.out.println("Base case 1: return node.val " + node.val + ", " + node.val);
            node.left = null;
            node.right = null;
            return new TreeNode[]{node, node};
        }
        
        // 2. Left Child nil
        if(node.left == null) {
            TreeNode tempRight = node.right;
            TreeNode[] pair = dfs(tempRight);
            node.right = pair[0];
            node.left = null;
            // System.out.println("Base case 2 => " + node.val + ", " + pair[1].val);
            return new TreeNode[]{node, pair[1]};
        }
        
        // 3. Right Child nil
        if(node.right == null) {
            TreeNode tempLeft = node.left;
            TreeNode[] pair = dfs(tempLeft);
            node.right = pair[0];
            node.left = null;
            // System.out.println("Base case 3 => " + node.val + ", " + pair[1].val);
            return new TreeNode[]{node, pair[1]};
        }
        
        // Recursive cases
        TreeNode tempRight = node.right;
        TreeNode tempLeft = node.left;
        
        TreeNode[] leftPair = dfs(tempLeft);    // [l1, r1]
        TreeNode[] rightPair = dfs(tempRight);  // [l2, r2]
        
        // adjust links
        node.right = leftPair[0];
        node.left = null;
        leftPair[1].right = tempRight;
        
        // System.out.println("Recursive => " + node.val + ", " + rightPair[1].val);
        
        return new TreeNode[]{node, rightPair[1]};
    }
}

