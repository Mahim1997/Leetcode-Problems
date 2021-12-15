class Solution {
    public int maxNumberOfBalloons(String text) {
        // return solveUsingArray(text);
        return solveUsingVariables(text);
    }
    
    private int solveUsingVariables(String text){
        int bCnt = 0, aCnt = 0, lCnt = 0, oCnt = 0, nCnt = 0;
        // target word: balloon
        for(int i=0; i<text.length(); i++){
            char c = text.charAt(i);
            switch(c){
                case 'b':
                    bCnt++;
                    break;
                case 'a':
                    aCnt++;
                    break;
                case 'l':
                    lCnt++;
                    break;
                case 'o':
                    oCnt++;
                    break;
                case 'n':
                    nCnt++;
                    break;
                    
            }
        }
        
        // actually need HALF for balloon
        lCnt /= 2;
        oCnt /= 2;
        
        return getMinimum(new int[]{bCnt, aCnt, lCnt, oCnt, nCnt});
    }
    
    private int getMinimum(int[] arr){
        int min = Integer.MAX_VALUE;
        for(int a: arr){
            min = Math.min(min, a);
        }
        return min;
    }
    
    private int solveUsingArray(String text){
        int[] frequencies = new int[26];
        
        for(int i=0; i<text.length(); i++){
            char currentChar = text.charAt(i);
            frequencies[currentChar - 'a']++;
        }
        
        return getCountOfWord(frequencies, "balloon");
    }
    
    private int getCountOfWord(int[] frequencies, String text){
        int count = 0, ans;
        while(true){
            ans = getCountOfWordOnce(frequencies, text);
            if(ans == 1){
                count++;
            }else{
                break;
            }
        }
        return count;    
    }
    
    private void printArray(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private int getCountOfWordOnce(int[] frequencies, String text){
        for(int i=0; i<text.length(); i++){
            int idx = text.charAt(i) - 'a';
            if(frequencies[idx] <= 0){
                return -1;
            }else{
                frequencies[idx]--;
            }
        }
        return 1;
    }
}