package queue;

//I: size >= 0 && element != null
public abstract  class AbstractQueue implements Queue {
    protected int size;

    AbstractQueue() {
        size = 0;
    }

    @Override
    public void enqueue(Object element) {
        assert element != null;

        ++size;
        enqueueImpl(element);
    }

    protected abstract void enqueueImpl(Object element);

    @Override
    public Object element() {
        assert size > 0;

        return elementImpl();
    }

    protected abstract Object elementImpl();

    @Override
    public Object dequeue() {
        assert size > 0;

        Object first = element();

        --size;
        removeImpl();

        return first;
    }

    protected abstract void removeImpl();

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        clearImpl();
    }

    protected abstract void clearImpl();

    @Override
    public Object[] toArray() {
        Object[] ans = new Object[size];

        for (int i = 0; i < size; ++i) {
            ans[i] = dequeue();
            enqueue(ans[i]);
        }

        return ans;
    }
}