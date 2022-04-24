class BigMap {
    Map<Pair<String, String>, Pair<Double, Integer>> bigMap;
    
    public BigMap(){
        this.bigMap = new HashMap<>();
    }
    
    private Pair<String, String> formKey(String in, String out){
        return new Pair<>(in, out);
    }
    
    private Pair<Double, Integer> getValue(String in, String out){
        Pair<String, String> key = new Pair<>(in, out);
        return bigMap.getOrDefault(key, new Pair<>(0.0, 0));
    }
    
    private Pair<Double, Integer> getValue(Pair<String, String> key){
        return bigMap.getOrDefault(key, new Pair<>(0.0, 0));
    }

    private Pair<Double, Integer> getNewAverage(int newTime,
                Pair<Double, Integer> previousAverage){
        
        double avgSoFar = previousAverage.getKey();
        int size = previousAverage.getValue();
        
        // calculate moving average
        avgSoFar = ((avgSoFar * size) + (newTime)) 
                        / (double)(size + 1);
        size++;
        
        return new Pair<>(avgSoFar, size);
    }
    
    private void addNewEntry(String checkin, String checkout, 
                             int newTime){
                
        // retrieve existing
        Pair<String, String> key = formKey(checkin, checkout);
        Pair<Double, Integer> pair = getValue(key);
        
        // calculate moving average
        Pair<Double, Integer> newPair = getNewAverage(newTime, pair);
        
        // update to map
        this.bigMap.put(key, newPair);
    }
    
    public double getAverage(String checkin, String checkout){
        Pair<Double, Integer> pair = getValue(checkin, checkout);
        return pair.getKey(); // first is moving average
    }
    
    // Extension
    public void add(IntraStationTime intraStationTime) {
        this.addNewEntry(intraStationTime.checkinStation,
                         intraStationTime.checkoutStation,
                         intraStationTime.timeElapsed);
    }
}

class IntraStationTime {
    public String checkinStation;
    public String checkoutStation;
    public int timeElapsed;
    
    public IntraStationTime(String in, String out, int del){
        this.checkinStation = in;
        this.checkoutStation = out;
        this.timeElapsed = del;
    }
}

class CheckinMap {
    private Map<Integer, Pair<String, Integer>> checkinMap;
    
    public CheckinMap(){
        this.checkinMap = new HashMap<>();
    }
    
    public void checkin(int customerID, 
                        String checkinStation, int time){
        
        this.checkinMap.put(customerID,
                           new Pair<>(checkinStation, time));
    }
    
    public IntraStationTime checkout(int customerID,
                          String checkoutStation, int time){
        
        Pair<String, Integer> previousPair = this.checkinMap.get(customerID);
        
        // remove from map
        this.checkinMap.remove(customerID);
        
        // calculate time
        int timeSpent = time - previousPair.getValue();
        
        // return IntraStationTime
        return new IntraStationTime(previousPair.getKey(), 
                                    checkoutStation, timeSpent);
        
    }
}


class UndergroundSystem {

    private BigMap bigMap;
    private CheckinMap checkinMap;
    
    public UndergroundSystem() {
        this.bigMap = new BigMap();
        this.checkinMap = new CheckinMap();        
    }
    
    public void checkIn(int id, String stationName, int t) {
        // add to small map
        this.checkinMap.checkin(id, stationName, t);
    }
    
    public void checkOut(int id, String stationName, int t) {
        // retrieve from small map
        IntraStationTime intraStationTime = this.checkinMap.checkout(id, stationName, t);
        
        // add to big map
        this.bigMap.add(intraStationTime);
    }
    
    public double getAverageTime(String startStation, 
                                 String endStation) {
        return this.bigMap.getAverage(startStation, endStation);
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */