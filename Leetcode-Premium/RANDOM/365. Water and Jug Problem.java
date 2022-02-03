class Solution {
    public boolean canMeasureWater(int jug1Capacity, 
                                   int jug2Capacity, 
                                   int targetCapacity) {
        int x = jug1Capacity;
        int y = jug2Capacity;
        int z = targetCapacity;
        
        if(z > (x + y)){
            return false;
        }
        if(x == 0){
            return (y == z) || (z == 0);
        }
        if(y == 0){
            return (x == z) || (z == 0);
        }
        
        // BFS
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        
        // Fill 'x', Fill 'y', Empty 'x', Empty 'y'
        int[] directions = {x, y, -x, -y}; // +x, +y, -x, -y
        queue.add(0);
        visited.add(0);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int valTop = queue.remove();
                
                for(int d: directions){
                    int newVal = valTop + d;
                    
                    // Goal node is found
                    if(newVal == z)
                        return true;
                    
                    if(visited.contains(newVal))
                        continue;
                    
                    if((newVal > 0) && (newVal < (x + y))){
                        queue.add(newVal);
                        visited.add(newVal);
                    }
                }
            }
        }
        
        return false;
    }
}