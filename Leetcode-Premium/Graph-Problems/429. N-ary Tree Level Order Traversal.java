/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        // pure BFS
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        
        bfs(root, list);
        return list;
    }
    
    private void bfs(Node root, List<List<Integer>> list){
        Queue<Node> queue = new LinkedList<>();
        // Tree -> no need for 'visited'
        
        queue.add(root);
        while(!queue.isEmpty()){
            int qSize = queue.size();
            List<Integer> currentLevelList = new ArrayList<>();
            for(int i=0; i<qSize; i++){
                Node item = queue.remove();
                
                // add current item onto current level's list
                currentLevelList.add(item.val);
                
                // add children into queue
                // for(Node child: item.children){
                //     queue.add(child);
                // }
                queue.addAll(item.children);
            }
            // add a clone
            list.add(new ArrayList<>(currentLevelList));
        }
    }
}