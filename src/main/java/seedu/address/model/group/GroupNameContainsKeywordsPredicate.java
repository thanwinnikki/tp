package seedu.address.model.group;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests if a {@code Group}'s {@code Name} matches any of the keywords given.
 */
public class GroupNameContainsKeywordsPredicate implements Predicate<Group> {
    private final List<String> keywords;

    /**
     * Constructs a GroupNameContainsKeywordsPredicate object.
     *
     * @param keywords Keywords to compare group names to.
     */
    public GroupNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Group group) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(group.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GroupNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
