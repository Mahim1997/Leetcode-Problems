class Vector2D {
    private int[] arr;
    private int idx;
    
    public Vector2D(int[][] vec) {
        int size = 0;

        for(int i=0; i<vec.length; i++){
            size += vec[i].length;
        }
        this.arr = new int[size];
        this.idx = 0;
        for(int i=0; i<vec.length; i++){
            for(int j=0; j<vec[i].length; j++){
                this.arr[idx++] = vec[i][j];
            }
        }
        
        this.idx = 0;
    }
    
    public int next() {
        return this.arr[this.idx++];    
    }
    
    public boolean hasNext() {
        if(this.idx < this.arr.length)
            return true;
        else
            return false;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */