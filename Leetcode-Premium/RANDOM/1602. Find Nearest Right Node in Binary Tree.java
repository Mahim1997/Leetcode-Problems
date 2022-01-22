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
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        // BFS style, next one ??
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.isEmpty() == false){
            // TreeNode node = queue.peek();
            int qSize = queue.size();
            
            // TreeNode prev = node;
            boolean isPrev = false;
            for(int i=0; i<qSize; i++){
                TreeNode node = queue.remove();
                if(isPrev == true){
                    return node;
                }
                
                if(node == u){
                    isPrev = true;
                }else{
                    isPrev = false;
                }
                
                
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
                
                
            }
        }
        
        return null;
    }
}