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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)    
            return list;
        
        // Level Order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        
        TreeNode node = root;
        queue.add(node);
        
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            
            for(int i=0; i<qSize; i++) {
                TreeNode poppedNode = queue.remove();
                
                // add children to queue
                addChildren(queue, poppedNode);
                
                // last element only
                if(i == (qSize - 1)) {
                    list.add(poppedNode.val);
                }
            }
        }
        
        return list;
    }
    
    private void addChildren(
        Queue<TreeNode> queue, 
        TreeNode node
    ) {
        if(node.left != null)   queue.add(node.left);
        if(node.right != null)  queue.add(node.right);
    }
}


