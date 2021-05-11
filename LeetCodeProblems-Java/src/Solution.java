import java.util.*;
import java.util.stream.Collectors;

class Solution {

    private double[] get_m_c(int p1_x, int p1_y, int p2_x, int p2_y){
        double[] arr = new double[2];
        if(p2_x == p1_x){
            arr[0] = Double.MAX_VALUE;
        }else if(p2_y == p1_y){
            arr[0] = 0;
        }
        else{
            arr[0] = (p2_y - p1_y)/(double)(p2_x - p1_x); //m
        }

        arr[1] = p1_y - arr[0]*p1_x; // c = y_1 - m*x_1
        return arr;
    }

    public int maxPoints(int[][] points) {
        // 2-D all vs all
        // store 'm' and 'c'

        int n = points.length;
        if(n == 1)
            return 1;

//        Position[][] dp = new Position[n][n];
        Map<Position, Integer> map = new HashMap<>();

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                double[]arr = get_m_c(points[i][0], points[i][1], points[j][0], points[j][1]);

//                System.out.println("P1: (" + points[i][0] + "," + points[i][1] + "), P2: (" + points[j][0] + "," + points[j][1] + ")" +
//                        ", m = " + arr[0] + " , c = " + arr[1]);

                Position p = new Position(arr[0], arr[1]);
                map.putIfAbsent(p, 0);
                map.put(p, map.get(p)+1);
            }
        }

        System.out.println(map);

        int nC2 = Collections.max(map.values());
        int ans = ( 1 + (int)Math.sqrt(1 + 8*nC2) ) / 2;

        return ans;
    }
}
class Position{
    double m;
    double c;

    public Position(double m, double c) {
        this.m = m;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Double.compare(position.m, m) == 0 && Double.compare(position.c, c) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m, c);
    }

    @Override
    public String toString() {
        return "Position{" +
                "m=" + m +
                ", c=" + c +
                '}';
    }
}
