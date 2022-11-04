/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // directly preorder
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list
                .stream()
                .map(x -> String.valueOf(x))
                .collect(Collectors.joining(","));
    }

    private void preorder(TreeNode node, List<Integer> list) {
        // Base case
        if(node == null) { return; }

        // visit self
        list.add(node.val);

        // recurse on 1st left subtree, 2nd right subtree    
        preorder(node.left, list);
        preorder(node.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Base case: Empty node
        if(data.isEmpty() || data.isBlank()) {
            return null;
        }

        System.out.println("data: " + data);
        // Get the integer list back
        String[] strArr = data.split(",");
        List<Integer> list = Arrays
                .stream(strArr)
                .map(x -> Integer.parseInt(x))
                .collect(Collectors.toList());

        // Recurse .
        int left = 0, right = list.size() - 1;
        return recurse(list, left, right);
    }

    private TreeNode recurse(
        List<Integer> list,
        int left,
        int right
    ) {
        // Create a tree node with the 'left' value
        // rightIdx = Find the node which has a HIGHER value
        // node.left  = recurse[left + 1, rightIdx - 1] // inclusive
        // node.right = recurse[rightIdx, right] // inclusive
        // If rightIdx not found, then node.right = null
        // If left > right, return (by default: node.left will be null)

        // Base case: left > right
        if(left > right) {
            return null;
        }
        int nodeVal = list.get(left);
        TreeNode node = new TreeNode(nodeVal);

        int rightIdx = -1;
        // Find the FIRST index with value > nodeVal
        for(int i=left; i<=right; i++) {
            if(list.get(i) > nodeVal) {
                rightIdx = i;
                break;
            }
        }

        if(rightIdx != -1) {
            node.left = recurse(list, left + 1, rightIdx - 1);
        }
        else {
            node.left = recurse(list, left + 1, right);
        }


        if(rightIdx != -1) {
            node.right = recurse(list, rightIdx, right);
        }

        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;