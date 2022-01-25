class Solution{
    class Triplet{
        int diff;
        int aliceValue;
        int bobValue;
        int sum;
        
        public Triplet(int a, int b){
            this.aliceValue = a;
            this.bobValue = b;
            this.diff = this.aliceValue - this.bobValue;
            this.sum = a + b;
        }
        
//         @Override
//         public int hashCode(){
//             int hash = 13;
//             hash = hash*15 + this.aliceValue;
//             hash = hash*15 + this.bobValue;
//             hash = hash*15 + this.diff;
//             return hash;
//         }
    
//         @Override
//         public boolean equals(Object o){
//             Triplet other = (Triplet) o;
//             if(other.aliceValue != this.aliceValue)
//                 return false;
//             if(other.bobValue != this.bobValue)
//                 return false;
//             if(other.diff != this.diff)
//                 return false;
            
//             return true;
//         }
        
        @Override
        public String toString(){
            return "aliceVal = " + aliceValue + ", bobVal = " + bobValue 
                + ", diff = " + diff + ", sum = " + sum;
        }
        
    }
    
    
    
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        Triplet[] triplets = new Triplet[n];
        
        for(int i=0; i<n; i++){
            triplets[i] = new Triplet(aliceValues[i], bobValues[i]);
        }
        
        // descending order.
        // sum because self gain + oppponent's loss
        Arrays.sort(triplets, (t1, t2) -> (t2.sum - t1.sum));
        
        // DEBUGGING
        // for(Triplet t: triplets){
        //     System.out.println(t.toString());
        // }
        
        
        int alice=0, bob=0;
        for(int idx=0; idx<n; idx++){
            if(idx%2 == 0) // EVEN
                alice += triplets[idx].aliceValue;
            else
                bob += triplets[idx].bobValue;
        }
        
        if(alice > bob){
            return 1;
        }
        if(alice == bob){
            return 0;
        }
        if(alice < bob){
            return -1;
        }
        return -1;
    }
    
}