package seedu.address.model;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs User preferences which data is retrieved from.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings GUI settings to be set.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     *
     * @return Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     *
     * @param addressBookFilePath Address book file path to be set.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     *
     * @param addressBook Address book to be set.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook.
     *
     * @return Returns the AddressBook.
     * */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book and false otherwise.
     *
     * @param person Person to be checked with.
     * @return Returns true if a person with the same identity as {@code person} exists in the address book
     * and false otherwise.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book and false otherwise.
     *
     * @param group Group to be checked with.
     * @return Returns true if a group with the same identity as {@code group} exists in the address book
     * and false otherwise.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     *
     * @param target Person to delete.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     *
     * @param person Person to add.
     */
    void addPerson(Person person);

    /**
     * Adds the given group.
     * {@code group} must not already exist in the address book.
     *
     * @param group Group to add.
     */
    void addGroup(Group group);

    /**
     * Replaces the person {@code target} in the address book with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target The target person you are trying to replace.
     * @param editedPerson The person you are trying to replace with.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Replaces the group {@code target} in the address book with {@code editedGroup}.
     * {@code target} must exist in the address book.
     * The group identity of {@code editedGroup} must not be the same as another existing group in the address book.
     *
     * @param target The target group you are trying to replace.
     * @param editedGroup The group you are trying to replace with.
     */
    void setGroup(Group target, Group editedGroup);

    /**
     * Returns an unmodifiable view of the filtered person list.
     *
     * @return Returns an unmodifiable view of the filtered person list.
     * */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to be used as filter.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<? super Person> predicate);

    /**
     * Returns the current predicate used to filter the filtered person list.
     *
     * @return The current predicate used to filter the filtered person list.
     */
    Predicate<? super Person> getFilteredPersonListPredicate();

    //=========== Group =============================================================

    /**
     * Deletes the given group.
     * The group must exist in the address book.
     *
     * @param target Group to be deleted.
     */
    void deleteGroup(Group target);

    /**
     * Returns an unmodifiable view of the filtered group list.
     *
     * @return Returns an unmodifiable view of the filtered group list.
     * */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     *
     * @param predicate Predicate to be used as filter.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<? super Group> predicate);

    /**
     * Returns the current predicate used to filter the filtered group list.
     *
     * @return The current predicate used to filter the filtered group list.
     */
    Predicate<? super Group> getFilteredGroupListPredicate();

    /**
     * Adds the person objects in the set to the specified group.
     * The group must exist in the address book.
     *
     * @param target
     */
    void addToGroup(Group target, Set<Person> persons);
}
