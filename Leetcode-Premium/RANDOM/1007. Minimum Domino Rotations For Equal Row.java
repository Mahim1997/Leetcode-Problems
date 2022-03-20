class Solution {    
    public int minDominoRotations(int[] tops, int[] bottoms) {
        
        // retrieve the majority elements
        int[] majoritiesTop = getMajorityElements(tops);
        int[] majoritiesBottom = getMajorityElements(bottoms);
        
        // pre-processing checking
        
        // Case 1: No majority elements for top and bottom.
        if((majoritiesTop[0] == -1) && (majoritiesBottom[0] == -1))
            return -1;

        // Run for TOP
        int minAnswerTop = getMinimumSwaps(majoritiesTop,
                                      tops, bottoms);
        
        
        // Run for BOTTOM
        int minAnswerBottom = getMinimumSwaps(majoritiesBottom,
                                     bottoms, tops);
        
        int answer = Math.min(minAnswerTop, minAnswerBottom);
        
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    private int[] getMajorityElements(int[] arr){
        int[] values = new int[7]; // dominos are 1-6
        Arrays.fill(values, 0); // 1-indexed
        
        // increment counts
        for(int x: arr)
            values[x]++;
        
        int[] answer = new int[]{-1, -1};
        
        int idxAnswer = 0, n = arr.length, halfN = n/2;
        for(int i=1; i<=6; i++){
            if(values[i] >= (halfN)){
                answer[idxAnswer++] = i; // always guaranteed to be 2 length
            }
        }
        return answer;
    }
    
    private int getMinimumSwaps(int[] majorityElements,
                               int[] takeArr, int[] dontTakeArr){
        
        int answer = Integer.MAX_VALUE;
        for(int majorityElement: majorityElements){
            if(majorityElement == -1)
                break;
            
            int counts = 0;
            boolean isPossible = true;

            for(int i=0; i<takeArr.length; i++){
                if(takeArr[i] != majorityElement){
                    if(dontTakeArr[i] == majorityElement){
                        counts++;
                    }else{
                        // cant be done at all
                        isPossible = false;
                        break;                        
                    }
                }
            }
            
            if(isPossible == false)
                continue;
            else
                answer = Math.min(answer, counts);
        }
        
        return answer;
    }
}

