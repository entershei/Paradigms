package queue;

//I: size >= 0 && element != null
public class ArrayQueue {
    private int size;
    private Object[] elements = new Object[5];
    private int head, tail;

    ArrayQueue() {
        elements = new Object[5];
        head = tail = -1;
    }

    // Pre: element ≠ null
    // Post: n' = n + 1
    // ∀i = (begin..end): a[i]' == a[i])
    // a[newBegin] = element
    public void enqueue(Object element) {
        assert element != null;

        ++size;
        ensureCapacity(size);

        tail = nextIndexTail();
        elements[tail] = element;
    }

    // Pre: n > 0
    // Post: R = a[begin] && immutable
    public Object element() {
        assert size > 0;

        return elements[head];
    }

    // Pre: n > 0
    // Post: R = a[begin] && n' == n - 1 &&
    // ∀i = (begin..end): a[i]' == a[i]
    // end' == newEnd(end)
    public Object dequeue() {
        assert size > 0;

        Object firstElement = elements[head];
        elements[head] = null;
        head = nextIndexHead(head);

        if (head == -1) {
            tail = -1;
        }

        --size;

        return firstElement;
    }

    // Post: R = n && immutable
    public int size() {
        return size;
    }

    // Post: R = (n > 0) && immutable
    public boolean isEmpty() {
        return size == 0;
    }

    // Post: n' == 0 && isEmpty()
    public void clear() {
        head = -1;
        tail = -1;
        size = 0;
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

    // Post: immutable && R = elements
    public Object[] toArray() {
        return copyElements(size);
    }

    //Pre: I && size > 0
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
