class Solution {
    private int[] heights;
    
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1)
            return heights[0];
        
        this.heights = heights;
        
        int[] left = getLeftArray();
        int[] right = getRightArray();
        
        // printArray(left);
        // printArray(right);
        
        int maxArea = 0;
        for(int i=0; i<heights.length; i++){
            int width = right[i] - left[i] + 1;
            int height = this.heights[i];
            maxArea = Math.max(maxArea, (width*height));
        }
        
        return maxArea;
    }
    
    private void printArray(int[] arr){
        for(int x: arr){
            System.out.print(x + " ");
        }
        System.out.println("");
    }
    
    private int[] getRightArray(){
        int n = this.heights.length;
        int[] right = new int[n];
        Arrays.fill(right, 0);
        
        Stack<Integer> stack = new Stack<>();
    
        for(int i=n-1; i>=0; i--){
            int currentHeight = this.heights[i];
            if(stack.isEmpty()){
                stack.push(i);
                right[i] = n-1;
                // System.out.println("For i = " + i + ", stack is empty");
            }
            else{
                int stackTopHeight = this.heights[stack.peek()];
                
                if(currentHeight <= stackTopHeight){
                    while(!stack.isEmpty()){
                        stackTopHeight = this.heights[stack.peek()];
                        if(stackTopHeight < currentHeight){break;}
                        stack.pop();
                    }
                    if(stack.isEmpty()){
                        right[i] = n-1;
                    }else{
                        right[i] = stack.peek() - 1;
                    }
                    stack.push(i);
                }
                else{
                    // monotonically increasing
                    right[i] = i;
                    stack.push(i);
                }
            }
        }
        return right;
    }
    
    private int[] getLeftArray(){
        int n = this.heights.length;
        int[] left = new int[n];
        Arrays.fill(left, 0);
        // maintain a sorted array ascending order of indices in stack
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            int currentHeight = this.heights[i];
            if(stack.isEmpty()){
                stack.push(i);
                left[i] = 0;    // blank stack, so it will be the leftmost
            }
            else{
                int stackTopHeight = this.heights[stack.peek()];
                
                // to handle equal cases [1,1,1,1]
                // need to check firstly for equal to as well
                if(currentHeight <= stackTopHeight){
                    // keep on popping the stack, until a smaller height is reached.
                    while(stack.isEmpty() == false){
                        stackTopHeight = this.heights[stack.peek()];
                        
                // inside, need to break with STRICTLY less than condition
                        if(stackTopHeight < currentHeight){break;}
                        stack.pop();
                    }
                    if(stack.isEmpty()){
                        left[i] = 0;
                    }else{
                        // include from THIS exactly
                        left[i] = stack.peek() + 1;
                    }
                    stack.push(i);
                }
                else{
                    left[i] = i;
                    // simply push to stack, as this is the bigger number
                    stack.push(i); // only THIS tile can be used
                }                
            }
        }
        return left;
    }
}