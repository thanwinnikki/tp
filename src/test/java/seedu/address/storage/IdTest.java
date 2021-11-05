package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class IdTest {

    @Test
    public void parse_null_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse(null));
    }

    @Test
    public void parse_noDelimiter_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("12"));
    }

    @Test
    public void parse_delimiterAtStart_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("-1-2"));
    }

    @Test
    public void parse_delimiterAtEnd_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-"));
    }

    @Test
    public void parse_extraDelimiters_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1--2"));
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1---2"));
    }

    @Test
    public void parse_extraValues_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-3"));
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-2-3-5"));
    }

    @Test
    public void parse_invalidDateCharacters_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("!@#-2"));
    }

    @Test
    public void parse_invalidTimeCharacters_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-!@#"));
    }

    @Test
    public void parse_invalidDateRadix_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("g-2"));
    }

    @Test
    public void parse_invalidTimeRadix_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-g"));
    }

    @Test
    public void parse_invalidDateRange_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("550a1b48f8-2"));
    }

    @Test
    public void parse_invalidTimeRange_throwsIllegalValueException() {
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> Id.parse("1-4e94914f0000"));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertNotEquals(null, Id.generateId());
        assertNotEquals(new IdTest(), Id.generateId());
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        Id id = Id.generateId();
        assertEquals(id, id);
    }

    @Test
    public void equals_differentDateTime_returnsFalse() throws IllegalValueException {
        Id id1 = Id.parse("0-1");
        Id id2 = Id.parse("1-2");
        assertNotEquals(id1, id2);
    }

    @Test
    public void equals_sameDateTime_returnsTrue() throws IllegalValueException {
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
