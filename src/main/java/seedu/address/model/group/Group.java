package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;
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
     * Name, Description, persons and tasks must be present and not null.
     */
    public Group(Name name, Description description, UniquePersonList persons, UniqueTaskList tasks) {
        requireAllNonNull(name, persons, tasks);
        this.name = name;
        this.description = description;
        this.persons = persons;
        this.tasks = tasks;
    }

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
     */
    public Group(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.description = null;
        this.persons = new UniquePersonList();
        this.tasks = new UniqueTaskList();
    }

    /**
     * Copies the given group.
     *
     * @param groupToCopy The group to be copied.
     */
    public Group(Group groupToCopy) {
        name = groupToCopy.name;
        description = groupToCopy.description;
        persons = new UniquePersonList();
        UniquePersonList oldUniquePersonList = groupToCopy.persons;
        oldUniquePersonList.forEach(persons::add);
        tasks = new UniqueTaskList();
        UniqueTaskList oldUniqueTaskList = groupToCopy.tasks;
        oldUniqueTaskList.forEach(tasks::add);
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
     *
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

    /**
     * Checks whether the group has the given group mate.
     *
     * @param groupMate The group mate.
     * @return Returns true if the group mate is in the group and false otherwise.
     */
    public boolean hasGroupMate(Person groupMate) {
        return persons.contains(groupMate);
    }

    /**
     * Checks whether the group has the given set of group mates.
     *
     * @param groupMates The group mates.
     * @return Returns true if the set of group mates is in the group and false otherwise.
     */
    public boolean hasGroupMates(Set<Person> groupMates) {
        return groupMates.stream().anyMatch(groupMate -> this.hasGroupMate(groupMate));
    }

    /**
     * Adds a task to the group.
     *
     * @param task Task to be added to the group.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a {@code Task} from the group.
     *
     * @param task Task to be deleted from the group.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Checks whether the group has the given task.
     *
     * @param task The task.
     * @return Returns true if the group has the given task and false otherwise.
     */
    public boolean hasTask(Task task) {
        return tasks.contains(task);
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
     * @return Returns true if both groups have the same name or are the same group and false otherwise.
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
     * @return Return true if both groups have equal instance attributes and false otherwise.
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
