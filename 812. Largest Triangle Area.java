class Solution {
    private double getArea(int a1, int b1, int a2, int b2, int a3, int b3){
        return 0.5 * Math.abs(
            (       (a1*(b2 - b3))
                -   (a2*(b1 - b3))
                +   (a3*(b1 - b2))
            )
        );
    }
    
    public double largestTriangleArea(int[][] points) {
        double maxArea = 0;
        
        for(int i=0; i<points.length-2; i++){
            for(int j=i+1; j<points.length-1; j++){
                for(int k=j+1; k<points.length; k++){
                    
                    int[] p1 = points[i];
                    int[] p2 = points[j];
                    int[] p3 = points[k];
                    
                    double currentArea = getArea(p1[0], p1[1],
                                                p2[0], p2[1],
                                                p3[0], p3[1]);
                    
                    maxArea = Math.max(maxArea, currentArea);
                    
                    
                }
            }
        }
        return maxArea;
    }
}