/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(
        TreeNode root, 
        TreeNode p, 
        TreeNode q
    ) {
        // Data structure to store parents
        Map<TreeNode, TreeNode> parentMap = new HashMap<>(); 
        parentMap.put(root, null); // parent[root] = NULL
        
        // Create parents while searching
        find(root, p, parentMap);
        find(root, q, parentMap);
    
        // Find lowest common parent
        Set<TreeNode> visitedSet = new HashSet<>();
        
        // Find all parents in the path for visited set
        visit(p, parentMap, visitedSet);
        return visit(q, parentMap, visitedSet);
    }
    
    private TreeNode visit(
        TreeNode node,
        Map<TreeNode, TreeNode> parentMap, 
        Set<TreeNode> visited
    ) {
        TreeNode itr = node;
        while(itr != null) {
            if(visited.contains(itr)) {
                // This is the first time found common ancestor
                return itr; 
            }
            visited.add(itr);
            itr = parentMap.get(itr);
        }
        return null;
    }
    
    private void find(
        TreeNode root, 
        TreeNode nodeToFind, 
        Map<TreeNode, TreeNode> parentMap
    ) {
         // BFS style
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            for(int i=0; i<qSize; i++) {
                TreeNode poppedNode = queue.remove();
                if(poppedNode == nodeToFind)
                    return;
                addChildren(poppedNode, queue, parentMap);
            }
        }
    }
    
    private void addChildren(
        TreeNode parent, 
        Queue<TreeNode> queue, 
        Map<TreeNode, TreeNode> parentMap
    ) {
        addChild(parent.left, parent, queue, parentMap);
        addChild(parent.right, parent, queue, parentMap);
    }
    
    private void addChild(
        TreeNode child,
        TreeNode parent,
        Queue<TreeNode> queue,
        Map<TreeNode, TreeNode> parentMap
    ) {
        if(child == null)   return;
        parentMap.put(child, parent); // key: child, val: parent
        queue.add(child);
    }
}


