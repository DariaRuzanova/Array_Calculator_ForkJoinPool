import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ArraySum extends RecursiveTask<Integer> {

    private final int[] array;

    public ArraySum(int[] array) {
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 2) {
            return Arrays.stream(array).sum();
        }
        ArraySum task1 = new ArraySum(Arrays.copyOfRange(array, 0, array.length / 2));
        ArraySum task2 = new ArraySum(Arrays.copyOfRange(array, array.length / 2, array.length));
        invokeAll(task1,task2);
        return task1.join() + task2.join();
    }
}
