class Solution {
    public int compareVersion(String version1, String version2) {
        // 1.01 and 1.001 are the same
        
        // naive solution
        // split by dots
        // compare left side, right side
        
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        
        int n1 = arr1.length, n2 = arr2.length;
        // int minLength = Math.min(arr1.length, arr2.length);
        int maxLength = Math.max(arr1.length, arr2.length);
        // Traverse upto the max length directly, instead of two loops
        for(int i=0; i<maxLength; i++){
            int num1 = (i < n1) ? Integer.parseInt(arr1[i]) : 0;
            int num2 = (i < n2) ? Integer.parseInt(arr2[i]) : 0;
            
            // or, simply use compareStrings(arr1[i], arr2[i]) function
            if(num1 < num2)
                return -1;
            if(num1 > num2)
                return 1;
            
            // else, simply continue
        }
        
        // none are equal
        return 0;
    }
    
    private int compareUsingArrayIntegers(String version1, String version2){
        // 1.01 and 1.001 are the same
        
        // naive solution
        // split by dots
        // compare left side, right side
        
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        
        // create integer arrays
        int maxLen = Math.max(arr1.length, arr2.length);
        int[] arr1Int = new int[maxLen];
        int[] arr2Int = new int[maxLen];
        
        // fill every revision with 0s
        Arrays.fill(arr1Int, 0);
        Arrays.fill(arr2Int, 0);
        
        // Now, fill each string's integer counterpart
        for(int i=0; i<arr1.length; i++){
            arr1Int[i] = Integer.parseInt(arr1[i]);
        }
        for(int i=0; i<arr2.length; i++){
            arr2Int[i] = Integer.parseInt(arr2[i]);
        }
        
        // Now, compare (by default, integer arrays have zero values)
        for(int i=0; i<arr1Int.length; i++){
            if(arr1Int[i] < arr2Int[i])
                return -1;
            if(arr1Int[i] > arr2Int[i])
                return 1;
        }
        
        // none are equal
        return 0;
    }
}