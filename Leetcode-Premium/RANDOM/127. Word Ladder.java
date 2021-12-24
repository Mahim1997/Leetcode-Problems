class Solution {
    private Set<String> allWordSet;
    
    public int ladderLength(String beginWord, String endWord, 
                            List<String> wordList) {
        
        // No need for Node.
        this.allWordSet = new HashSet<>(wordList);
        
        if(this.allWordSet.contains(endWord) == false)
            return 0;
        
        Queue<String> queue = new LinkedList<>();
        
        this.allWordSet.remove(beginWord);
        queue.add(beginWord);
        int level = 1;
        
        while(queue.isEmpty() == false){
            int qSize = queue.size(); // to pop those elements for current level
            
            // remove current level's elements in for loop, and add to queue.
            for(int qItr=0; qItr<qSize; qItr++){
                String currentWord = queue.remove();
                // remove from possibleWords/this.allWordsSet
                this.allWordSet.remove(currentWord);
                List<String> children = getChildren(currentWord);
                for(String child: children){
                    if(child.equals(endWord) == true)
                        return (level + 1);
                    
                    if(this.allWordSet.contains(child) == true){
                        queue.add(child); // add to queue
                    }
                }
            }
            
            level++;
        }
        
        return 0;
    }
    
    // GIVES Time Limit Error
//     private List<String> getChildren(String currentWord){
//         List<String> list = new ArrayList<>();
        
//         for(String word: this.allWordSet){
//             if(canChangeByOneCharacter(word, currentWord)){
//                 list.add(word);
//             }
//         }
        
//         return list;
//     }
       
    private List<String> getChildren(String word){
        List<String> list = new ArrayList<>();
        
        char[] chars = word.toCharArray();
        int charLen = chars.length;
        
        for(int i=0; i<charLen; i++){            // per position
            char prevChar = chars[i];
            for(char c='a'; c<='z'; c++){   // per character iteration
                if(c != prevChar){
                    chars[i] = c;
                    String newWord = new String(chars);
                    if(this.allWordSet.contains(newWord) == true){
                        list.add(newWord);
                    }
                    chars[i] = prevChar;    // rechange back to original char
                }
            }
        }
        
        return list;
    }
    
    // All strings are of same length
    private boolean canChangeByOneCharacter(String source, String dest){
        int numMismatches = 0;
        int strlen = source.length();
        for(int i=0; i<strlen; i++){
            if(source.charAt(i) != dest.charAt(i))
                numMismatches++;
            
            if(numMismatches >= 2)
                return false;
        }
        
        return (numMismatches == 1);
    }
    
}