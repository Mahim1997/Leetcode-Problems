class Trie {
    static class TrieNode{
        char character;
        boolean canEnd;
        Map<Character, TrieNode> childrenMap;
        
        public TrieNode(char c){
            this.character = c;
            this.canEnd = false; // initially false.
            this.childrenMap = new HashMap<>();
        }
    }
    
    TrieNode head;
    
    public Trie() {
        this.head = new TrieNode('$'); // dummy node
    }
    
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode iter = this.head;
        if(iter.childrenMap.containsKey(chars[0]) == false){
            // word can't be of zero length
            // node does not have the first character of the word.
            // simply keep on adding the words
            for(int i=0; i<chars.length; i++){
                char c = chars[i];
                TrieNode newNode = new TrieNode(c);
                iter.childrenMap.put(c, newNode);
                iter = newNode;
                if(i == (chars.length-1)){
                    iter.canEnd = true;
                }
            }
        }else{
            // node HAS the first character of the word.
            for(int i=0; i<chars.length; i++){
                char c = chars[i];
                if(iter.childrenMap.containsKey(c) == true){
                    iter = iter.childrenMap.get(c); // just move on to that
                }else{
                    // create new node
                    TrieNode newNode = new TrieNode(c);
                    iter.childrenMap.put(c, newNode);
                    iter = newNode;
                }
                
                if(i == (chars.length-1)){
                    iter.canEnd = true;
                }
            }
        }
    }
    
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TrieNode iter = this.head;
        for(int i=0; i<chars.length; i++){
            char c = chars[i];
            if(iter.childrenMap.containsKey(c) == false)
                return false;
            iter = iter.childrenMap.get(c);
            if((i == (chars.length-1))
              && (c == iter.character)
              && (iter.canEnd == true)){
                return true;
            }
        }
        return false;
    }
    
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        TrieNode iter = this.head;
        for(int i=0; i<chars.length; i++){
            char c = chars[i];
            if(iter.childrenMap.containsKey(c) == false)
                return false;
            
            iter = iter.childrenMap.get(c);
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */