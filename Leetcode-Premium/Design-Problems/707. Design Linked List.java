class Node{
    public int val;
    public Node next;
    
    public Node(int val){
        this.val = val;
    }
}

class MyLinkedList {
    
    private int length;
    private Node head;
    private Node tail;
    
    public MyLinkedList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }
    
    private Node getNode(int index){
        if(index >= this.length)
            return null;
        
        Node node = this.head;
        for(int i=0; i<index; i++){
            node = node.next;
        }
        return node;
    }
    
    public int get(int index) {
        Node node = getNode(index);
        return (node == null) ? -1 : node.val;
    }
    
    public void addAtHead(int val) {
        Node newNode = new Node(val); // initial stage
        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            newNode.next = this.head;
            this.head = newNode;
        }
        this.length++;
    }
    
    public void addAtTail(int val) {
        Node newNode = new Node(val); // initial stage
        if(this.tail == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.length++;
    }
    
    public void addAtIndex(int index, int val) {
        // edge cases
        // System.out.println("addAtIndex(), index = "
        //     + index + ", val = " + val 
        //     + ", len = " +  this.length);
        
        // 0. Ignore
        if(index > this.length){
            return;
        }
        
        // 1. If null OR 2. Insertion at head
        if((this.length == 0) || (index == 0)){
            addAtHead(val);
        }
        else if(index == this.length){
            // 3. Insert at tail
            addAtTail(val);
        }
        else{
            // 4. Normal condition
            Node prevNode = getNode(index - 1);
            Node nextNode = prevNode.next;
            
            // adjust links
            Node newNode = new Node(val);
            prevNode.next = newNode;
            newNode.next = nextNode;
            
            this.length++;
        }
    }
    
    public void deleteAtIndex(int index) {
        // edge cases
        
        // 1. If null
        if(this.length == 0){
            return;
        }
        
        // 2. If index == 0
        if(index == 0){
            this.head = this.head.next;
            
            if(this.head == null)
                this.tail = null;
            
            this.length--;
            return;
        }
        
        // 3. Ignore if index >= this.length
        if(index >= this.length){
            return;
        }
        
        // 4. Normal case
        Node prevNode = getNode(index - 1);
        
        if(prevNode.next == null)
            prevNode.next = null;
        else
            prevNode.next = prevNode.next.next;
        
        if(index == (this.length - 1))
            this.tail = prevNode;
        
        this.length--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */