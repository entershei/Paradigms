package queue;

public class ArrayQueueModuleTest {
    public static void fill() {
        for (int i = 0; i < 10; i++) {
            ArrayQueueModule.enqueue(i + 10);
        }
    }

    public static void dump() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                    ArrayQueueModule.size() + " " +
                            ArrayQueueModule.element() + " " +
                            ArrayQueueModule.dequeue()
            );
        }
    }

    public static void clear() {
        System.out.println(ArrayQueueModule.size());
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.size());
    }

    public static void main(String[] args) {
        //fill();
        //dump();
        ArrayQueueModule.enqueue(0);

        ArrayQueueModule.enqueue(1);

        ArrayQueueModule.enqueue(2);

        Object[] ans = ArrayQueueModule.toArray();
        System.out.println(ans.length);
        for (int i = 0; i < ans.length; ++i) {
            System.out.println(ans[i]);
        }
    }
}
