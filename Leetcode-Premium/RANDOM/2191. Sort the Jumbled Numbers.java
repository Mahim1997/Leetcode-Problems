class MyClass{
    public int originalNumber;
    public int mappedNumber;
    public int originalIndex;
    
    public MyClass(int on, int m, int oi){
        this.originalNumber = on;
        this.mappedNumber = m;
        this.originalIndex = oi;
    }
    
    @Override
    public String toString(){
        return "oN=" + originalNumber + ", mN=" 
            + mappedNumber + ", oI=" + originalIndex;
    }
}

class MyComparator implements Comparator<MyClass>{
    
    @Override
    public int compare(MyClass obj1, MyClass obj2){
        if(obj1.mappedNumber > obj2.mappedNumber){
            return 1; // need to check reverse ordering ?
        }
        else if(obj1.mappedNumber < obj2.mappedNumber){
            return -1; 
        }
        
        // need to check ordering +1/-1 ?
        return (obj1.originalIndex > obj2.originalIndex) ? 1 : -1;
    }
}

class Solution {

    private int convertNumber(Map<String, String> mappingStr, 
                              int originalNum){
        
        String str = String.valueOf(originalNum);
        StringBuilder bld = new StringBuilder();
        int len = str.length();

        for(int i=0; i<len; i++){
            String key = String.valueOf(str.charAt(i));
            bld.append(mappingStr.get(key));            
        }
        
        return Integer.parseInt(bld.toString());
    }
    
    private Map<String, String> getMapping(int[] mapping){
        Map<String, String> map = new HashMap<>();
        
        for(int i=0; i<mapping.length; i++)
            map.put(String.valueOf(i), String.valueOf(mapping[i]));
        
        return map;
    }
    
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        MyClass[] objects = new MyClass[n];
        
        // MAP 
        Map<String, String> mappingStr = getMapping(mapping);
        
        for(int i=0; i<n; i++){
            int num = nums[i];
            int mappedNum = convertNumber(mappingStr, num);
            objects[i] = new MyClass(num, mappedNum, i);
        }
        
        Arrays.sort(objects, new MyComparator());
        
        // DEBUGG
        // for(MyClass obj: objects){
        //     System.out.println(obj);
        // }
        
        int[] newNums = new int[n];
        for(int i=0; i<n; i++){
            newNums[i] = objects[i].originalNumber;
        }
        
        return newNums;
    }
}