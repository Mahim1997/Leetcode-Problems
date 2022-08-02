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
    private int getHeight(
        TreeNode root, 
        List<List<Integer>> listAnswer
    ) {
        if(root == null)
            return -1; // so 'leaves' have 0-level

        int height = 1 + Math.max(
            getHeight(root.left, listAnswer),
            getHeight(root.right, listAnswer)
        );            
        
        // check if this index is present in listAnswer
        if(listAnswer.size() == height) {
            listAnswer.add(new ArrayList<>());
        }
        listAnswer.get(height).add(root.val);
        
        return height;
    }
    
    // API
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> listAnswer = new ArrayList<>();
        Map<TreeNode, Integer> mapHeights = new HashMap<>();
        
        int maxHeight = getHeight(root, listAnswer);
        
        return listAnswer;
    }
}






