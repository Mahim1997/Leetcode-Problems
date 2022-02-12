

class Solution {
    private Set<String> allWordSet;
    
    public int ladderLength(String beginWord, String endWord, 
                            List<String> wordList) {
        this.allWordSet = new HashSet<>(wordList);
        
        // Base case check, end word unavailable in wordList/wordSet
        if(allWordSet.contains(endWord) == false)
            return 0;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        int distance = 0;
        queue.add(beginWord);
        // visited.add(beginWord);
        
        while(!queue.isEmpty()){
            distance++;
            int qSize = queue.size();
            for(int i=0; i<qSize; i++){
                String word = queue.remove();
                if(word.equals(endWord))
                    return distance;
                
                for(String child: getChildren(word)){
                    if(visited.contains(child) == false){
                        queue.add(child);
                        visited.add(child);
                    }
                }
            }            
        }
        
        return 0;
    }
    
    // Differ by a single character.
    List<String> getChildren(String word){
        char[] charArr = word.toCharArray();
        List<String> list = new ArrayList<>();
        
        int strlen = word.length();
        for(int i=0; i<strlen; i++){
            char c = word.charAt(i);
            for(char a='a'; a<='z'; a++){
                if(c != a){
                    charArr[i] = a;
                    String str = String.valueOf(charArr);
                    if(this.allWordSet.contains(str)){
                        list.add(str);
                    }
                }
            }
            charArr[i] = c;
        }
        
        return list;
    }
    
}