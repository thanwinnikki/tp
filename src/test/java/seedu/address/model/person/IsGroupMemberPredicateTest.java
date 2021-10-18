package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGroups.CS2101_GROUP_BUILDER;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.Group;

public class IsGroupMemberPredicateTest {

    private Group firstPredicateGroup = CS2103T_GROUP_BUILDER.build();
    private Group secondPredicateGroup = CS2101_GROUP_BUILDER.build();

    @Test
    public void equals() {
        IsGroupMemberPredicate firstPredicate =
                new IsGroupMemberPredicate(firstPredicateGroup);
        IsGroupMemberPredicate secondPredicate =
                new IsGroupMemberPredicate(secondPredicateGroup);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsGroupMemberPredicate firstPredicateCopy =
                new IsGroupMemberPredicate(firstPredicateGroup);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isGroupMember_returnsTrue() {
        IsGroupMemberPredicate predicate =
                new IsGroupMemberPredicate(firstPredicateGroup);
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_isNotGroupMember_returnsFalse() {
        IsGroupMemberPredicate predicate =
                new IsGroupMemberPredicate(firstPredicateGroup);
        assertFalse(predicate.test(HOON));
    }
}
