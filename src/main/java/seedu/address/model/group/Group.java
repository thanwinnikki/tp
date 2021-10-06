package seedu.address.model.group;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.model.names.Name;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a Group in the address book.
 * Guarantees: Details are present and not null. persons is not validated todo
 */
public class Group {

    // Identity fields
    private final Name name;

    // Data fields
    private final UniquePersonList persons;

    /**
     * Name must be present and not null.
     */
    public Group(Name name) {
        requireNonNull(name);
        this.name = name;
        this.persons = new UniquePersonList();
    }

    public Name getName() {
        return name;
    }

    public UniquePersonList getPersons() {
        return persons;
    }

    /**
     * Only checks if 2 groups have the same name or are the same group.
     * @param otherGroup Other group to compare.
     * @return Returns true if both groups have the same name or are the same group;
     */
    public boolean isSameGroup(Group otherGroup) {
        if (otherGroup == this) {
            return true;
        }
        boolean same = (otherGroup != null) &&
            otherGroup.getName().equals(this.getName());

        return same;
    }

    /**
     * This checks if both names and person lists are the same.
     * @param other Other object to compare.
     * @return Return true if both groups have the same name and person list.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if(!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return otherGroup.getName().equals(this.getName())
                && otherGroup.getPersons().equals(this.getPersons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, persons);
    }

    @Override
    public String toString() {
        return getName().toString();
    }
}
