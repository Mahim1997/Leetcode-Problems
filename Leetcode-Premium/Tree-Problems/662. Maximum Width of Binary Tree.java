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
    public int widthOfBinaryTree(TreeNode root) {        
        // BFS style
        // maintain masks
        // suffix ==> 0 FOR left child, ==> 1 FOR right child
        
        int maxCount = 1;
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    
        int levelMask = 0; // level = 0, mask = 0
        queue.add(new Pair<>(root, levelMask));
        
        while(!queue.isEmpty()){
            int qSize = queue.size();
            
            int firstNodeMask = 0, lastNodeMask = 0;
            boolean firstNodeFound = false, secondNodeFound = false;
            
            for(int i=0; i<qSize; i++){
                Pair<TreeNode, Integer> pair = queue.remove();
                TreeNode node = pair.getKey();
                int currentNodeMask = pair.getValue();
                
                if(node == null){
                    continue;
                }
                
                // update prefixes
                int leftNodeMask = currentNodeMask << 1;
                int rightNodeMask = leftNodeMask | 1;
                if(node.left != null){
                    queue.add(new Pair<>(node.left, leftNodeMask));
                }
                if(node.right != null){
                    queue.add(new Pair<>(node.right, rightNodeMask));
                }
                
                
                // update values
                // this is the first node
                if(firstNodeFound == false){
                    firstNodeFound = true;
                }else{
                    // check if second node is found
                    if(secondNodeFound == false){
                        secondNodeFound = true;
                    }
                }
                
                // ONLY update for first node
                if((firstNodeFound == true) && (secondNodeFound == false)){
                    firstNodeMask = currentNodeMask;
                }
                
                // update for any other nodes
                if(secondNodeFound == true){
                    lastNodeMask = currentNodeMask;
                }
                
            }
            
            // only if TWO non-null nodes found, update
            if((firstNodeFound == true) && (secondNodeFound == true)){
                int currentCount = lastNodeMask - firstNodeMask + 1;
                maxCount = Math.max(maxCount, currentCount);
            }
            
        }
    
        return maxCount;
    }
}