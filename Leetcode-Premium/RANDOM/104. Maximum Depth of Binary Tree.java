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
        if(root == null)
            return 0;
        
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        
        /*TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int level = 0;
        
        while(queue.isEmpty() == false){
            int qSize = queue.size();
            for(int i=0; i<qSize; i++){
                node = queue.remove();
                if(node == null)
                    continue;
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
            }
            level++;
        }
        return level;
        */
    }
}