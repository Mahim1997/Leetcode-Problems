// Naive Implementation
class StockSpanner {

    List<Integer> list;
    
    public StockSpanner() {
        this.list = new ArrayList<>();
    }
    
    public int next(int price) {
        this.list.add(price);
        int cnt = 0;
        int idx = this.list.size() - 1;
        for(int i=idx; i>=0; i--){
            if(this.list.get(i) <= price){
                cnt++;
            }else{
                break;
            }
        }
        
        return cnt;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */