public class Linked_list <T> {
    

    public class Node <T> {

        public T data;
        public Node <T> prev;
        public Node <T> next;

        public Node (T data, Node <T> prev, Node <T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public Node() {

        }
    }

    public int size = 0;
    public Node <T> head = null;
    public Node <T> tail = null;

    public void add(T data) {
        if(size == 0) {
            head = new Node<T>(data, null, null);
            tail = head;
        } else {
            tail.next = new Node<T>(data, tail, null);
            tail = tail.next;
        }
        size++;
    }
    
}


