/**
 * // This is Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *     public boolean hasShips(int[] topRight, int[] bottomLeft);
 * }
 */

class Position{
    public int x;
    public int y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
}

class Solution {
    private Sea sea;
    
    private int recursiveCount(Position bottomLeft, Position topRight){
        // base cases
        
        // System.out.println("Calling for bottomLeft = " + bottomLeft 
        //                   + ", topRight = " + topRight);
        
        // exceeds limit
        if(bottomLeft.x > topRight.x)
            return 0;
        if(bottomLeft.y > topRight.y)
            return 0;
        
        // hasShips check
        int[] arrTR = new int[2];
        int[] arrBL = new int[2];

        arrTR[0] = topRight.x;
        arrTR[1] = topRight.y;

        arrBL[0] = bottomLeft.x;
        arrBL[1] = bottomLeft.y;

        if(this.sea.hasShips(arrTR, arrBL) == false)
            return 0;        
        
        // Single point => one ship, otherwise, above will return 0
        if((bottomLeft.x == topRight.x) && 
           (bottomLeft.y == topRight.y)){
            return 1;
        }
        
        Position midPoint = new Position(
            (bottomLeft.x + topRight.x)/2,
            (bottomLeft.y + topRight.y)/2
        );
        
        
        int cnt1 = recursiveCount(
            new Position(bottomLeft.x, bottomLeft.y),
            new Position(midPoint.x, midPoint.y)
        );
        
        int cnt2 = recursiveCount(
            new Position(midPoint.x + 1, bottomLeft.y),
            new Position(topRight.x, midPoint.y)
        );
        
        int cnt3 = recursiveCount(
            new Position(bottomLeft.x, midPoint.y + 1),
            new Position(midPoint.x, topRight.y)
        );
        
        int cnt4 = recursiveCount(
            new Position(midPoint.x + 1, midPoint.y + 1),
            new Position(topRight.x, topRight.y)
        );
        
        
        return (cnt1 + cnt2 + cnt3 + cnt4);
    }
    
    public int countShips(Sea sea, int[] topRight, 
                          int[] bottomLeft) {
        // Binary search in two grids ??
        // return sea.hasShips(topRight, topRight) == true ? 1 : 0;
        
        this.sea = sea;
        
        return recursiveCount(
            new Position(bottomLeft[0], bottomLeft[1]), 
            new Position(topRight[0], topRight[1])
        );
    }
}