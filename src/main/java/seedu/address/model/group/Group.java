package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.function.Consumer;

import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Represents a Group in the address book.
 * Guarantees: Details are present and not null. persons is not validated todo
 */
public class Group {

    // Identity fields
    private final Name name;

    // Data fields
    private final Description description;
    private final UniquePersonList persons;
    private final UniqueTaskList tasks;

    /**
     * Name and Description must be present and not null.
     */
    public Group(Name name, Description description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.persons = new UniquePersonList();
        this.tasks = new UniqueTaskList();
    }

    /**
     * Name must be present and not null.
     * This is just a temporary fix
     */
    public Group(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.description = null;
        this.persons = new UniquePersonList();
        this.tasks = new UniqueTaskList();
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
    }

    public UniquePersonList getPersons() {
        return persons;
    }

    public UniqueTaskList getTasks() {
        return tasks;
    }

    /**
     * Adds a person to the group.
     * @param person Person to be added to the group.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Removes a group mate from the group.
     *
     * @param groupMate The group mate which is to be removed from the group.
     */
    public void removeGroupMate(Person groupMate) {
        persons.remove(groupMate);
    }

    public boolean hasGroupMate(Person groupMate) {
        return persons.contains(groupMate);
    }

    /**
     * Adds a task to the group.
     * @param task Task to be added to the group.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a {@code Task} from the group.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Carries out the given actions on each group mate in this group.
     *
     * @param groupMateConsumer The {@code Consumer} that performs actions on each group mate.
     */
    public void doForEachGroupMate(Consumer<? super Person> groupMateConsumer) {
        persons.forEach(groupMateConsumer);
    }

    /**
     * Carries out the given actions on each task in this group.
     *
     * @param taskConsumer The {@code Consumer} that performs actions on each task.
     */
    public void doForEachTask(Consumer<? super Task> taskConsumer) {
        tasks.forEach(taskConsumer);
    }

    /**
     * Only checks if 2 groups have the same name or are the same group.
     *
     * @param otherGroup Other group to compare.
     * @return Returns true if both groups have the same name or are the same group;
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }
        boolean same = (otherGroup != null)
                && otherGroup.getName().equals(this.getName());

        return same;
    }

    /**
     * This checks if all instance attributes are equal.
     *
     * @param other Other object to compare.
     * @return Return true if both groups have equal instance attributes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        boolean haveEqualNames = name.equals(otherGroup.name);
        boolean haveNullDescriptions = description == null && otherGroup.description == null;
        boolean haveEqualDescriptions = haveNullDescriptions
                || (description != null && description.equals(otherGroup.description))
                || (otherGroup.description != null && otherGroup.description.equals(description));
        boolean haveEqualGroupMateLists = persons.equals(otherGroup.persons);
        boolean haveEqualTaskLists = tasks.equals(otherGroup.tasks);
        return haveEqualNames && haveEqualDescriptions && haveEqualGroupMateLists && haveEqualTaskLists;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, persons, tasks);
    }

    @Override
    public String toString() {
        return getName().toString();
    }
}
