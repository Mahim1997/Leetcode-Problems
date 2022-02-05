class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        List< List<Integer> > listOfMovements = getAllMovements(targetSeconds);
        
        int minCost = Integer.MAX_VALUE;
        for(List<Integer> movement: listOfMovements){
            minCost = Math.min(
                minCost,
                getCostOfMovement(movement, startAt, moveCost, pushCost)
            );
        }
        
        return minCost;
    }
    
    private List<Integer> getListFromDigits(int number){
        List<Integer> list = new ArrayList<>();
        int num = number;
        while(num > 0){
            int digit = num%10;
            list.add(digit);
            num/=10;
        }
        Collections.reverse(list);
        return list;
    }
    
    private List<List<Integer>> getAllMovements(int target){
        List< List<Integer> > listOfMovements = new ArrayList<>();
        
        // always this is needed.
        listOfMovements.add(getListFromDigits(target));
        if(target < 60){
            return listOfMovements;
        }   
        else{
            // need to convert to minutes.
            int min = target/60;
            int seconds = target%60;
            
            // normal min:seconds is added
            List<Integer> normalList = new ArrayList<>();
            normalList.addAll(getListFromDigits(min));
            normalList.addAll(getListFromDigits(seconds));
            
            // min:[0-99s] is added
            int newSeconds = seconds + 60;
            if(newSeconds <= 99){
                List<Integer> otherList = new ArrayList<>();
                int newMin = min - 1;
                if(newMin != 0)
                    otherList.add(getListFromDigits(newMin));
				
				// only add seconds
                otherList.add(getListFromDigits(newSeconds));
			}
			listOfMovements.add(otherList);
		}
			
			return listOfMovements;
	}

    
    private int getCostOfMovement(List<Integer> movement, 
                                  int startAt, 
                                  int moveCost, 
                                  int pushCost){
        int cost = 0;
        for(int i=0; i<movement.size(); i++){
            int move = movement.get(i);
            if(i == 0){
                if(move != startAt){
                    cost += moveCost;
                }
            }else{
                if(move != movement.get(i - 1)){
                    cost += moveCost;
                }
            }
            
            cost += pushCost;
        }
        
        return cost;
    }
}




