class Solution {
    public int[] singleNumber(int[] nums) {
        // 11111101010101011	a
//         10000010110101011	b
//         -------------------------
//         01111111100000000	a^b

//         10000000011111111	~(a^b)
//         10000000100000000	~(a^b)+1 = -(a^b)

        // 00000000100000000	(a^b) & -(a^b)
    
        int xor_arr = 0;
        for(int x: nums){
            xor_arr ^= x; // = a^b
        }
        
        // find the mask such that the first bit
        // wrt right side is 1, and rest all bits are 0
        // can be any bit with a difference between 'a' and 'b'
        int mask = xor_arr & (-xor_arr);
        
        int ans_1 = 0, ans_2 = 0;
        // split the array with respect to this bip
        for(int x: nums){
            if((x&mask) == 0){
                ans_1 = ans_1 ^ x;
            }else{
                ans_2 = ans_2 ^ x;
            }
        }
        return new int[]{ans_1, ans_2};
    }
}