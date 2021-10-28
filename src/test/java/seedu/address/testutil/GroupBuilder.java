package seedu.address.testutil;

import java.util.Arrays;

import javafx.collections.ObservableList;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_GROUP_NAME = "CS2103T";
    public static final String DEFAULT_GROUP_DESCRIPTION = "This is a CS module group";

    private Name name;
    private Description description;
    private UniquePersonList persons;
    private UniqueTaskList tasks;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = new Name(DEFAULT_GROUP_NAME);
        description = new Description(DEFAULT_GROUP_DESCRIPTION);
        persons = new UniquePersonList();
        tasks = new UniqueTaskList();
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        description = groupToCopy.getDescription();
        persons = groupToCopy.getPersons();
        tasks = groupToCopy.getTasks();
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Group} that we are building.
     */
    public GroupBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code UniqueTaskList} of the {@code Group} to contain specified {@code Task}.
     * @return
     */
    public GroupBuilder withTasks(Task... taskList) {
        UniqueTaskList editedList = new UniqueTaskList();
        Arrays.stream(taskList).forEach(task -> editedList.add(task));
        this.tasks = editedList;
        return this;
    }

    /**
     * Sets the {@code UniquePersonList} of the {@code Group} to contain specified {@code Person}.
     * @return
     */
    public GroupBuilder withMembers(Person... personList) {
        UniquePersonList editedList = new UniquePersonList();
        Arrays.stream(personList).forEach(person -> editedList.add(person));
        this.persons = editedList;
        return this;
    }

    /**
     * Builds the {@code Group} as intended for testing.
     * @return
     */
    public Group build() {
        Group buildGroup = new Group(name, description);
        ObservableList<Person> personList = persons.asUnmodifiableObservableList();
        for (int i = 0; i < personList.size(); i++) {
            buildGroup.add(personList.get(i));
        }
        ObservableList<Task> taskList = tasks.asUnmodifiableObservableList();
        for (int i = 0; i < taskList.size(); i++) {
            buildGroup.addTask(taskList.get(i));
        }
        return buildGroup;
    }
}
