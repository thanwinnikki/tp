package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.group.Group;

/**
 * Tests that a {@code Person} is in the given group.
 */
public class IsGroupMemberPredicate implements Predicate<Person> {
    private final Group group;

    public IsGroupMemberPredicate(Group group) {
        this.group = group;
    }

    @Override
    public boolean test(Person person) {
        return group.getPersons()
                .contains(person);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsGroupMemberPredicate // instanceof handles nulls
                && group.equals(((IsGroupMemberPredicate) other).group)); // state check
    }
}