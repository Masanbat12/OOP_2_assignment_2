package Part_B;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Creating the class as Adapter so it,
 * would act as a connector between two incompatible
 * interfaces (Callable and Future),
 * otherwise they cannot be connected directly.
 * @param <T>
 */
public class fType<T> extends FutureTask<T> implements Comparable<fType<T>>  {
    /**
     * Declaration of task and type objects.
     */
    private final Task<T> task;
    private TaskType type;

    /**
     * Constructor for the fType class.
     * Gets a Callable Type.
     */
    public fType(Callable c){
        super(c);
        this.task = null;
    }
    /**
     * Constructor for the fType class.
     * Gets a Callable Type.
     */
    public fType(Task<T> task){
        super(task.getTask());
        this.task = null;
    }

    /**
     * Getter for the Callable.
     */
    public Callable<T> getCallable(){
        return this.task.getTask();
    }

    /**
     * Getter for the Task.
    */
    public Task<T> getTask() {
        return task;
    }
    /**
     * Getter for the type.
     */
    public Task<T> getType() {
        return task;
    }

    /**
     * Getter for the priority element of type int.
    */
    public int getPriority(){
        return ((task).getType().getPriorityValue());
    }
    @Override
    public int compareTo(fType<T> o) {
        return Integer.compare(this.type.getPriorityValue() , o.getType().getPriorityValue());
    }

}
