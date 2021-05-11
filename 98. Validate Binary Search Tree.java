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
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // inorder traversal.
        TreeNode temp = root;
        inorder(temp, list);
        System.out.println(list);
        
        // distincts always.
        
        if(list.size() == 2){
            if(root.left != null){
                if(root.left.val < root.val)
                    return true;
                else
                    return false;
            }
            if(root.right != null){
                if(root.right.val > root.val)
                    return true;
                else
                    return false;
            }
        }
        for(int i=1; i<list.size()-1; i++){
            if(!((list.get(i) > list.get(i-1)) && 
               (list.get(i) < list.get(i+1))))
                return false;
        }
        
        return true;
    }
    private void inorder(TreeNode root, List<Integer> list){
        if(root.left != null){
            inorder(root.left, list);
        }
        list.add(root.val);
        if(root.right != null){
            inorder(root.right, list);
        }
    }
	
// Faster implementation
    private int prev = -1; // changes in between
    private boolean inorderCheck(TreeNode root, int prev){
        
        // Inorder traversal checking.
        boolean ans = true;
        
        if(root.left != null){
            // use the recursive calling [AND]
            ans = ans & inorderCheck(root.left);
        }
        // self checking with previous val.
        if(prev == -1)
            prev = root.val; // first time setting
        else{
            if(prev >= root.val){ // violation checking
                return false;
            }
            prev = root.val; // set previous to current val
        }
        
        if(root.right != null){
            //same checking as left subtree
            ans = ans & inorderCheck(root.right);
        }
        
        return ans;
    }
}