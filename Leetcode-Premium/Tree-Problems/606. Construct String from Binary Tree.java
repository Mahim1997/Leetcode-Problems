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
    private boolean isLeaf(TreeNode node) {
        return (
            (node.left == null) &&
            (node.right == null)
        );
    }
    
    public String tree2str(TreeNode root) {
        if(root == null)    return "";

        StringBuilder bld = new StringBuilder();
        preorder(root, bld);
        return bld.toString();
    }
    
    private void preorder(TreeNode node, StringBuilder bld) {
        if(node == null)    return;

        // Base case
        if(isLeaf(node)) {
            bld.append(node.val);
            return;
        }
        
        // 'visit' node
        bld.append(node.val);
        
        // recurse left child
        bld.append('(');
        preorder(node.left, bld);
        bld.append(')');
        
        // recurse right child, only when exists
        if(node.right != null) {            
            bld.append('(');
            preorder(node.right, bld);
            bld.append(')');
        }
    }
}
