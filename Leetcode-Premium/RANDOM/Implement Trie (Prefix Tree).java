class TrieNode {
    protected Map<Character, TrieNode> children;
    protected boolean isWord;
    // char data; // ???
    
    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }
    
    public boolean containsChild(char c) {
        return children.containsKey(c);
    }
    
    public TrieNode getChild(char c) {
        return children.get(c);
    }
    
    public void addChild(char c) {
        TrieNode node = new TrieNode();
        this.children.put(c, node);
    }
}

class Trie {
    private TrieNode head;
    public Trie() {
        this.head = new TrieNode(); // head
    }
    
    public void insert(String word) {
        TrieNode node = head;
        for(char c: word.toCharArray()) {
            if(!node.containsChild(c))
                node.addChild(c);
            
            node = node.getChild(c);
        }
        node.isWord = true;
    }
    
    private boolean searchCommon(String word, boolean isWord) {
        TrieNode node = head;
        for(char c: word.toCharArray()) {
            if(!node.containsChild(c))
                return false;
            
            node = node.getChild(c);
        }
        
        return (isWord ? node.isWord : true);
    }
    
    public boolean search(String word) {
        return searchCommon(word, true);
    }
    
    public boolean startsWith(String prefix) {
        return searchCommon(prefix, false);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */