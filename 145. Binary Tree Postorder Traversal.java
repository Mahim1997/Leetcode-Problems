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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(list, root);
        
        return list;
    }
    void postOrder(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }
        
        
        // left child
        if(root.left != null){
            postOrder(list, root.left);
        }
        
        // right child
        if(root.right != null){
            postOrder(list, root.right);
        }
        
        // self
        list.add(root.val);
    }
}