class TrieNode {
    private char data;
    private boolean isWord;
    private Map<Character, TrieNode> children;

    public TrieNode() {
        this.data = '$';
        this.isWord = false;
        this.children = new HashMap<>();
    }

    public TrieNode getChild(char c) {
        return this.children.getOrDefault(c, null);
    }

    public boolean containsChild(char c) {
        return this.children.containsKey(c);
    }

    public TrieNode addChild(char c) {
        TrieNode node = new TrieNode();
        node.data = c;
        this.children.put(c, node);
        return node;
    }

    // Return a copy (instead of original map)
    public Map<Character, TrieNode> getChildren() {
        return new HashMap<>(this.children);
    }

    public void setWord() {
        this.isWord = true;
    }

    public void resetWord() {
        this.isWord = false;
    }

    public boolean isWord() {
        return this.isWord;
    }

    public void setData(char c) {
        this.data = c;
    }

    @Override
    public String toString() {
        return "TrieNode, data: " + this.data + ", isWord: " + this.isWord + ", children: " + this.children.toString();
    }
}

class Trie {
    public TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode itr = root;
        for(char c: word.toCharArray()) {
            if(itr.containsChild(c)) {
                itr = itr.getChild(c);
            }
            else {
                itr = itr.addChild(c);
            }
        }
        itr.setWord();
    }

    private boolean doesExist(String word, boolean isPrefix) {
        TrieNode itr = root;
        for(char c: word.toCharArray()) {
            if(itr.containsChild(c)) {
                itr = itr.getChild(c);
            }
            else {
                return false;
            }
        }
        return (isPrefix ? true : itr.isWord());
    }

    public boolean isPrefix(String prefix) {
        return doesExist(prefix, true);
    }

    public boolean isWord(String word) {
        return doesExist(word, false);
    }

    @Override
    public String toString() {
        return this.root.toString();
    }
}

class Solution {
    private Trie trie;
    private char[][] board;

    private void createTrie(String[] words) {
        this.trie = new Trie();
        for(String word: words) {
            this.trie.addWord(word);
        }
    }

    private boolean isWithin(int row, int col) {
        return (
            (row >= 0) && (col >= 0) &&
            (row < this.board.length) && (col < this.board[0].length)
        );
    }

    private static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int LIMIT = 10;

    private List<String> ansList;

    private boolean[][] visited;

    public List<String> findWords(char[][] board, String[] words) {
        int ROWS = board.length, COLS = board[0].length;

        this.ansList = new ArrayList<>();
        this.board = board;

        // By default: false
        this.visited = new boolean[ROWS][COLS];

        // Create the trie
        createTrie(words);

        for(int r=0; r<board.length; r++) {
            for(int c=0; c<board[r].length; c++) {
                // will change to StringBuilder later
                backtrack(r, c, "", this.trie.root); 
            }
        }
        return this.ansList;
    }

    private void backtrack(
        int row, 
        int col, 
        String wordSoFar,
        TrieNode nodeSoFar
    ) {
        // Check invalid & return
        if(!isWithin(row, col)) { return; }
        if(visited[row][col]) { return; }

        // System.out.println("Calling for row: " + row + ", col: " + col + ", wordSoFar: " + wordSoFar + ", visited: " + visited);

        char currentChar = this.board[row][col];
        if(!nodeSoFar.containsChild(currentChar)) { return; }

        TrieNode currentNode = nodeSoFar.getChild(currentChar);
        String currentWord = wordSoFar + "" + currentChar;

        // Cut off if > 10 has been reached by the grid
        if(currentWord.length() > LIMIT) { return; }

        // Check for word & make it NOT word anymore (for repetitions)
        if(currentNode.isWord()) {
            this.ansList.add(currentWord);
            currentNode.resetWord();
        }

        // Visit before backtrack
        visited[row][col] = true;

        for(int[] dir: directions) {
            int rowNew = row + dir[0], colNew = col + dir[1];
            if(!isWithin(rowNew, colNew)) { continue; }
            if(visited[rowNew][colNew]) { continue; }

            backtrack(rowNew, colNew, currentWord, currentNode);
        }

        // Unvisit after backtrack
        visited[row][col] = false;
    }
}


