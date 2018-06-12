package queue;

public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT stack) {
        for (int i = 0; i < 10; i++) {
            ArrayQueueADT.enqueue(stack, i + 10);
        }
    }

    public static void dump(ArrayQueueADT stack) {
        while (!ArrayQueueADT.isEmpty(stack)) {
            System.out.println(
                    ArrayQueueADT.size(stack) + " " +
                            ArrayQueueADT.element(stack) + " " +
                            ArrayQueueADT.dequeue(stack)
            );
        }
    }

    public static void clear(ArrayQueueADT stack) {
        System.out.println(ArrayQueueADT.size(stack));
        ArrayQueueADT.clear(stack);
        System.out.println(ArrayQueueADT.size(stack));
    }

    public static void main(String[] args) {
        ArrayQueueADT stack = new ArrayQueueADT();

        fill(stack);
        dump(stack);
        //clear(stack);
    }
}
