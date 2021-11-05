package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class IdTest {

    @Test
    public void parse_null_throwsIllegalValueException() {
        // Equivalence Partition {idString}: Null
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse(null));
    }

    @Test
    public void parse_noDelimiter_throwsIllegalValueException() {
        // Equivalence Partition {idString}: No delimiter (the hyphen)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("12"));
    }

    @Test
    public void parse_delimiterAtStart_throwsIllegalValueException() {
        // Equivalence Partition {idString}: Delimiter at the start (the hyphen)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("-1-2"));
    }

    @Test
    public void parse_delimiterAtEnd_throwsIllegalValueException() {
        // Equivalence Partition {idString}: Delimiter at the end (the hyphen)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-"));
    }

    @Test
    public void parse_extraDelimiters_throwsIllegalValueException() {
        // Equivalence Partition {idString}: Extra delimiters (the hyphens)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1--2"));
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1---2"));
    }

    @Test
    public void parse_extraValues_throwsIllegalValueException() {
        // Equivalence Partition {values}: More than two values
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-3"));
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-3-5"));
    }

    @Test
    public void parse_invalidDateCharacters_throwsIllegalValueException() {
        // Equivalence Partition {epochDay}: Invalid characters
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("!@#-2"));
    }

    @Test
    public void parse_invalidTimeCharacters_throwsIllegalValueException() {
        // Equivalence Partition {nanoOfDay}: Invalid characters
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-!@#"));
    }

    @Test
    public void parse_invalidDateRadix_throwsIllegalValueException() {
        // Equivalence Partition {epochDay}: Invalid radix (another base, base 17, instead of base 16)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("g-2"));
    }

    @Test
    public void parse_invalidTimeRadix_throwsIllegalValueException() {
        // Equivalence Partition {nanoOfDay}: Invalid radix (another base, base 17, instead of base 16)
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-g"));
    }

    @Test
    public void parse_invalidDateRange_throwsIllegalValueException() {
        // Equivalence Partition {epochDay}: Date beyond maximum supported date, which is '+999999999-12-31'
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("550a1b48f8-2"));
    }

    @Test
    public void parse_invalidTimeRange_throwsIllegalValueException() {
        // Equivalence Partition {nanoOfDay}: Date beyond maximum supported time, which is '23:59:59.999999999'
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-4e94914f0000"));
    }

    @Test
    public void compareTo_equalIds_returnsZero() throws IllegalValueException {
        // Equivalence Partition {epochDay, nanoOfDay}: Both same
        Id id1 = Id.parse("0-1");
        Id id2 = Id.parse("0-1");
        int expectedComparisonResult = 0;
        int actualComparisonResult = id1.compareTo(id2);
        assertEquals(expectedComparisonResult, actualComparisonResult);
    }

    @Test
    public void compareTo_differentDate_returnsCorrectNonZero() throws IllegalValueException {
        // Equivalence Partition {epochDay}: Different values
        Id id1 = Id.parse("0-1");
        Id id2 = Id.parse("1-1");
        int actualComparisonResult = id1.compareTo(id2);
        assertTrue(actualComparisonResult < 0);

        actualComparisonResult = id2.compareTo(id1);
        assertTrue(actualComparisonResult > 0);
    }

    @Test
    public void compareTo_differentTime_returnsCorrectNonZero() throws IllegalValueException {
        // Equivalence Partition {nanoOfDay}: Different values
        Id id1 = Id.parse("0-1");
        Id id2 = Id.parse("0-2");
        int actualComparisonResult = id1.compareTo(id2);
        assertTrue(actualComparisonResult < 0);

        actualComparisonResult = id2.compareTo(id1);
        assertTrue(actualComparisonResult > 0);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        // Equivalence Partition {type}: Different types
        assertNotEquals(null, Id.generateId());
        assertNotEquals(new IdTest(), Id.generateId());
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        // Equivalence Partition {reference}: Same object
        Id id = Id.generateId();
        assertEquals(id, id);
    }

    @Test
    public void equals_differentDate_returnsFalse() throws IllegalValueException {
        // Equivalence Partition {epochDay}: Different dates
        Id id1 = Id.parse("0-2");
        Id id2 = Id.parse("1-2");
        assertNotEquals(id1, id2);
    }

    @Test
    public void equals_differentTime_returnsFalse() throws IllegalValueException {
        // Equivalence Partition {nanoOfDay}: Different times
        Id id1 = Id.parse("1-1");
        Id id2 = Id.parse("1-2");
        assertNotEquals(id1, id2);
    }

    @Test
    public void equals_sameDateTime_returnsTrue() throws IllegalValueException {
        // Equivalence Partition {epochDay, nanoOfDay}: All same
        Id id1 = Id.parse("1-2");
        Id id2 = Id.parse("1-2");
        assertEquals(id1, id2);
    }

    @Test
    public void generateUniqueId_multipleIds_generatesUniqueId() {
        Set<Id> idSet = new HashSet<>();
        Id id1 = Id.generateUniqueId(idSet);
        idSet.add(id1);
        for (int i = 0; i < 20; i++) {
            Id id2 = Id.generateUniqueId(idSet);
            idSet.add(id2);
            assertNotEquals(id1, id2);
            id1 = id2;
        }
    }
}
