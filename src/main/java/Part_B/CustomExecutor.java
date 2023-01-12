package Part_B;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.*;

public class CustomExecutor<T> extends ThreadPoolExecutor {
    /**
     * Declaration of 3 elements as int, boolean,and one dataStructure.
     * Using dataStructure of ArrayList that gets elements.
     */
    ArrayList<Integer> elementsT = new ArrayList<Integer>(10);
    private PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
    //result of the Runtime operation is the half of the number of available processors.
    private static final int Min_T = Runtime.getRuntime().availableProcessors() / 2;
    //result of this operation is the number of available processors less 1
    private static final int Max_T = Runtime.getRuntime().availableProcessors() - 1;
    private boolean isTerminated;

    /**
     * An empty constructor for CustomExecutor class.
     */
    public CustomExecutor() {
      super(Min_T, Max_T, 3000, TimeUnit.MILLISECONDS,
      new PriorityBlockingQueue<>(Min_T, Comparator.comparing(task -> ((Task) task))));
        initC();
        isTerminated = false;
    }
    /**
     * initialize the hashmap
     */
    private void initC() {

        this.elementsT.add(-1);
        this.elementsT.add(0);
        this.elementsT.add(1);
        this.elementsT.add(2);
        this.elementsT.add(3);
    }

    /**
     * This function schedule the execution of a task.
     * Gets an object of Task.
     * The function then returns a handle or a "future" object that can be used to track
     * the status of the task and retrieve its result once it has completed.
     */

    public <T> Future<T> submit(Task<T> task) {
        if (isTerminated) {
            return null;
        }
        RunnableFuture<T> futureTask = new fType<T>(task);
        execute(futureTask);
        return futureTask;
    }

    /**
     * This function schedule the execution of a task.
     * Gets an object of Callable.
     * The function then returns a handle or a "future" object that can be used to track
     * the status of the task and retrieve its result once it has completed.
     */
    public <T> Future<T> submit(Callable<T> c) {
        if (isTerminated) {
            return null;
        }
        Task<T> task = Task.createTask(c);
        return submit(task);
    }

    /**
     * This function schedule the execution of a task.
     * Gets 2 objects of Callable and TaskType.
     * The function then returns a handle or a "future" object that can be used to track
     * the status of the task and retrieve its result once it has completed.
     */
    public <T> Future<T> submit(Callable<T> callable, TaskType type) {
        if (isTerminated) {
            return null;
        }
        Task<T> task = Task.createTask(callable, type);
        return submit(task);
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if(r != null)
           elementsT.remove(0);
        super.beforeExecute(t, r);
    }

    /**
     * Returns the max priority in queue in O(1) complexity,
     * using if conditions:
     * 1 or 2 or 3, the max priority in the queue, 1 is the highest value.
     */
    public int getCurrentMax() {
       int k = 1;
       int j = -1;
        while(k < 3){
            if (elementsT.get(k) != 0) {
                return j+1;
            }
            k++;
        }
        return 0;
    }


    /**
     * This function stop the execution of all tasks that have been scheduled or are currently running,
     * and release any tasks that are being used by the CustomExecutor.
     */
    public void gracefullyTerminate() {
        try {
            isTerminated = this.awaitTermination(300, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

