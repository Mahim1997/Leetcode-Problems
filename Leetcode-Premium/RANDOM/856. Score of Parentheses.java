class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> levels = new Stack<>();
        Stack<Pair<Integer, Integer>> pointsLevels = new Stack<>();
        
        int level = 0;
        int strlen = s.length();
        
        int initialPoint = 1;
        
        for(int i=0; i<strlen; i++){
            // System.out.println("At i = " + i + ", levels = " + levels
            //                   + ", pointsLevels = " + pointsLevels);
            char c = s.charAt(i);
            if(c == '('){
                // push into levels stack
                level++;
                levels.push(level);
            }
            else if(c == ')'){
                // level stays as is
                int poppedLevel = levels.pop();
                
                // now check the other stack
                if(pointsLevels.isEmpty()){
                    pointsLevels.push(new Pair<>(initialPoint,
                                                 level));
                }
                else{

                    Pair<Integer, Integer> top = pointsLevels.peek();
                    int topPoint = top.getKey(), topLevel = top.getValue();

                    if(level > topLevel){
                        // simply push [1, currentLevel]
                        pointsLevels.push(new Pair<>(initialPoint, level));
                    }
                    else if(level == topLevel){
                        // adjust with the top of stack
                        int newPoint = topPoint + initialPoint;
                        pointsLevels.pop();
                        pointsLevels.push(new Pair<>(newPoint, level));
                    }
                    else{
                        // Case: level < topLevel [X 2]
                        // create new pair with adjusting via topOfStack
                        pointsLevels.pop();
                        Pair<Integer, Integer> newPair = new Pair<>(2*topPoint,
                                                                    level);
                        
                        // Only case is when after merging, top of stack has level == new pair's level, then simply add and push
                        if(pointsLevels.isEmpty()){
                            pointsLevels.push(newPair);
                        }
                        else{
                            if(pointsLevels.peek().getValue() == level){
                                top = pointsLevels.pop();
                                int addedPoint = top.getKey() + newPair.getKey();
                                pointsLevels.push(new Pair<>(addedPoint, level));
                            }else{
                                // level > topLevel, so just push
                                pointsLevels.push(newPair);
                            }
                        }
                        
                    }
                    
                }
                
                level--;
            }
        }
        
        return pointsLevels.peek().getKey();
    }
}