class Solution {
    // API call
    public int ladderLength(
        String beginWord, 
        String endWord, 
        List<String> wordList
    ) {
        // Use hashset for amortized O(1) checking
        Set<String> allWordSet = new HashSet<>(wordList);
        
        // Base case check, end word unavailable in wordList/wordSet
        if(allWordSet.contains(endWord) == false)
            return 0;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        int distance = 0;
        
        queue.add(beginWord);
        visited.add(beginWord);
        
        while(!queue.isEmpty()) {
            distance++;
            int qSize = queue.size();
            
            // Level order traversal BFS
            for(int i=0; i<qSize; i++){
                String word = queue.remove();
                if(word.equals(endWord))
                    return distance;
                
                for(String child: getChildren(word, allWordSet)) {
                    if(visited.contains(child))
                        continue;

                    queue.add(child);
                    visited.add(child);
                }
            }
        }
        
        return 0;
    }
    
    // Differ by a single character.
    List<String> getChildren(String word, Set<String> allWordSet){
        char[] charArr = word.toCharArray();
        List<String> list = new ArrayList<>();
        
        int strlen = word.length();
        for(int i=0; i<strlen; i++){
            char charCurrent = word.charAt(i);
            
            // Temporarily change each character [1 distance]
            for(char charItr ='a'; charItr <= 'z'; charItr++) {
                if(charItr != charCurrent){
                    charArr[i] = charItr;
                    String strNew = String.valueOf(charArr);
                    
                    if(allWordSet.contains(strNew))
                        list.add(strNew);
                }
            }
            
            // replace with the prev word
            charArr[i] = charCurrent;
        }
        
        return list;
    }
    
}