class MyComparator implements Comparator<String>{
    
    private int compareRecursive(String s1, String s2){
        int s1Len = s1.length(), s2Len = s2.length();
        int minLen = Math.min(s1Len, s2Len);
        
        int answer = 0;
        
        // base case [either is fine]
        if((s1Len == 0) || (s2Len == 0))
            return 0;
        
        // equal checking
        boolean foundAns = false;
        for(int i=0; i<minLen; i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            if(c1 < c2){
                // eg. ["34", "55"] => "55" should come first
                // -1 means less than the other [appear first]
                answer = -1; 
                foundAns = true;
                break;
            }
            else if(c1 > c2){
                // eg. ["55", "34"] => "55" should come first
                // 1 means greater than the other [appear second]
                answer = 1; 
                foundAns = true;
                break;
            }
            else{
                // equal, continue
                continue;
            }
            
        }
        
        // recursive
        String str1New, str2New;
        
        if(foundAns == false){
            // need to reloop, recursively ?
            if(s1Len < s2Len){
                // s1 is smaller
                // compare s1[all] with s2[idx2+minLen: ]
                str1New = s1;
                str2New = s2.substring(minLen, s2Len);
            }else{
                // s1 is bigger
                // compare s2[all] with s1[idx1+minLen: ]
                str2New = s2;
                str1New = s1.substring(minLen, s1Len);
            }
            
            return compareRecursive(str1New, str2New);
        }
        
        // reverse of logic here (descending order sort)
        return -answer;
    }
    
    @Override
    public int compare(String s1, String s2){
        // compare upto smaller length.
        
        /*
            Guaranteed to be unique
            otherwise, edge case checking
            if(s1.equals(s2)){return 0;}
        */
        
        return compareRecursive(s1, s2);
    }
}

class Solution {
    public String largestNumber(int[] nums) {
        // edge case -> all are zeros
        
        boolean allZeros = true;
        String[] arr = new String[nums.length];
        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                allZeros = false;
            }
            arr[i] = String.valueOf(nums[i]);
        }
        
        // edge case ->> all are zeros
        if(allZeros == true){
            return "0";
        }
        
        // guaranteed to be unique
        Arrays.sort(arr, new MyComparator());
        
        String number = Arrays.stream(arr)
                    .collect(Collectors.joining(""));
        
        return number;
        // rechange it back [000 -> 0]
        // return String.valueOf(Integer.parseInt(number));
    }
}