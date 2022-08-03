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
    private int globalMin;
    private int globalMax;
    
    private Map<Pair<Integer, Integer>, List<TreeNode>> cache;
    
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> list = new ArrayList<>();
    
        this.globalMin = 1;
        this.globalMax = n;
        this.cache = new HashMap<>();
        
        return dfs(globalMin, globalMax);
    }
    
    // Inclusive of min and max
    private List<TreeNode> dfs(int min, int max) {
        // System.out.println("Calling for min = " + min + ", max = " + max);
        Pair<Integer, Integer> key = new Pair<>(min, max);
        if(this.cache.containsKey(key))
            return this.cache.get(key);
        
        List<TreeNode> list = new ArrayList<>();
        // Base cases
        if(min > max)
            return list; // return empty list
        if(min == max) {
            list.add(new TreeNode(max));
            return list;
        }
        
        for(int i=min; i<=max; i++) {
            List<TreeNode> leftList  = dfs(min, i - 1);
            List<TreeNode> rightList = dfs(i + 1, max);
        
            // merge each by doing cross set product
            
            // check if any empty
            if(leftList.isEmpty()) {
                for(TreeNode rightNode: rightList) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = null;
                    currentNode.right = rightNode;
                    list.add(currentNode);
                }      
            }
            else if(rightList.isEmpty()) {
                for(TreeNode leftNode: leftList) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.right = null;
                    currentNode.left = leftNode;
                    list.add(currentNode);
                }                            
            }
            else {
                // both exists
                for(TreeNode leftNode: leftList) {
                    for(TreeNode rightNode: rightList) {
                        TreeNode currentNode = new TreeNode(i);
                        currentNode.left = leftNode;
                        currentNode.right = rightNode;
                        list.add(currentNode);
                    }
                }
            }
        }    

        this.cache.put(key, list);
        return list;
    }
}




