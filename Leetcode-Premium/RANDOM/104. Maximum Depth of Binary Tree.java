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
    public int maxDepth(TreeNode root) {
        // Recursive approach
        // if(root == null)
        //     return 0;
        // return 1 + Math.max(
        //     maxDepth(root.left),
        //     maxDepth(root.right)
        // );
        
        // Using BFS
        return usingBFS(root);
    }
    
    private int usingBFS(TreeNode root){
        // edge case
        if(root == null)
            return 0;
        
        // Binary Tree -> no need for visited set
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int distance = 0;
        while(!queue.isEmpty()){
            int qSize = queue.size();
            for(int i=0; i<qSize; i++){
                TreeNode node = queue.remove();
                if(node.left != null)
                    queue.add(node.left);
                
                if(node.right != null)
                    queue.add(node.right);
            }
            distance++;
        }
        return distance;
    }
}

