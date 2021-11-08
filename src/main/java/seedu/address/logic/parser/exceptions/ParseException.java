package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * Creates an ParseException.
     *
     * @param message Message of the exception.
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Creates an ParseException.
     *
     * @param message Message of the exception.
     * @param cause Cause of this exception being thrown.
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
