class Solution {
    private int[] flowerbed;
    
    private boolean isEmpty(int idx){
        if(idx < 0)
            return true;
        if(idx >= flowerbed.length)
            return true;
        
        return (flowerbed[idx] == 0);
    }
    
    
    public boolean canPlaceFlowers(int[] flowerbed, int amount) {
        this.flowerbed = flowerbed;
        
        if(amount == 0)
            return true;
        
        int n = flowerbed.length;
        for(int i=0; i<n; i++){

            if(flowerbed[i] == 1){
                continue;
            }
            
            if(i == 0){ // only check right side
                if(isEmpty(i + 1)){
                    flowerbed[i] = 1;
                    amount--;
                }
            }
            else if(i == (n - 1)){ // only check left side
                if(isEmpty(i - 1)){
                    flowerbed[i] = 1;
                    amount--;
                }
            }
            else{
                if((isEmpty(i - 1)) && (isEmpty(i + 1))){
                    flowerbed[i] = 1;
                    amount--;
                }
            }
            
            if(amount <= 0)
                return true;
        }
        
        return false;
    }
}