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
        // return preOrderRecursive(root);
        return preOrderIterative(root);
    }
    
    private List<Integer> preOrderIterative(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        
        // edge case
        if(root == null)
            return list;
        
        // Using stack
        
        /*  1. Visit this vertex
            2. Push right child to stack
            3. Push left  child to stack [so that, top is the left subtree]
        */
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
        
            // visit the node
            list.add(node.val);
            
            // push right child
            if(node.right != null)
                stack.add(node.right);
            
            // push left child
            if(node.left != null)
                stack.add(node.left);
        }
        
        
        return list;
    }
    
    private List<Integer> preOrderRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        
        TreeNode node = root;
        preOrder(node, list);
        return list;
    }
    
    private void preOrder(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        
        // visit current node
        list.add(root.val);
        
        // visit left subtree
        preOrder(root.left, list);
        
        // visit right subtree
        preOrder(root.right, list);
    }
}









