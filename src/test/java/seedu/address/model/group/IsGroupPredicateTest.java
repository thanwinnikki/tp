package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGroups.CS2101_GROUP_BUILDER;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;

import org.junit.jupiter.api.Test;

public class IsGroupPredicateTest {

    private Group firstPredicateGroup = CS2103T_GROUP_BUILDER.build();
    private Group secondPredicateGroup = CS2101_GROUP_BUILDER.build();

    @Test
    public void equals() {
        IsGroupPredicate firstPredicate =
                new IsGroupPredicate(firstPredicateGroup);
        IsGroupPredicate secondPredicate =
                new IsGroupPredicate(secondPredicateGroup);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsGroupPredicate firstPredicateCopy =
                new IsGroupPredicate(firstPredicateGroup);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isGroup_returnsTrue() {
        IsGroupPredicate predicate =
                new IsGroupPredicate(firstPredicateGroup);
        assertTrue(predicate.test(CS2103T_GROUP_BUILDER.build()));
    }

    @Test
    public void test_isNotGroup_returnsFalse() {
        IsGroupPredicate predicate =
                new IsGroupPredicate(firstPredicateGroup);
        assertFalse(predicate.test(CS2101_GROUP_BUILDER.build()));
    }
}
