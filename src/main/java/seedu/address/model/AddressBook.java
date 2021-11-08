package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Class that wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson and .isSameGroup comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueGroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new UniqueGroupList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Groups in the {@code toBeCopied}
     *
     * @param toBeCopied Address book to be copied.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     *
     * @param persons Persons to set.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     *
     * @param groups Groups to set.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     *
     * @param newData New data to be reset with.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        ObservableList<Group> oldGroupList = newData.getGroupList();
        ObservableList<Group> newGroupList = FXCollections.observableArrayList();
        oldGroupList.forEach(oldGroup -> {
            Group newGroup = new Group(oldGroup);
            newGroupList.add(newGroup);
        });
        setGroups(newGroupList);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book and false otherwise.
     *
     * @param person Person to be checked with.
     * @return Returns true if a person with the same identity as {@code person} exists in the address book
     * and false otherwise.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book and false otherwise.
     *
     * @param group Group to be checked with.
     * @return Returns true if a group with the same identity as {@code group} exists in the address book
     * and false otherwise.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     *
     * @param p Person to add.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     *
     * @param g Group to add.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Adds {@code persons} to {@code target}.
     *
     * @param target Group to add persons to.
     * @param persons Persons to be added.
     */
    public void addToGroup(Group target, Set<Person> persons) {
        persons.forEach(target::add);
    }

    /**
     * Replaces the person {@code target} in the address book with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target The target person you are trying to replace.
     * @param editedPerson The person you are trying to replace with.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the group {@code target} in the address book with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     *
     * @param target The target group you are trying to replace.
     * @param editedGroup The group you are trying to replace with.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);

        groups.setGroup(target, editedGroup);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key Person to be removed.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        groups.removePersonFromAllGroups(key);
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     *
     * @param key Group to be removed.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && groups.equals(((AddressBook) other).groups));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
