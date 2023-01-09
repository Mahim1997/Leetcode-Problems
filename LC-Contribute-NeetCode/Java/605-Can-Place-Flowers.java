class Solution {
    private static int FLOWER = 1;
    private static int EMPTY = 0;

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // Edge case: 0 flowers
        if(n == 0) {
            return true;
        }

        int countFlowers = 0;
        int len = flowerbed.length;
        
        int lastPlacedFlower = -1;
        for(int i=0; i<len; i++) {
            if (flowerbed[i] == EMPTY) {
                boolean isLeftEmpty = (i == 0) || (
                    (flowerbed[i - 1] == EMPTY) && (lastPlacedFlower != (i - 1))
                );
                boolean isRightEmpty = (i == (len - 1)) || (flowerbed[i + 1] == EMPTY);
                
                // Both empty, flower can be placed
                if(isLeftEmpty && isRightEmpty) {
                    lastPlacedFlower = i;
                    countFlowers++;

                    if(countFlowers == n) {
                        return true;
                    }
                }
            }
        }

        return countFlowers == n;
    }
}