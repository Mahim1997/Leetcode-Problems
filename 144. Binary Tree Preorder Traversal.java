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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(list, root);
        
        return list;
    }
    void preorder(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }
        
        // self
        list.add(root.val);
        
        // left child
        if(root.left != null){
            preorder(list, root.left);
        }
        
        // right child
        if(root.right != null){
            preorder(list, root.right);
        }
    }
}