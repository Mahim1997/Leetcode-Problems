class Solution {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        Map<Character, Integer> map = getCharacterIntegerMap();
        int firstNumber = getConvertedNumber(firstWord, map);
        int secondNumber = getConvertedNumber(secondWord, map);
        int targetNumber = getConvertedNumber(targetWord, map);
        
        return ((firstNumber + secondNumber) == targetNumber);
    }
    
    private Map<Character, Integer> getCharacterIntegerMap(){
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<26; i++){
            char c = (char) ('a' + i);
            map.put(c, i);
        }
        return map;
    }
    
    private String getConvertedString(String word, Map<Character, Integer> map){
        StringBuilder bld = new StringBuilder();
        for(int i=0; i<word.length(); i++){
            char currentChar = word.charAt(i);
            bld.append(String.valueOf(map.get(currentChar)));
        }
        return bld.toString();
    }
    
    private int getConvertedNumber(String word, Map<Character, Integer> map){
        return Integer.parseInt(getConvertedString(word, map));
    }
}