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
    private int UNDEFINED = -100000;
    
    private boolean isLeafNode(TreeNode root) {
        return ((root.left == null) && (root.right == null));
    }
    
    private boolean areEqual(int v1, int v2, int v3){
        return ((v1 == v2) && (v1 == v3));
    }
    
    private Pair<Integer, Boolean> dfs(TreeNode node){
        // if null, return a default [can't use it]
        
        // if leaf node, return {1, true}
        if(isLeafNode(node))
            return new Pair<>(1, true);
        
        // normal node, check both children

        // has a left child
        Pair<Integer, Boolean> left     = new Pair<>(UNDEFINED, false);
        Pair<Integer, Boolean> right    = new Pair<>(UNDEFINED, false);
        
        if(node.left != null){
            left = dfs(node.left);
        }
        
        if(node.right != null){
            right = dfs(node.right);
        }
        
        // combine
        int leftVal     = (node.left == null)    ? UNDEFINED : node.left.val;
        int rightVal    = (node.right == null)   ? UNDEFINED : node.right.val;
        
        // left.getKey() + right.getKey() + 1;
        
        int sum = 0;
        boolean isUnique = true;
        
        if(node.left != null){
            // left exists
            sum += left.getKey();
            isUnique &= left.getValue();
            
            isUnique &= (node.left.val == node.val);
        }
        
        if(node.right != null){
            // right exists
            sum += right.getKey();
            isUnique &= right.getValue();
            
            isUnique &= (node.right.val == node.val);
        }
        
        // check for itself using isUnique
        if(isUnique)
            sum++;
        
        Pair<Integer, Boolean> ans = new Pair<>(sum, isUnique);
        // System.out.println("Returning ans: " + ans);
        
        return ans;
    }
    
    public int countUnivalSubtrees(TreeNode root) {
        // edge case
        if(root == null)
            return 0;
        
        return dfs(root).getKey(); // key: num of univalue subtrees && val: isUniqueValue
    }
}