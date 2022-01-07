class Solution {
    private void printArray(int[][] trips){
        for(int[] x: trips){
            System.out.println(x[0] + " " + x[1] + " " + x[2]);
        }
    }
    
    public boolean carPooling(int[][] trips, int capacity) {
        // sort wrt starting distances
        
        // return markAllDistances(trips, capacity);
        return markChangedDistances(trips, capacity);
    }
    
    private boolean markChangedDistances(int[][] trips, 
                                         int capacity){
        Arrays.sort(trips, (int[] x1, int[] x2) -> 
                    (x1[1] - x2[1]));
        
        int[] timeChanges = new int[1000 + 1];
        // Arrays.fill(timeChanges, 0); // by default 0
        for(int[] trip: trips){
            // increment by no. of passengers
            timeChanges[trip[1]] += trip[0]; 
            // decrement by no. of passengers
            timeChanges[trip[2]] -= trip[0];
        }
        
        // now recheck
        int passengersTaken = 0;
        for(int timeChange: timeChanges){
            passengersTaken += timeChange;
            if(passengersTaken > capacity){
                return false;
            }
        }
        
        return true;
    }
    
    private boolean markAllDistances(int[][] trips, int capacity){
        Arrays.sort(trips, (int[] x1, int[] x2) -> (x1[1] - x2[1]));

        int maxDistance = 1000;
        int[] distances = new int[maxDistance + 1];
        Arrays.fill(distances, 0); // initially all capacities are 0
        
        
        for(int i=0; i<trips.length; i++){
            int[] trip = trips[i];
            
            for(int time=trip[1]; time<trip[2]; time++){
                // that trip's num passengers
                distances[time] += trip[0]; 
            }
        }
        
        for(int i=0; i<=1000; i++){
            if(distances[i] > capacity)
                return false;
        }
        
        
        return true;
    }
}