package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

/**
 * A list of Tasks that enforces uniqueness between its elements and does not allow nulls.
 * A Task is considered unique by comparing using {@code Task#isSameTask(Task)}. As such, adding and updating of
 * Tasks uses Task#isSameTask(Task) for equality so as to ensure that the Task being added or updated is
 * unique in terms of identity in the UniqueTaskList. However, the removal of a Task uses Task#equals(Object) so
 * as to ensure that the Task with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#isSameTask(Task)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();
    private final ObservableList<Task> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the task list contains the specified task.
     *
     * @param toCheck Specified task to check whether it exists in the task list.
     * @return Returns true if list contains an equivalent task as the given argument and false otherwise.
     */

    public boolean contains(Task toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTask);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Fetches a specified {@code Task} according to its index in the list.
     */
    public Task getTask(int i) {
        return internalList.get(i);
    }

    /**
     * Adds a task to the list.
     * The task must not already exist in the list.
     *
     * @param toAdd Task to add to the task list.
     */
    public void add(Task toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The Task identity of {@code editedTask} must not be the same as another existing Task in the list.
     *
     * @param target The target task you are trying to replace.
     * @param editedTask The task you are trying to replace with.
     */
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }

    /**
     * Removes the equivalent task from the list.
     * The task must exist in the list.
     *
     * @param toRemove The task to remove.
     */
    public void remove(Task toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Replaces the current task list with {@code replacement} task list.
     * The new task list must not be null.
     *
     * @param replacement The replacement task list.
     */
    public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     *
     * @param tasks The list of tasks to replace the current list with.
     */
    public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }

    /**
     * Returns the list of tasks as an unmodifiable {@code ObservableList}.
     *
     * @return ObservableList containing the tasks in the task list.
     */
    public ObservableList<Task> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && internalList.equals(((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Checks if the tasks in the {@code List} of tasks are the same.
     *
     * @param tasks {@code List} of tasks to be checked.
     * @return Returns true if the tasks are unique and false otherwise.
     */
    private boolean tasksAreUnique(List<Task> tasks) {
        for (int i = 0; i < tasks.size() - 1; i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).isSameTask(tasks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
