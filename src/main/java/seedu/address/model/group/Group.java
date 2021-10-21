package seedu.address.model.group;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.names.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

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

    /**
     * Name and Description must be present and not null.
     */
    public Group(Name name, Description description) {
        requireAllNonNull(name, description);
        this.name = name;
        this.description = description;
        this.persons = new UniquePersonList();
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

    /**
     * Adds a person to the group.
     * @param person Person to be added to the group.
     */
    public void add(Person person) {
        this.persons.add(person);
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
        boolean same = (otherGroup != null)
                && otherGroup.getName().equals(this.getName());

        return same;
    }

    /**
     * This checks if both names, description and person lists are the same.
     * @param other Other object to compare.
     * @return Return true if both groups have the same name and person list.
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
        return otherGroup.getName().equals(this.getName())
                && otherGroup.getDescription().equals((this.getDescription()))
                && otherGroup.getPersons().equals(this.getPersons());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, persons);
    }

    @Override
    public String toString() {
        return getName().toString();
    }
}
