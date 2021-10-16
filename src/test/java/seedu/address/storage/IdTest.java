package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class IdTest {

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
}
