class Leaderboard {
    
    static class Player{
        int id; int score;
        public Player(int id, int score){this.id = id; 
                                         this.score = score;}
        
        @Override
        public String toString(){
            return "Player(id=" + id + ",score=" + score + ")";
        }
    }
    
    private PriorityQueue<Player> pq;
    private Map<Integer, Player> map;
    
    Leaderboard() {
        // descending ==> x2 - x1
        this.pq = new PriorityQueue<>((Player x1, Player x2) -> 
                                      (x2.score - x1.score));
        this.map = new HashMap<>();
    }
    
    void printDetails(){
        // printing map
        System.out.println(this.map);
        
        // printing pq
        PriorityQueue<Player> copy = new PriorityQueue<>(this.pq);
        while(copy.isEmpty() == false){
            System.out.println(copy.remove());
        }
    }
    
    void addScore(int playerId, int score) {
        if(this.map.containsKey(playerId) == false){
            // create new player.
            Player p = new Player(playerId, score);
            this.map.put(playerId, p);
            this.pq.add(p);
        }else{
            Player player = this.map.get(playerId);
            this.pq.remove(player);
            
            player.score += score; // add to existing score.
            this.map.put(playerId, player);
            this.pq.add(player);
        }
        
        // printDetails(); // DEBUGGING
    }
    
    int top(int K) {
        // System.out.println("For K = " + K);
        // printDetails(); // DEBUGGING
        
        int score = 0;
        PriorityQueue<Player> copy = new PriorityQueue<>(this.pq);
        while((copy.isEmpty() == false) && (K > 0)){
            Player p = copy.remove();
            score += p.score;
            K--;
        }
        return score;
    }
    
    void reset(int playerId) {
        if(this.map.containsKey(playerId)){
            Player p = this.map.get(playerId);
            this.pq.remove(p);
            this.map.remove(playerId);            
        }
    }
    
};

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard* obj = new Leaderboard();
 * obj->addScore(playerId,score);
 * int param_2 = obj->top(K);
 * obj->reset(playerId);
 */
 
 /*
 class Leaderboard {
    
    private Map<Integer, Integer> map;
    
    Leaderboard() {
        this.map = new HashMap<>();
    }
    
    void printDetails(){
        // printing map
        System.out.println(this.map);
    }
    
    void addScore(int playerId, int score) {
        if(this.map.containsKey(playerId) == false){
            this.map.put(playerId, score);
        }else{
            int currentScore = this.map.get(playerId);
            currentScore += score;
            this.map.put(playerId, currentScore);
        }
    }
    
    int top(int K) {
        // System.out.println("For K = " + K);
        // printDetails(); // DEBUGGING
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((x1, x2) -> (x1 - x2));
        
        int cnt = 0;
        for(int key: this.map.keySet()){
            if(cnt < K){
                int score = this.map.get(key);
                minHeap.add(score);
            }else{
                int topScore = minHeap.peek();
                int score = this.map.get(key);
                if(score > topScore){ // update heap
                    minHeap.remove();
                    minHeap.add(score);
                }
            }
            cnt++;
        }
        // System.out.println("Printing minHeap => " + minHeap);
        
        int cumulativeScore = 0;
        while(minHeap.isEmpty() == false){
            cumulativeScore += minHeap.peek();
            minHeap.remove();
        }
            
        return cumulativeScore;
    }
    
    void reset(int playerId) {
        if(this.map.containsKey(playerId)){
            this.map.remove(playerId);
        }
    }
    
};

/**
 * Your Leaderboard object will be instantiated and called as such:
 * Leaderboard* obj = new Leaderboard();
 * obj->addScore(playerId,score);
 * int param_2 = obj->top(K);
 * obj->reset(playerId);
 */
 
 */