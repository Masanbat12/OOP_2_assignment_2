package Part_B;

import java.util.Objects;
import java.util.concurrent.Callable;

public class Task<T> implements Comparable<Task<T>>, Callable<T> {
    /**
     * Declaration of 3 elements.
     */
    private TaskType type;
    private Callable<T> task;
    private int priority;

    /**
     * Getter for priority
     */
    public int getPriority() {

        return priority;
    }

    /**
        constructor for the Task class that get Callable and TaskType.
    */
    public Task(Callable<T> task, TaskType type) {
        this.task = task;
        this.type = type;
        priority = 1;
    }

    /**
        Computes a result, or throws an exception if unable to do so.
    */
    public T call() throws Exception {
        return task.call();
    }

    /**
     * Factory method "createTask" that gets a task of type Callable,
     * and a type of TaskType.
     * It's checking the type the is inserted and if one of
     * the condition is return true, the function return a new task
     * as required, else return null.
     */
    public static Task createTask(Callable task, TaskType type) {
        if (Objects.equals(type, TaskType.COMPUTATIONAL)) {
            return new Task(task, TaskType.COMPUTATIONAL);
        } else if (Objects.equals(type, TaskType.IO)) {
            return new Task(task, TaskType.IO);
        } else if (Objects.equals(type, TaskType.OTHER)) {
            return new Task(task, TaskType.OTHER);
        }
        return null;
    }

    /**
     * Factory method that gets only task of type Callable
     */

    public static Task createTask(Callable task) {
        return createTask(task, TaskType.OTHER);
    }

    /**
     * Getter for TaskType: type
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Getter for Callable: task
     */
    public Callable<T> getTask() {
        return task;
    }

    /**
     * CompareTo function is that returns Integer, it's operate by
     * comparing 2  int values numerically getPriorityValue.
     * returns 0 if it is equal.
     */
    @Override
    public int compareTo(Task<T> o) {
        return Integer.compare(this.type.getPriorityValue(), o.getType().getPriorityValue());
    }

    /**
     * Function to return the value of the priority.
    */
    public int getPriorityValue() {
        return priority;
    }
}