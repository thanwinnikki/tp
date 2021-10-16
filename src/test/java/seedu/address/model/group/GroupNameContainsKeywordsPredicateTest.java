package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GroupNameContainsKeywordsPredicate firstPredicate =
                new GroupNameContainsKeywordsPredicate(firstPredicateKeywordList);
        GroupNameContainsKeywordsPredicate secondPredicate =
                new GroupNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GroupNameContainsKeywordsPredicate firstPredicateCopy =
                new GroupNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Group -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        GroupNameContainsKeywordsPredicate predicate =
                new GroupNameContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new GroupBuilder().withName("CS2103T CS2101").build()));

        // Multiple keywords
        predicate = new GroupNameContainsKeywordsPredicate(Arrays.asList("CS2103T", "CS2101"));
        assertTrue(predicate.test(new GroupBuilder().withName("CS2103T CS2101").build()));

        // Only one matching keyword
        predicate = new GroupNameContainsKeywordsPredicate(Arrays.asList("ES2660", "CS2103T"));
        assertTrue(predicate.test(new GroupBuilder().withName("CS2103T CS2101").build()));

        // Mixed-case keywords
        predicate = new GroupNameContainsKeywordsPredicate(Arrays.asList("cS2103t", "Cs2101"));
        assertTrue(predicate.test(new GroupBuilder().withName("CS2103T CS2101").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GroupNameContainsKeywordsPredicate predicate = new GroupNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GroupBuilder().withName("CS2101").build()));

        // Non-matching keyword
        predicate = new GroupNameContainsKeywordsPredicate(Arrays.asList("ES2660"));
        assertFalse(predicate.test(new GroupBuilder().withName("CS2101 CS2103T").build()));

        // Keywords match members, but does not match name
        predicate = new GroupNameContainsKeywordsPredicate(Arrays.asList("ALICE", "BENSON", "CARL", "DANIEL"));
        assertFalse(predicate.test(new GroupBuilder().withName("CS2103T")
                .withMembers(ALICE, BENSON, CARL, DANIEL).build()));
    }
}
