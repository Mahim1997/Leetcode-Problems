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
        List<Integer> list = new LinkedList<>(); // add to the beginning
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode node = root;
        
        // edge case
        if(root == null)
            return list;
        
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            
            // visit this node 'at the last' ==> add to head of linked list
            list.add(0, node.val);
            
            // visit left subtree
            if(node.left != null)
                stack.push(node.left);
            
            // visit right subtree
            if(node.right != null)
                stack.push(node.right);
        }
        
        
        return list;
    }
    
    private void postOrder(TreeNode root,
                                   List<Integer> list){
        if(root == null){
            return;
        }
        
        if(root.left != null){
            postOrder(root.left, list);
        }
        if(root.right != null){
            postOrder(root.right, list);
        }
        
        list.add(root.val);
    }
}