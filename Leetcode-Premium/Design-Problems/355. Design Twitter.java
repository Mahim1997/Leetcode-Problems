class Pair {
    public int tweetId;
    public long timestamp;
    
    public Pair(int tId, long time){
        this.tweetId = tId;
        this.timestamp = time;
    }
    
    public String toString(){
        return ("[" + tweetId + "," + timestamp + "]");
    }
}

class CircularArray {
    static int N = 10;
    public Pair[] arr;
    public int index;
    public int length;
    
    public int indexIterator;
    public int lengthIterator;
    
    public CircularArray(){
        this.arr = new Pair[N];
        Arrays.fill(this.arr, new Pair(-1, -1)); // initially fill as -1
        this.index = -1;
        this.length = 0;
    }
    
    public int nextIndex(int i){
        return (i + 1) % N;
    }
    public int previousIndex(int i){
        if(i == 0) {return (N - 1);}
        return (i - 1);
    }
    
    public void addElement(int tweetId, long timestamp){
        this.index = nextIndex(this.index);
        this.arr[this.index] = new Pair(tweetId, timestamp);
        this.length = Math.min(this.length + 1, N);
        
        this.indexIterator = this.index;
        this.lengthIterator = this.length;
    }
    
    public void printCircularArray(){
        int len = this.length;
        int idx = this.index;
        
        System.out.println("len = " + this.length + ", idx = " + this.index);
        for(int i=0; i<N; i++){
            System.out.print(this.arr[i] + " ");
        }
        
        // while(len > 0){
        //     System.out.print(this.arr[idx] + " ");
        //     idx = nextIndex(idx);
        //     len--;
        // }
        System.out.println("");
    }
}

class Twitter {

    private Map<Integer, CircularArray> tweetsMap;
    private Map<Integer, Set<Integer>> followersMap;
    private static long TIMESTAMP;
    
    public Twitter() {
        this.tweetsMap = new HashMap<>();
        this.followersMap = new HashMap<>();
        TIMESTAMP = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        // Retrieve the circular array
        CircularArray circularArray = this.tweetsMap.getOrDefault(userId,
                                           new CircularArray());
        
        // Add the new tweet with added timeStamp
        circularArray.addElement(tweetId, TIMESTAMP++);
        
        // Update into map
        this.tweetsMap.put(userId, circularArray);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        // Get the followers of THIS USER ID + SELF
        Set<Integer> followers = this.followersMap.getOrDefault(userId,
                                                new HashSet<>());
        followers.add(userId); // add self
        
        // System.out.println("For userId = " + userId + ", followers = " + followers);
        // Get the highest timestamp element for each person
        // Iterate accordingly
        
        List<Integer> answer = new ArrayList<>();
        int numElementsTaken = 0;
        
        // first set iterators accordingly
        for(int person: followers){
            CircularArray circularArray = this.tweetsMap.getOrDefault(person,
                                                                        null);
            if(circularArray == null){ continue; }
        
            circularArray.indexIterator = circularArray.index;
            circularArray.lengthIterator = circularArray.length;
            
            // put back into map
            this.tweetsMap.put(person, circularArray);
            
            // DEBUGGING
            // System.out.println("\nperson: " + person + ", circularArray => " );
            // circularArray.printCircularArray();
            
        }
        
        int numIterations = 0;
        while(true){
            numIterations++;
            
            int personMax = -1;
            long maxTimeStamp = -1;
            
            boolean hasChanged = false;
            
            for(int person: followers){
                CircularArray circularArray = this.tweetsMap.getOrDefault(person,    
                                                                             null);
                if(circularArray == null){ continue; }
                if(circularArray.lengthIterator <= 0){ continue; }

                // circularArray.indexIterator = circularArray.index;
                int idx = circularArray.indexIterator;
                
                if(circularArray.arr[idx].timestamp > maxTimeStamp){
                    maxTimeStamp = circularArray.arr[idx].timestamp;
                    personMax = person;
                    hasChanged = true;
                }
                
                // System.out.println("numIterations = " + numIterations
                // + ", person = " + person +  " -> circularArray printing .... ");
                // circularArray.printCircularArray();
                
                
            }
            
            // System.out.println(">> personMax = " + personMax + ", hasChanged = "
            //                   + hasChanged);
            
            if(!hasChanged){
                break;
            }
            
            // append to answer
            CircularArray cArr = this.tweetsMap.get(personMax);
            
            int tweetIdAnswer = cArr.arr[cArr.indexIterator].tweetId;
            // System.out.println("Appending to answer: personMax => " + personMax
            //               + ", tweetId = " + tweetIdAnswer);
            answer.add(tweetIdAnswer);
            
            cArr.indexIterator = cArr.previousIndex(cArr.indexIterator);
            cArr.lengthIterator--;
            
            // put back to map
            this.tweetsMap.put(personMax, cArr);
            
            if(answer.size() == 10){
                break;
            }
        }
        
        // System.out.println("Returning answer = " + answer);
        return answer;
    }
    
    public void follow(int followerId, int followeeId) {
        // Retrieve the followerSet
        Set<Integer> followers = this.followersMap.getOrDefault(followerId, 
                                                            new HashSet<>());
        // Follow new user
        followers.add(followeeId);
        
        // Add back to map
        this.followersMap.put(followerId, followers);
    }
    
    public void unfollow(int followerId, int followeeId) {
        // Retrieve the followerSet
        Set<Integer> followers = this.followersMap.getOrDefault(followerId,
                                                           new HashSet<>());
        // Remove followed user
        followers.remove(followeeId);
        
        // Add back to map
        this.followersMap.put(followerId, followers);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */