class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // Set<Integer> set = new HashSet<>();
        for(int n: nums){ // add all numbers to a hash set.
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        
        if(k == 0){
            int cnt = 0;
            for(int key: map.keySet()){
                if(map.get(key) > 1){
                    cnt ++;
                }
            }
            return cnt;
        }
        
        
        Set<MyPair> ans = new HashSet<>();
        for(int i=0; i<nums.length; i++){
            int num = nums[i];
            int otherNum1 = k + num;
            int otherNum2 = num - k;
            
            // k == 0 checking.
            // if(set.contains(otherNum1)){
            if(map.containsKey(otherNum1)){
                ans.add(new MyPair(num, otherNum1));
            }
            if(map.containsKey(otherNum2)){
                ans.add(new MyPair(num, otherNum2));
            }
            
        }
        return ans.size();
    }
    
    static void pr(Object o){
        System.out.println(o.toString());
    }

    static class MyPair {
        int a;
        int b;

        MyPair(int a, int b) {
            if (a >= b) { // always keep 'a' smaller than 'b'
                int temp = a;
                a = b;
                b = temp;
            }
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final MyPair other = (MyPair) obj;
            if (this.a != other.a) {
                return false;
            }
            if (this.b != other.b) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + this.a;
            hash = 53 * hash + this.b;
            return hash;
        }

        @Override
        public String toString() {
            return "MyPair{" + "a=" + a + ", b=" + b + '}';
        }

    }
    
    
}