class Log {
    private String message;
    
    public Log(String msg) {
        this.message = msg;
    }
    
    public String getMessage() {
        return this.message;
    }

    public boolean contains(String searchString) {
        return this.message.contains(searchString);
    }

    @Override
    public String toString() {
        return "Log[" + this.message + "]";
    }
}

class Holder {
    // Common Platform to have both machine, and service
    // For faster implementation
    
    private List<Integer> logIDs;
    
    public Holder() {
        this.logIDs = new ArrayList<>();
    }
    
    public List<Integer> getLogIDs() {
        return this.logIDs;
    }
    
    public void addLogID(int logID) {
        this.logIDs.add(logID);
    }
    
    @Override
    public String toString() {
        return "Holder[" + this.logIDs.toString() + "]";
    }
}

class LogAggregator {
    // private static int NUM_MACHINES = 20 + 1;
    // private static int NUM_SERVICES = 20 + 1;
    
    private Map<Integer, Log> mapLog;
    
    private Holder[] machines;
    private Holder[] services;
    
    private int numMachines;
    private int numServices;
    
    public LogAggregator(int numMachines, int numServices) {
        this.mapLog = new HashMap<>();
        
        this.numMachines = numMachines;
        this.numServices = numServices;
        
        this.machines = new Holder[numMachines];
        this.services = new Holder[numServices];
        
        // initialize
        for(int i=0; i<numMachines; i++) {
            this.machines[i] = new Holder();
        }
        for(int i=0; i<numServices; i++) {
            this.services[i] = new Holder();
        }
    }
    
    public void pushLog(int logId, int machineId, int serviceId, String message) {
        // Add to log map
        Log log = new Log(message);
        this.mapLog.put(logId, log);
        
        // Add to machines
        this.machines[machineId].addLogID(logId);
        
        // Add to services
        this.services[serviceId].addLogID(logId);
    }
    
    public List<Integer> getLogsFromMachine(int machineId) {
        return this.machines[machineId].getLogIDs();
    }
    
    public List<Integer> getLogsOfService(int serviceId) {
        return this.services[serviceId].getLogIDs();
    }
    
    public List<String> search(int serviceId, String searchString) {
        return this.services[serviceId]
            .getLogIDs()
            .stream()
            .map(logId -> this.mapLog.get(logId))
            .filter(logMessage -> logMessage.contains(searchString))
            .map(log -> log.getMessage())
            .collect(Collectors.toList());
    }
}

/**
 * Your LogAggregator object will be instantiated and called as such:
 * LogAggregator obj = new LogAggregator(machines, services);
 * obj.pushLog(logId,machineId,serviceId,message);
 * List<Integer> param_2 = obj.getLogsFromMachine(machineId);
 * List<Integer> param_3 = obj.getLogsOfService(serviceId);
 * List<String> param_4 = obj.search(serviceId,searchString);
 */