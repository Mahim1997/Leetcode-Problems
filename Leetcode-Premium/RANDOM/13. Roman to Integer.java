class Solution {
    private Map<String, Integer> map = new HashMap<>();
    
    private void initialiseMap(){
        this.map.put("I",1);
        this.map.put("V",5);
        this.map.put("X",10);
        this.map.put("L",50);
        this.map.put("C",100);
        this.map.put("D",500);
        this.map.put("M",1000);
        
        this.map.put("IV", 4);
        this.map.put("IX", 9);
        this.map.put("XL", 40);
        this.map.put("XC", 90);
        this.map.put("CD", 400);
        this.map.put("CM", 900);
    }
    
    public int romanToInt(String s) {
        this.initialiseMap();
        
        int num = 0, strlen = s.length();
        if(strlen == 1){
            return this.map.get(s.substring(0, 1));
        }
        
        for(int i=0; i<strlen; i++){
            // substr is EXCLUSIVE
            if(i != strlen - 1){
                String str = s.substring(i, i+2); // [i, i+1] INCLUSIVE
                if(this.map.containsKey(str) == true){
                    num += this.map.get(str);
                    i++;
                }else{
                    num += this.map.get(s.substring(i, i+1));
                }                
            }else{
                num += this.map.get(s.substring(i, i+1));
            }
            
            // System.out.println("i = " + i + ", num = " + num);
        }
        
        
        return num;
    }
}