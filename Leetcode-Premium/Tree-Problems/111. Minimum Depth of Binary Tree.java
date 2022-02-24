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
    public int minDepth(TreeNode root) {
        // return recursive(root);
        
        return bfs(root);
    }
    
    private int bfs(TreeNode root){
        // edge case
        if(root == null)
            return 0;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int depth = 1; // initially is '1'
        while(!queue.isEmpty()){
            int qSize = queue.size();
            
            for(int i=0; i<qSize; i++){
                TreeNode node = queue.remove();
                
                // check if leaf node, then return THIS depth
                if((node.left == null) && (node.right == null))
                    return depth;
                
                if(node.left != null)
                    queue.add(node.left);
                
                if(node.right != null)
                    queue.add(node.right);
            }
            
            // increment the depth
            depth++;
        }
        
        
        return depth;
    }
    
    private int recursive(TreeNode root){
        // edge case
        if(root == null)
            return 0;
        
        // base case
        if((root.left == null) && (root.right == null))
            return 1;
        
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        
        if(root.left != null)
            leftDepth = minDepth(root.left) + 1;
        if(root.right != null)
            rightDepth = minDepth(root.right) + 1;
        
        return Math.min(leftDepth, rightDepth);
    }
}