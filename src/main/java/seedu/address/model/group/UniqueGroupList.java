package seedu.address.model.group;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.person.Person;

/**
 * A list of groups.
 * todo
 */
public class UniqueGroupList implements Iterable<Group> {

    private final ObservableList<Group> internalList = FXCollections.observableArrayList();
    private final ObservableList<Group> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Checks if the group list contains a specified group.
     * @param toCheck Specified group to check whether it exists in the group list.
     * @return True if group list contains the specified group.
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGroup);
    }

    /**
     * Adds a group with a unique name to the group list.
     * @param toAdd Group to add to the group list.
     */
    public void add(Group toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the group {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the group list.
     * @param target The target group you are trying to replace.
     * @param editedGroup The group you are trying to replace with.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GroupNotFoundException();
        }

        if (!target.isSameGroup(editedGroup) && contains(editedGroup)) {
            throw new DuplicateGroupException();
        }

        internalList.set(index, editedGroup);
    }

    /**
     * Removes the equivalent group from the list.
     * The group must exist in the list.
     */
    public void remove(Group toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Removes the equivalent person from all groups.
     */
    public void removePersonFromAllGroups(Person toRemove) {
        requireNonNull(toRemove);
        internalList.stream()
                .filter(group -> group.getPersons().contains(toRemove))
                .forEach(group -> group.getPersons().remove(toRemove));
    }

    /**
     * Replace the current group list with {@code replacement} group list.
     * The new group must not be null.
     * @param replacement The replacement group list.
     */
    public void setGroups(UniqueGroupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the current group list with {@code groups}. {@code List} of groups.
     * List of groups must not contain any duplicates.
     * @param groups The {@code List} of groups to replace the current list.
     */
    public void setGroups(List<Group> groups) {
        requireAllNonNull(groups);
        if (!groupsAreUnique(groups)) {
            throw new DuplicateGroupException();
        }
        internalList.setAll(groups);
    }

    /**
     * Returns the list of groups as an unmodifiable {@code ObservableList}.
     * @return ObservableList containing the groups in the group list.
     */
    public ObservableList<Group> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Group> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks whether other object is the same group list or has the same list of groups.
     * @param other Other object to check.
     * @return Returns true if other object is the same group list or has the same list of groups.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueGroupList
                        && internalList.equals(((UniqueGroupList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Checks if the groups in the {@code List} of groups have the same name or are the same group.
     * @param groups {@code List} of groups to be checked.
     * @return
     */
    private boolean groupsAreUnique(List<Group> groups) {
        for (int i = 0; i < groups.size() - 1; i++) {
            for (int j = i + 1; j < groups.size(); j++) {
                if (groups.get(i).isSameGroup(groups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
