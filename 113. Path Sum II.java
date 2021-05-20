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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> answer = new ArrayList<>();
        
        // uses recursion
        pathSumRecursion(root, 0, targetSum, new ArrayList<>(), answer);
        
        return answer;
    }
    
    private void pathSumRecursion(TreeNode node, 
                          int sumSoFar, 
                          int target,
                          List<Integer> currentPath,
                          List<List<Integer>> answer){
        // node null case
        if(node == null){
            return; // dont remove anything.
        }
        
        // STORE STATE
        currentPath.add(node.val); // add current node
        
////////////////////////// Backtracking begins \\\\\\\\\\\\\\\\\\\\\\\\\

        // System.out.println("Node.val = " + node.val + " , currentPath = " + currentPath.stream().map(String::valueOf).collect(Collectors.joining(",")));
        
        // leaf case [good case]
        if((node.left == null) && (node.right == null)){
            if((sumSoFar + node.val) == target){                 
                answer.add(new ArrayList<>(currentPath)); // this path is one possible answer
            }
        }
        
        // recursion left child
        pathSumRecursion(node.left, sumSoFar+node.val, target, currentPath, answer); 
        // recursion right child
        pathSumRecursion(node.right, sumSoFar+node.val, target, currentPath, answer);
        
////////////////////////// Backtracking ends \\\\\\\\\\\\\\\\\\\\\\\\\

        // RESTORE STATE
        currentPath.remove(currentPath.size() - 1);
        
    }
}