class Solution {
    public void sortColors(int[] nums) {
        // twoPasses(nums);
        onePass(nums);
    }
    
    private void printArr(int[] arr){
        for(int x: arr){System.out.print(x + " ");}
        System.out.println("");
    }
    
    private void onePass(int[] nums){
        // fill up zeros from left to right
        // fill up twos  from right to left
        // place ones in the middle
        int n = nums.length;
        int idxZeros = 0;
        int idxTwos = n - 1;

        for(int i=0; i<n; ){
            // 0 -> SWAP with idxZeros, increment idxZeros, increment i
            // 1 -> keep it as is, increment i
            // 2 -> SWAP with idxTwos, DON't increment i, decrement idxTwos
            // if idxZeros == idxTwos, break
            int num = nums[i];
            
            if(num == 0){
                int temp = nums[i];
                nums[i] = nums[idxZeros];
                nums[idxZeros] = temp;
                idxZeros++;
                i++;
            }
            else if(num == 1){
                i++;
            }
            else if(num == 2){
                int temp = nums[i];
                nums[i] = nums[idxTwos];
                nums[idxTwos] = temp;
                idxTwos--;
                // DON'T increment i
            }
            
            // System.out.println("AFTERWARDS, i = " + i + ", idxZeros = " 
            //                    + idxZeros + ", idxTwos = " + idxTwos);
            // printArr(nums);
            
            if(i == (idxTwos+1)){break;} // anything [idxTwos, ..., n-1] are sorted
        }
    }
    
    private void twoPasses(int[] nums){
        int countZeros = 0, countOnes = 0, countTwos = 0;
        for(int num: nums){
            if(num == 0){
                countZeros++;
            }
            else if(num == 1){
                countOnes++;
            }
            else if(num == 2){
                countTwos++;
            }
        }
        
        int i = 0;
        i = placeReturnIndex(nums,i, 0, countZeros);
        i = placeReturnIndex(nums,i, 1, countOnes);
        i = placeReturnIndex(nums,i, 2, countTwos);
        return;
    }
    private int placeReturnIndex(int[] nums, int start,
                                 int value, int countValue){
        int i = start;
        for(; i<nums.length; i++){
            if(countValue == 0){break;}
            nums[i] = value;
            countValue--;
        }
        return i;
    }
}