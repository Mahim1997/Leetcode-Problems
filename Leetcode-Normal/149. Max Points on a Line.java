import java.util.*;
import java.util.stream.Collectors;

class Required4Values {
    int m_numerator;
    int m_denominator;
    int c_numerator;
    int c_denominator;


    private int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    public Required4Values() {
        this.m_denominator = 1;
        this.m_numerator = 1;
        this.c_denominator = 1;
        this.c_numerator = 1;
    }

    public Required4Values(int m_denominator, int m_numerator, int c_denominator, int c_numerator) {
        this.m_denominator = m_denominator;
        this.m_numerator = m_numerator;
        this.c_denominator = c_denominator;
        this.c_numerator = c_numerator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Required4Values that = (Required4Values) o;

        // a/b == c/d -> a*d == b*c
        if ((this.m_numerator * that.m_denominator) != (that.m_numerator * this.m_denominator)) {
            return false;
        }
        if ((this.c_numerator * that.c_denominator) != (that.c_numerator * this.c_denominator)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(m_denominator, m_numerator, c_denominator, c_numerator);
    }

    @Override
    public String toString() {
        return "Req4Vals{" +
                "m_n=" + m_numerator +
                ", m_d=" + m_denominator +
                ", c_n=" + c_numerator +
                ", c_d=" + c_denominator +
                '}';
    }

    public void normalize() {
        // normalize numerators and denominators.

        // special case checking
        if((this.m_numerator == 0) || (this.m_denominator == 0)){
            return;
        }

        int gcd_m = gcd(this.m_numerator, this.m_denominator);
        this.m_numerator /= gcd_m;
        this.m_denominator /= gcd_m;

        if(this.m_denominator < 0){
            this.m_denominator *= -1;
            this.m_numerator *= -1;
        }

        int gcd_c = gcd(this.c_numerator, this.c_denominator);
        this.c_numerator /= gcd_c;
        this.c_denominator /= gcd_c;

        if(this.c_denominator < 0){
            this.c_denominator *= -1;
            this.c_numerator *= -1;
        }
    }
}

class Solution {

    public Required4Values get_m_c(int p1_x, int p1_y, int p2_x, int p2_y) {
        Required4Values req4Vals = new Required4Values();

        // axes equal checking.
        if (p1_x == p2_x) { // x-axis equal, m = 1/0 -> INFINITY, c = 0/0
            req4Vals.m_numerator = 1;
            req4Vals.m_denominator = 0;
            req4Vals.c_numerator = p1_x;
            req4Vals.c_denominator = 0;
            return req4Vals;
        }
        if (p1_y == p2_y) { // y-axis equal, m = 0/1  -> c = y_val
            req4Vals.m_numerator = 0;
            req4Vals.m_denominator = 1;
            req4Vals.c_numerator = p1_y; // same y value
            req4Vals.c_denominator = 1;
            return req4Vals;
        }

        // normal computations.

        // m = (y2 - y1) / (x2 - x1) [FORMAT: numerator always positive]
        int numerator = p2_y - p1_y;
        int denominator = p2_x - p1_x;
//        if (denominator < 0) {
//            denominator = -denominator;
//            numerator = -numerator;
//        }
        req4Vals.m_numerator = numerator;
        req4Vals.m_denominator = denominator;

        // c = [ m_d*y_1 - m_u*x_1] / m_u ; where m = m_u/m_d
        numerator = req4Vals.m_denominator * p1_y - req4Vals.m_numerator * p1_x;
        denominator = req4Vals.m_numerator;
//        if (denominator < 0) {
//            denominator = -denominator;
//            numerator = -numerator;
//        }
        req4Vals.c_numerator = numerator;
        req4Vals.c_denominator = denominator;

        req4Vals.normalize();

        return req4Vals;
    }
    private void printMap(Map<Required4Values, Integer> map){
        for(Required4Values req: map.keySet()){
            System.out.println(req.toString() + ": " + map.get(req));
        }
    }
    public int maxPoints(int[][] points) {
        // 2-D all vs all
        // store 'm' and 'c'

        int n = points.length;
        if (n == 1)
            return 1;

        Map<Required4Values, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Required4Values required4Values = get_m_c(points[i][0], points[i][1],
                        points[j][0], points[j][1]);
                map.put(required4Values, map.getOrDefault(required4Values, 0) + 1); // increment
            }
        }

//        System.out.println(map);
//        printMap(map);

        int nC2 = Collections.max(map.values());
        int ans = ( 1 + (int)Math.sqrt(1 + 8*nC2) ) / 2;

//        System.out.println("nC2 = " + nC2 + " , ans = " + ans);

        return ans;
    }
}
