package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements;
    private int head, tail;

    ArrayQueue() {
        super();
        elements = new Object[5];
        head = -1;
        tail = -1;
    }

    @Override
    protected void enqueueImpl(Object element) {
        ensureCapacity(size);
        tail = nextIndexTail();
        elements[tail] = element;
    }

    @Override
    protected Object elementImpl() {
        return elements[head];
    }

    @Override
    protected void removeImpl() {
        elements[head] = null;
        head = nextIndexHead(head);

        if (head == -1) {
            tail = -1;
        }
    }

    @Override
    public void clearImpl() {
        head = -1;
        tail = -1;
        elements = null;
    }

    private Object[] copyElements(int newSize) {
        Object[] ans = new Object[newSize];

        int curHead = head;

        for (int i = 0; curHead != -1; ++i) {
            ans[i] = elements[curHead];
            curHead = nextIndexHead(curHead);
        }

        return ans;
    }

    private int nextIndexHead(int head) {
        if (head == tail) {
            return -1;
        } else if (head != elements.length - 1){
            return head + 1;
        } else {
            return 0;
        }
    }

    private int nextIndexTail() {
        if (tail == -1) {
            head = 0;
            return 0;
        } else if (tail < elements.length - 1) {
            return tail + 1;
        } else {
            return 0;
        }
    }

    private void ensureCapacity(int capacity) {
        if (elements == null) {
            elements = new Object[5];
        }

        if (capacity <= elements.length) {
            return;
        }

        if (head == -1) {
            elements = new Object[2 * capacity];
            return;
        }

        Object[] newElements = copyElements(2 * capacity);

        head = 0;
        tail = size - 2;
        elements = newElements;
    }
}
