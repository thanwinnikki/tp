package seedu.address.model.group;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} is in the given group.
 */
public class IsGroupPredicate implements Predicate<Group> {
    private final Group group;

    public IsGroupPredicate(Group group) {
        this.group = group;
    }

    @Override
    public boolean test(Group group) {
        return this.group.equals(group);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsGroupPredicate // instanceof handles nulls
                && group.equals(((IsGroupPredicate) other).group)); // state check
    }
}
