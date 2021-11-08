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
 * A list of groups that enforces uniqueness between its elements and does not allow nulls.
 * A group is considered unique by comparing using {@code Group#isSameGroup(Group)}. As such, adding and updating of
 * groups uses Group#isSameGroup(Group) for equality so as to ensure that the group being added or updated is
 * unique in terms of identity in the UniqueGroupList. However, the removal of a group uses Group#equals(Object) so
 * as to ensure that the group with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Group#isSameGroup(Group)
 */
public class UniqueGroupList implements Iterable<Group> {

    private final ObservableList<Group> internalList = FXCollections.observableArrayList();
    private final ObservableList<Group> internalUnmodifiableList = FXCollections
            .unmodifiableObservableList(internalList);

    /**
     * Checks if the group list contains the specified group.
     *
     * @param toCheck Specified group to check whether it exists in the group list.
     * @return Returns true if group list contains the specified group and false otherwise.
     */
    public boolean contains(Group toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameGroup);
    }

    /**
     * Adds a group with a unique name to the group list.
     *
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
     *
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
     *
     * @param toRemove The group to remove.
     */
    public void remove(Group toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new GroupNotFoundException();
        }
    }

    /**
     * Removes the equivalent person from all groups.
     *
     * @param toRemove The person to remove.
     */
    public void removePersonFromAllGroups(Person toRemove) {
        requireNonNull(toRemove);
        internalList.stream()
                .filter(group -> group.getPersons().contains(toRemove))
                .forEach(group -> group.getPersons().remove(toRemove));
    }

    /**
     * Replaces the current group list with {@code replacement} group list.
     * The new group list must not be null.
     *
     * @param replacement The replacement group list.
     */
    public void setGroups(UniqueGroupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the current group list with {@code groups}.
     * {@code groups} must not contain any duplicates.
     *
     * @param groups The list of groups to replace the current list with.
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
     *
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
     * Checks whether another object is the same group list or has the same list of groups.
     *
     * @param other Other object to check.
     * @return Returns true if the other object is the same group list or
     * has the same list of groups and false otherwise.
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
     *
     * @param groups {@code List} of groups to be checked.
     * @return Returns true if the groups are unique and false otherwise.
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
