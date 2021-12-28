class Solution {
    public int[] replaceElements(int[] arr) {
        if(arr.length == 1){
            arr[0] = -1;
            return arr;
        }
        
        for(int i=0; i<arr.length-1; i++){
            int num = Integer.MIN_VALUE;
            for(int j=i+1; j<arr.length; j++){
                num = Math.max(num, arr[j]);
            }
            arr[i] = num;
        }
        
        arr[arr.length - 1] = -1;
        
        return arr;
    }
}