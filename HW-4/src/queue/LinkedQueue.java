package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    LinkedQueue() {
        super();
        head = null;
        tail = null;
    }

    @Override
    protected void enqueueImpl(Object element) {
        if (size == 1) {
            Node cur = new Node(element);
            tail = cur;
            head = cur;
        } else {
            Node tailNew = new Node(element);
            tail.next = tailNew;
            tail = tailNew;
        }
    }

    @Override
    protected Object elementImpl() {
        return head.value;
    }

    @Override
    protected void removeImpl() {
        head = head.next;

        if (head == null) {
            tail = null;
        }
    }

    @Override
    protected void clearImpl() {
        head = null;
        tail = null;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value, Node next) {
            assert value != null;

            this.value = value;
            this.next = next;
        }

        public Node(Object value) {
            assert value != null;

            this.value = value;
            this.next = null;
        }

        public Node() {
            this.value = null;
            this.next = null;
        }
    }
}
