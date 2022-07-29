class FoodInfo {
    private String name;
    private int rating;
    private String cuisine;
    
    public FoodInfo(String name, int rating, String cuisine) {
        this.name = name;
        this.rating = rating;
        this.cuisine = cuisine;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getRating() {
        return this.rating;
    }
    
    public String getCuisine() {
        return this.cuisine;
    }
    
    @Override
    public String toString() {
        return "[" + this.name + 
                "," + this.rating + 
                "," + this.cuisine + "]";
    }
}

class FoodComparator implements Comparator<FoodInfo> {
    @Override
    public int compare(FoodInfo o1, FoodInfo o2) {
        if(o1.getRating() != o2.getRating()) // max wrt rating
            return (o2.getRating() - o1.getRating());
        
        // lexicographic sorting
        return (o1.getName().compareTo(o2.getName())); 
    }
}

class Database {
    private Map<String, TreeSet<FoodInfo>> mapCuisineFoodInfo;
    private Map<String, Integer> mapFoodInfoIndex;
    
    private FoodComparator comparator;
    private int N;
    private FoodInfo[] foodInfos;
    
    public Database(
        String[] foods, 
        String[] cuisines, 
        int[] ratings
    ) {
        this.comparator = new FoodComparator();
        this.N = foods.length;
        this.foodInfos = new FoodInfo[N];
        
        // initialize maps
        this.mapCuisineFoodInfo = new HashMap<>();
        this.mapFoodInfoIndex = new HashMap<>();
        
        // build
        for(int i=0; i<N; i++) {
            String foodName = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];
            
            // build array
            FoodInfo foodInfo = new FoodInfo(
                foodName, rating, cuisine
            );
            this.foodInfos[i] = foodInfo;
            
            // build index mapping
            this.mapFoodInfoIndex.put(foodName, i);
    
            // build tree set mapping
            TreeSet<FoodInfo> treeSet = this.mapCuisineFoodInfo.getOrDefault(
                cuisine, 
                new TreeSet<>(this.comparator)
            );
            treeSet.add(foodInfo);
            
            // update into map
            this.mapCuisineFoodInfo.put(cuisine, treeSet);
        }
    }
    
    public void changeRating(String food, int newRating) {
        if(this.mapFoodInfoIndex.containsKey(food) == false)
            return;     // safe side: extra checking
        
        
        int index = this.mapFoodInfoIndex.get(food);
        FoodInfo foodInfo = this.foodInfos[index];
        String cuisine = foodInfo.getCuisine();
        
        // Get required tree set
        TreeSet<FoodInfo> treeSet = this.mapCuisineFoodInfo.get(cuisine);
        
        // System.out.println(">>>> changeRating => food = " + food + ", newRating: " + newRating + ": " + this.mapCuisineFoodInfo + ", foodInfo to remove: " + foodInfo);
        
        // Remove from tree set
        treeSet.remove(foodInfo);
        
        // Add to tree set
        FoodInfo foodInfoNew = new FoodInfo(food, newRating, cuisine);
        treeSet.add(foodInfoNew);
        
        // Update into map
        this.mapCuisineFoodInfo.put(cuisine, treeSet);
    
        // Update into index table
        this.foodInfos[index] = foodInfoNew;
    }
    
    public String getHighestRatedFood(String cuisine) {
        if(this.mapCuisineFoodInfo.containsKey(cuisine) == false)
            return "";  // safe side: checking ... throw exception later
        
        TreeSet<FoodInfo> treeSet = this.mapCuisineFoodInfo.get(cuisine);
        String ans = treeSet.first().getName();
        // System.out.println("++++> getHighestRatedFood() for cuisine: " + cuisine + ", returning " + ans);
        return ans;
    }
}

class FoodRatings {
    private Database db;
    
    // API
    public FoodRatings(
        String[] foods, 
        String[] cuisines, 
        int[] ratings
    ) {
        this.db = new Database(foods, cuisines, ratings);    
    }
    
    // API
    public void changeRating(String food, int newRating) {
        this.db.changeRating(food, newRating);
    }
    
    // API
    public String highestRated(String cuisine) {
        return this.db.getHighestRatedFood(cuisine);
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */