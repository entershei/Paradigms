package queue;

//I: size >= 0 && element != null
public class ArrayQueueADT {
    private int size;
    private Object[] elements = new Object[5];
    private int head, tail;

    ArrayQueueADT() {
        elements = new Object[5];
        head = tail = -1;
    }

    // Pre: element ≠ null
    // Post: n' = n + 1
    // ∀i = (begin..end): a[i]' == a[i])
    // a[newBegin] = element
    public static void enqueue(ArrayQueueADT stack, Object element) {
        assert element != null;

        ++stack.size;
        ensureCapacity(stack, stack.size);

        stack.tail = nextIndexTail(stack);
        stack.elements[stack.tail] = element;
    }

    // Pre: n > 0
    // Post: R = a[begin] && immutable
    public static Object element(ArrayQueueADT stack) {
        assert stack.size > 0;

        return stack.elements[stack.head];
    }

    // Pre: n > 0
    // Post: R = a[begin] && n' == n - 1 &&
    // ∀i = (begin..end): a[i]' == a[i]
    // end' == newEnd(end)
    public static Object dequeue(ArrayQueueADT stack) {
        assert stack.size > 0;

        Object firstElement = stack.elements[stack.head];
        stack.elements[stack.head] = null;
        stack.head = nextIndexHead(stack, stack.head);

        if (stack.head == -1) {
            stack.tail = -1;
        }

        --stack.size;

        return firstElement;
    }

    // Post: R = n && immutable
    public static int size(ArrayQueueADT stack) {
        return stack.size;
    }

    // Post: R = (n > 0) && immutable
    public static boolean isEmpty(ArrayQueueADT stack) {
        return stack.size == 0;
    }

    // Post: n' == 0 && isEmpty()
    public static void clear(ArrayQueueADT stack) {
        stack.head = -1;
        stack.tail = -1;
        stack.size = 0;
        stack.elements = null;
    }

    // Post: immutable && R = elements
    public static Object[] toArray(ArrayQueueADT stack) {
        Object[] ans = new Object[stack.size];

        int curHead = stack.head;

        for (int i = 0; i < stack.size; ++i) {
            ans[i] = stack.elements[curHead];
            curHead = nextIndexHead(stack, curHead);
        }

        return ans;
    }

    //Pre: I && size > 0
    private static int nextIndexHead(ArrayQueueADT stack, int head) {
        if (head == stack.tail) {
            return -1;
        } else if (head != stack.elements.length - 1){
            return head + 1;
        } else {
            return 0;
        }
    }

    private static int nextIndexTail(ArrayQueueADT stack) {
        if (stack.tail == -1) {
            stack.head = 0;
            return 0;
        } else if (stack.tail < stack.elements.length - 1) {
            return stack.tail + 1;
        } else {
            return 0;
        }
    }

    private static void ensureCapacity(ArrayQueueADT stack, int capacity) {
        if (stack.elements == null) {
            stack.elements = new Object[5];
        }

        if (capacity <= stack.elements.length) {
            return;
        }

        Object[] newElements = new Object[2 * capacity];

        if (stack.head == -1) {
            stack.elements = newElements;
            return;
        }

        if (stack.head <= stack.tail) {
            for (int i = stack.head; i <= stack.tail; ++i) {
                newElements[i - stack.head] = stack.elements[i];
            }
        } else {
            int j = 0;
            for (int i = stack.head; i < stack.elements.length; ++i) {
                newElements[j] = stack.elements[i];
                ++j;
            }

            for (int i = 0; i <= stack.tail; ++i) {
                newElements[j] = stack.elements[i];
                ++j;
            }
        }
        stack.head = 0;
        stack.tail = stack.size - 2;
        stack.elements = newElements;
    }
}
