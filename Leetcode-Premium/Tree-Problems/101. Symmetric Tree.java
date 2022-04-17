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
    public boolean isMirrorImage(TreeNode p, TreeNode q) {
        // both null, true
        if((p == null) && (q == null))
            return true;
        
        // any one null, other not, false
        if((p == null) || (q == null))
            return false;
        
        // check values
        if(p.val != q.val)
            return false;
        
        // check left[p] == right[q] && left[q] == right[p]
        return (
            isMirrorImage(p.left, q.right) &&
            isMirrorImage(p.right, q.left)
        );
    }
    
    public boolean isSymmetric(TreeNode root) {
        // edge case
        if(root == null)
            return true;
        
        return isMirrorImage(root.left, root.right);
    }
}