package queue;

public interface Queue {
    // Pre: element ≠ null
    // Post: n' = n + 1
    // ∀i = (begin..end): a[i]' == a[i])
    // a[newBegin] = element
    void enqueue(Object element);

    // Pre: n > 0
    // Post: R = a[begin] && immutable
    Object element();

    // Pre: n > 0
    // Post: R = a[begin] && n' == n - 1 &&
    // ∀i = (begin..end): a[i]' == a[i]
    // end' == newEnd(end)
    Object dequeue();

    // Post: R = n && immutable
    int size();

    // Post: R = (n > 0) && immutable
    boolean isEmpty();

    // Post: n' == 0 && isEmpty()
    void clear();

    // Post: immutable && R = elements
    Object[] toArray();
}
