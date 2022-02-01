class Solution {
    
    private Map<Character, List<String>> map;
    private Map<Character, List<String>> getMapDigits(){
        Map<Character, List<String>> map = new HashMap<>();
        
        map.put('2', new ArrayList<>(List.of("a","b","c")));
        map.put('3', new ArrayList<>(List.of("d","e","f")));
        map.put('4', new ArrayList<>(List.of("g","h","i")));
        map.put('5', new ArrayList<>(List.of("j","k","l")));
        map.put('6', new ArrayList<>(List.of("m","n","o")));
        map.put('7', new ArrayList<>(List.of("p","q","r", "s")));
        map.put('8', new ArrayList<>(List.of("t","u","v")));
        map.put('9', new ArrayList<>(List.of("w","x","y", "z")));
        
        return map;
    }
    
    private List<String> getNewCombination(List<String> combinationExisting, 
                                           char c){
        // recursive ... prefix style
        List<String> combinationOfCurrentChar = this.map.get(c);
        
        // Cartesian Product of List1 X List2
        List<String> list = new ArrayList<>();
        
        if(combinationExisting.isEmpty()){
            return combinationOfCurrentChar;
        }
        
        for(String currentCharWords: combinationOfCurrentChar){
            for(String existingCharWords: combinationExisting){
                String newCombination = existingCharWords+currentCharWords;
                list.add(newCombination);
            }
        }
        return list;
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        
        this.map = getMapDigits();
        
        // f("23") = findList("2") + f("3") = 
        // f("3") = findList("3") + f("") = ['d','e','f'] + []
        // f("") = []

        int strlen = digits.length();
        for(int i=0; i<strlen; i++){
            list = getNewCombination(list, digits.charAt(i));
        }
        
        return list;
    }
}