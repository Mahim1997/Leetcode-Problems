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
    List<Integer> list;
    public List<Integer> preorderTraversal(TreeNode root) {
        this.list = new ArrayList<>();
        
        recursion(root);
        
        return this.list;
    }
    
    private void recursion(TreeNode root){
        if(root == null){
            return;
        }
        this.list.add(root.val);
        if(root.left != null){
            recursion(root.left);
        }
        if(root.right != null){
            recursion(root.right);
        }
    }
}