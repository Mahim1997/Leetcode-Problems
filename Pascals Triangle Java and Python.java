/*
## Python
from math import comb


class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        # print(comb(10,0))
        ans = []
        for n in range(0, numRows):
            ans.append([comb(n, i) for i in range(0, n+1)])
            
        return ans

*/

class Solution {
    private int nCr(int n, int r){
        if(r == 0)
            return 1;
        if(r == n)
            return 1;
        
        if(r > n/2){
            r = n - r;
        }
        long numerator = 1;
        for(int i=0; i<r; i++){
            numerator *= (n - i);
        }
        long denominator = 1;
        for(int i=1; i<=r; i++){
            denominator *= i;
        }
        return (int)(numerator/denominator);
        
    }
    private List<Integer> generateRow(int n){
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i<=n; i++){ // nC0, nC1, ..., nCn
            ans.add(nCr(n, i));
        }
        return ans;
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int row=0; row<numRows; row++){ // n => 0 to numRows-1
            ans.add(generateRow(row));
        }
        return ans;
    }
}
