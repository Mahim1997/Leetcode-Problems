class Trie {

    static class TrieNode{
        char character;
        
        int countWords;
        int countPrefixes;
        // eg. [s], [saa], [see], [saw], [s] -> single variable, all will have (s, 4)
        // need two counters, (s, prefix=3, word=2)
        
        Map<Character, TrieNode> childrenMap;
        
        public TrieNode(char c){
            this.character = c;
            this.childrenMap = new HashMap<>();
                    
            this.countWords = 0;
            this.countPrefixes = 0;
        }
    }
    
    private TrieNode root;
    
    public Trie() {
        this.root = new TrieNode('$');
    }
    
    public void insert(String word) {
        TrieNode node = this.root;
        char[] chars = word.toCharArray();
        for(char c: chars){
            if(node.childrenMap.containsKey(c) == false){
                // does not exist in the children map
                node.childrenMap.put(c, new TrieNode(c));
            }
            // move to the child in both cases
            node = node.childrenMap.get(c);
            // increment the number of words under this node
            node.countPrefixes++;
        }
        
        // finalize
        // node.isEndingWord = true; // not needed, as default will be zero otherwise
        node.countWords++;
    }
    
    // Traverse the trie wrt the word, and return that node
    // return null, if doesn't exist.
    private TrieNode getNodeOfString(String word){
        TrieNode node = this.root;
        char[] chars = word.toCharArray();
        for(char c: chars){
            if(node.childrenMap.containsKey(c) == false){
                return null;
            }
            node = node.childrenMap.get(c);
        }
        return node;
    }
    
    public int countWordsEqualTo(String word) {
        // no need to check if isEndingWord, as default is 0
        TrieNode nodeObtained = getNodeOfString(word);
        return (nodeObtained == null) ? 0 : nodeObtained.countWords;
    }
    
    public int countWordsStartingWith(String prefix) {
        TrieNode nodeObtained = getNodeOfString(prefix);
        return (nodeObtained == null) ? 0 : nodeObtained.countPrefixes;
    }
    
    public void erase(String word) {
        // // Question stated always assume word exists

        TrieNode node = this.root;
        char[] chars = word.toCharArray();
        for(char c: chars){
            if(node.childrenMap.containsKey(c) == false){
                return;
            }
            // first move to that child node, then increment/decrement prefix/word counts
            node = node.childrenMap.get(c);
            node.countPrefixes--;
        }
        node.countWords--;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */