class WordDictionary {
    static class TrieNode{
        char character;
        boolean canEnd;
        Map<Character, TrieNode> childrenMap;
        
        public TrieNode(char c){
            this.character = c;
            this.canEnd = false;
            this.childrenMap = new HashMap<>();
        }
        
        @Override
        public String toString(){
            return "TrieNode(char=" + this.character + ")";
        }
    }
    // Trie-data structure needed ?
    private TrieNode head;
    
    public WordDictionary() {
        this.head = new TrieNode('$'); // dummy head node
    }
    
    public void addWord(String word) {
        TrieNode iter = this.head;
        char[] chars = word.toCharArray();
        for(int i=0; i<chars.length; i++){
            char c = chars[i];
            if(iter.childrenMap.containsKey(c) == false){
                TrieNode newNode = new TrieNode(c);
                iter.childrenMap.put(c, newNode);
                iter = newNode;
            }else{
                iter = iter.childrenMap.get(c);
            }
            if(i == (chars.length - 1))
                iter.canEnd = true;
        }
    }
    
    /** Returns if the word is in the node. */
    public boolean searchInNode(String word, TrieNode node) {
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (!node.childrenMap.containsKey(ch)) {
                // if the current character is '.'
                // check all possible nodes at this level
                if (ch == '.') {
                    for (char x : node.childrenMap.keySet()) {
                        TrieNode child = node.childrenMap.get(x);
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true;
                        }
                    }
                }
                // if no nodes lead to answer
                // or the current character != '.'
                return false;
            } else {
                // if the character is found
                // go down to the next level in trie
                node = node.childrenMap.get(ch);
            }
        }
        return node.canEnd;
    }
    
    private boolean searchHelper(String word, TrieNode node){
        // System.out.println("Searching for word = " + word + ", node = " + node);
        
        
        int wordLen = word.length();
        for(int i=0; i<wordLen; i++){
            char c = word.charAt(i);
            if(node.childrenMap.containsKey(c) == true){
                node = node.childrenMap.get(c);
            }else{
                // if(c != '.'){return false;} // doesn't work for some reason
                // c == '.' condition has reached.
 
                if(c == '.'){                    
                    for(TrieNode childNode: node.childrenMap.values()){
                        boolean searchChild = searchHelper(
                            word.substring(i+1), 
                            childNode
                        );
                        if(searchChild == true){
                            return true;
                        }
                    }
                }
                
                return false;
            }
        }
        // node has reached the end
        return node.canEnd;
    }
    
    public boolean search(String word) {
        TrieNode iter = this.head;
        // System.out.println("Printin iter's map: " + iter.childrenMap);
        return searchHelper(word, iter);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */