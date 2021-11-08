package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests if a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonNameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Constructs a PersonNameContainsKeywordsPredicate object.
     *
     * @param keywords Keywords to compare person names to.
     */
    public PersonNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
