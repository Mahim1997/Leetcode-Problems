class Solution {    
    static int[] coins = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    static String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    
    public String intToRoman(int num) {  
        // Greedy ?
        return greedy(num);
    }
    
    private String greedy(int num){
        StringBuilder bld = new StringBuilder();
        int temp = num;
        int currentLargestCoinsIdx = coins.length - 1;
        int currentLargestCoinValue;
        while(temp > 0){
            currentLargestCoinValue = coins[currentLargestCoinsIdx];
            if(temp >= coins[currentLargestCoinsIdx]){
                bld.append(romans[currentLargestCoinsIdx]);
                temp -= currentLargestCoinValue;
            }else{
                currentLargestCoinsIdx--;
            }
        }
        
        
        return bld.toString();
    }
    
}
