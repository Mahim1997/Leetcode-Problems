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
        List<List<Integer>> ansList = new ArrayList<>();
        dfs(root, ansList);
        return ansList;
    }
    
    private boolean isLeaf(TreeNode node) {
        return ((node.left == null) && (node.right == null));
    }
    
    private int dfs(
        TreeNode node, 
        List<List<Integer>> ansList
    ) {
        // Base case
        if(node == null)
            return -1;
        
        // Recursive
        int level;
        if(isLeaf(node)) {
            level = 0;
        }
        else {
            level = 1 + Math.max(
                dfs(node.left, ansList),
                dfs(node.right, ansList)
            );
        }
        
        int ansListSize = ansList.size();
        if(ansList.size() <= level) {
            for(int i=ansListSize; i<=level; i++)
                ansList.add(new ArrayList<>());
        }
        ansList.get(level).add(node.val);
                
        return level;
    }
}



