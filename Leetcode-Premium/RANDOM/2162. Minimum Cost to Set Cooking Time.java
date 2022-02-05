class Solution {
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        List< List<Integer> > listOfMovements = getAllMovements(targetSeconds);
        
        int minCost = Integer.MAX_VALUE;
        
        // System.out.println("Printing listOfMovements = " + listOfMovements);
        
        for(List<Integer> movement: listOfMovements){
            int cost = getCostOfMovement(movement, startAt, moveCost, pushCost);
            // System.out.println("Cost for " + movement + ", = " + cost);
            if(cost != 0){
                minCost = Math.min(
                    minCost,
                    cost
                );
            }
        }
        
        return minCost;
    }

    private List<Integer> getListFromSeconds(int number){
        List<Integer> list = new ArrayList<>();
        if(number == 0){
            list.add(0);
            list.add(0);
            return list;
        }
        
        int num = number;
        while(num > 0){
            int digit = num%10;
            list.add(digit);
            num/=10;
        }
        if(number < 10){
            list.add(0);
        }
        Collections.reverse(list);
        return list;
    }

    
    private List<Integer> getListFromDigits(int number){
        List<Integer> list = new ArrayList<>();
        if(number == 0){
            return list;
        }
        
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
        if(target < 60){
            listOfMovements.add(getListFromDigits(target));
            return listOfMovements;
        }   
        else{
            // need to convert to minutes.
            int min = target/60;
            int seconds = target%60;
            
            // normal min:seconds is added
            
            // cannot have 100 mins
            if(min <= 99){
                List<Integer> normalList = new ArrayList<>();
                normalList.addAll(getListFromDigits(min));

                normalList.addAll(getListFromSeconds(seconds));            

                listOfMovements.add(normalList);                
            }
            
            
            // min:[0-99s] is added
            int newSeconds = seconds + 60;
            if(newSeconds <= 99){
                List<Integer> otherList = new ArrayList<>();
                int newMin = min - 1;
                // System.out.println("newMin = " + newMin + ", min = " + min);
                
                if(newMin != 0)
                    otherList.addAll(getListFromDigits(newMin));
				
				// only add seconds
                otherList.addAll(getListFromDigits(newSeconds));
			    
                // add the other list
                listOfMovements.add(otherList);
			}
		}
			
			return listOfMovements;
	}

    
    private int getCostOfMovement(List<Integer> movement, 
                                  int startAt, 
                                  int moveCost, 
                                  int pushCost){
        int cost = 0;
        
        int idx = 0;
        for(int i=0; i<movement.size(); i++){
            if(movement.get(i) != 0){
                idx = i;
                break;
            }
        }
        
        for(int i=idx; i<movement.size(); i++){
            int move = movement.get(i);
            if(i == idx){
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




