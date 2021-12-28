class Solution {
    public void duplicateZeros(int[] arr) {
        if(arr.length <= 1)
            return;
        if(arr.length == 2){
            if(arr[0] == 0){
                arr[1] = 0;
                return;
            }
        }
        
        for(int i=0; i<(arr.length - 1); i++){
            if(arr[i] == 0){
                // shift
                // for(int j=i+2; j<arr.length; j++){
                for(int j=arr.length-1; j>=(i + 2); j--){
                    arr[j] = arr[j - 1];
                }
                arr[i + 1] = 0;
                i++;
            }
        }
        
        // if(arr[arr.length - 2] == 0){
        //     arr[arr.length - 1] = 0;
        // }
    }
}