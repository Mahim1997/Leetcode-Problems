/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 1, r = Integer.MAX_VALUE;
        
        int m;
        while(l <= r){
            m = l + (r - l)/2;
            
            int pick = guess(m);
            if(pick == 0) // found
                return m;
            
            if(pick == -1){
                // guess is higher than 'n'
                // search left side
                r = m - 1;
            }
            else{
                // guess is lower than 'n'
                // search right side
                l = m + 1;
            }
        }
        
        return -1;
    }
}