class Solution {
    private int factorial(int n){
        int ans = 1;
        while(n >= 1){
            ans *= n;
            n--;
        }
        return ans;
    }
    
    public String getPermutation(int n, int k) {
        
        // base case
        if(n == 1){
            return "1";
        }
        
        // int n_minus1_fact = factorial(n-1);
        
        
        //eg. n=4 -> (n-1)! = 6
        // k-1 = 4/6 = 0 -> 1st bucket
        // k-1 = 6/6 = 1 -> 2nd bucket
        // k-1 = 5/6 = 0 [0th offset]
        
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++){
            list.add(i);
        }
        // System.out.println("Initially, printing list: "
        //       + list.stream().map(String::valueOf).collect(Collectors.joining(", "))
              // );
        
        StringBuilder bld = new StringBuilder();
        
        // int which_bucket = (k - 1) / n_minus1_fact;

        while(n > 1){
            int n_minus1_fact = factorial(n-1);
            // int n_fact = factorial(n);
            
            int bucket_id = (k - 1) / n_minus1_fact;
            
            // System.out.println("(n-1)! = " + n_minus1_fact + " , n = " + n + " k = " + k + " bucketID = " + bucket_id);
            
            // transform 'k'
            // k = k % n_minus1_fact;
            k -= bucket_id*n_minus1_fact;
            
            // transform 'n'
            n--;
            
            // get element and put into string
            int element = list.get(bucket_id);
            bld.append(String.valueOf(element));
            
            
            // remove the element, use new Integer() to remove by element.
            list.remove(new Integer(element));
            
            // System.out.println("Adding element = " + element + ", printing list: "
            //   + list.stream().map(String::valueOf).collect(Collectors.joining(", "))
            //   );
        }
        
        // last append ?
        for(int i=0; i<list.size(); i++){
            bld.append(String.valueOf(list.get(i)));
        }
        
        return bld.toString();
        
        
    }
}