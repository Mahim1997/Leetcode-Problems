class Bitset {

    private boolean[] bits;
    private int numOnes;
    private boolean isFlipped;
    
    public Bitset(int size) {
        // initially all are zeros
        this.bits = new boolean[size];
        this.numOnes = 0;
        this.isFlipped = false; // initially
    }
    
    private void setBit(int idx){
        if(this.bits[idx] == false){
            this.numOnes++;
        }
        this.bits[idx] = true;
    }
    
    private void clearBit(int idx){
        if(this.bits[idx] == true){
            this.numOnes--;
        }
        this.bits[idx] = false;
    }
    
    public void fix(int idx) {
        if(this.isFlipped == false)
            setBit(idx);
        else
            clearBit(idx);
        
    }
    
    public void unfix(int idx) {
        if(this.isFlipped == false)
            clearBit(idx);
        else
            setBit(idx);
    }
    
    private int getTrueNumberOfOnes(){
        if(this.isFlipped == false)
            return this.numOnes;
        else
            return (this.bits.length - this.numOnes);
    }
    
    public void flip() {
        this.isFlipped = !this.isFlipped;
    }
    
    public boolean all() {
        return (this.getTrueNumberOfOnes() == this.bits.length);
    }
    
    public boolean one() {
        return (this.getTrueNumberOfOnes() > 0);
    }
    
    public int count() {
        return this.getTrueNumberOfOnes();
    }
    
    public String toString() {
        StringBuilder bld = new StringBuilder();
        
        for(int i=0; i<this.bits.length; i++){
            boolean bitXOR = this.bits[i] ^ this.isFlipped;
            char c = (bitXOR == false) ? '0' : '1';
            bld.append(c);
        }
        
        return bld.toString();
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */