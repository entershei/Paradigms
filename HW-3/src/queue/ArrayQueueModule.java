package queue;

//I: size >= 0 && element != null
public class ArrayQueueModule {
    private static int size;
    private static Object[] elements = new Object[5];
    private static int head = -1, tail = -1;

    // Pre: element ≠ null
    // Post: n' = n + 1
    // ∀i = (begin..end): a[i]' == a[i])
    // a[newBegin] = element
    public static void enqueue(Object element) {
        assert element != null;

        ++size;
        ensureCapacity(size);

        tail = nextIndexTail();

        elements[tail] = element;
    }

    // Pre: n > 0
    // Post: R = a[begin] && immutable
    public static Object element() {
        assert size > 0;

        return elements[head];
    }

    // Pre: n > 0
    // Post: R = a[begin] && n' == n - 1 &&
    // ∀i = (begin..end): a[i]' == a[i]
    // end' == newEnd(end)
    public static Object dequeue() {
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
    public static int size() {
        return size;
    }

    // Post: R = (n > 0) && immutable
    public static boolean isEmpty() {
        return size == 0;
    }

    // Post: n' == 0 && isEmpty()
    public static void clear() {
        head = -1;
        tail = -1;
        size = 0;
        elements = null;
    }

    // Post: immutable && R = elements
    public static Object[] toArray() {
        Object[] ans = new Object[size];

        int curHead = head;

        for (int i = 0; i < size; ++i) {
            ans[i] = elements[curHead];
            curHead = nextIndexHead(curHead);
        }

        return ans;
    }

    //Pre: I && size > 0
    private static int nextIndexHead(int head) {
        if (head == tail) {
            return -1;
        } else if (head < elements.length - 1){
            return head + 1;
        } else {
            return 0;
        }
    }

    private static int nextIndexTail() {
        if (tail == -1) {
            head = 0;
            return 0;
        } else if (tail < elements.length - 1) {
            return tail + 1;
        } else {
            return 0;
        }
    }

    private static void ensureCapacity(int capacity) {
        if (elements == null) {
            elements = new Object[5];
        }

        if (capacity <= elements.length) {
            return;
        }

        Object[] newElements = new Object[2 * capacity];

        if (head == -1) {
            elements = newElements;
            return;
        }

        if (head <= tail) {
            for (int i = head; i <= tail; ++i) {
                newElements[i - head] = elements[i];
            }
        } else {
            int j = 0;
            for (int i = head; i < elements.length; ++i) {
                newElements[j] = elements[i];
                ++j;
            }

            for (int i = 0; i <= tail; ++i) {
                newElements[j] = elements[i];
                ++j;
            }
        }

        elements = newElements;
        head = 0;
        tail = size - 2;
    }
}
