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
    public List<List<Integer>> findLeaves(TreeNode root) {
        TreeNode temp = root;
        List<List<Integer>> ans = new ArrayList<>();
        
        // Go BFS style in  a loop.
        boolean breakFlag = false;
        while(breakFlag == false){
            // if itself is a leaf node ...
            List<Integer> leaves;            
            if(isLeafNode(temp) == true){
                leaves = new ArrayList<>();
                leaves.add(temp.val);
                breakFlag = true;
            }else{
                leaves = getAndRemoveLeaves(temp);
            }
            ans.add(new ArrayList<>(leaves)); // create a copy.
            // System.out.println("Got once !!");
            // printArrayListDouble(ans);
        }
        
        
        return ans;
    }
    
    private void printArrayListDouble(List<List<Integer>> list2D){
        for(List<Integer> list: list2D){
            for(int x: list){
                System.out.print(x + " ");
            }
            System.out.println("");
        }
    }
    
    private boolean isLeafNode(TreeNode node){
        return ((node.left == null) && (node.right == null));
    }
    
    private List<Integer> getAndRemoveLeaves(TreeNode rootNode){
        TreeNode root = rootNode;
        List<Integer> list = new ArrayList<>();
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while(queue.isEmpty() == false){
            TreeNode node = queue.remove();
            
            // node doesn't exist.
            if(node == null){
                continue;
            }
            
            // left child exists.
            if(node.left != null){ 
                if(isLeafNode(node.left) == true){
                    // add to list & remove the node.
                    list.add(node.left.val);
                    node.left = null;
                }else{ // not a leaf node, push to queue.
                    queue.add(node.left);
                }
            }
            // right child exists.
            if(node.right != null){
                if(isLeafNode(node.right) == true){
                    // add to list & remove the node.
                    list.add(node.right.val);
                    node.right = null;
                }else{ // not a leaf node, push to queue.
                    queue.add(node.right);
                }
            }
            
        }
        
        return list;
    }
    
    
}