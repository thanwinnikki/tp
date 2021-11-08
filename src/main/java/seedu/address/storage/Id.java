package seedu.address.storage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents the ID reference to any object saved in storage.
 * This can be used to refer to objects stored at another location within the file instead of writing its details
 * multiple times.
 */
public class Id implements Comparable<Id> {

    public static final String MESSAGE_MALFORMED_ID = "This ID is malformed.";

    private static final String DELIMITER = "-";
    private static final int EPOCH_DAY_TOKEN_INDEX = 0;
    private static final int NANO_OF_DAY_TOKEN_INDEX = 1;
    private static final int HEX_RADIX = 16;
    private static final String STRING_REPRESENTATION_FORMAT = "%s-%s";

    private final LocalDateTime localDateTime;

    private Id() {
        localDateTime = LocalDateTime.now();
    }

    private Id(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * Generates an {@code Id} object that is unique within the passed set of {@code Id} objects.
     *
     * @param idSet The set of {@code Id} objects that the generated {@code Id} must be unique in.
     * @return The generated {@code Id}.
     */
    public static Id generateUniqueId(Set<Id> idSet) {
        Id id = generateId();
        while (idSet.contains(id)) {
            id = generateId();
        }
        return id;
    }

    /**
     * Generates an {@code Id}.
     *
     * @return The generated {@code Id}.
     */
    public static Id generateId() {
        return new Id();
    }

    /**
     * Parses the given {@code String} ID to produce the corresponding {@code Id}.
     *
     * @param idString The {@code String} ID.
     * @return The corresponding {@code Id}.
     * @throws IllegalValueException If a malformed {@code String} ID is passed.
     */
    public static Id parse(String idString) throws IllegalValueException {
        String[] tokens = getTokens(idString);
        String epochDayHex = tokens[EPOCH_DAY_TOKEN_INDEX];
        LocalDate localDate = parseLocalDate(epochDayHex);
        String nanoOfDayHex = tokens[NANO_OF_DAY_TOKEN_INDEX];
        LocalTime localTime = parseLocalTime(nanoOfDayHex);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return new Id(localDateTime);
    }

    private static String[] getTokens(String idString) throws IllegalValueException {
        if (idString == null || idString.startsWith(DELIMITER) || idString.endsWith(DELIMITER)) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        String[] tokens = idString.split(DELIMITER);
        if (tokens.length != 2) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        return tokens;
    }

    private static LocalDate parseLocalDate(String epochDayHex) throws IllegalValueException {
        long epochDay;
        LocalDate localDate;
        try {
            epochDay = Long.parseLong(epochDayHex, HEX_RADIX);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        try {
            localDate = LocalDate.ofEpochDay(epochDay);
        } catch (DateTimeException e) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        return localDate;
    }

    private static LocalTime parseLocalTime(String nanoOfDayHex) throws IllegalValueException {
        long nanoOfDay;
        LocalTime localTime;
        try {
            nanoOfDay = Long.parseLong(nanoOfDayHex, HEX_RADIX);
        } catch (NumberFormatException e) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        try {
            localTime = LocalTime.ofNanoOfDay(nanoOfDay);
        } catch (DateTimeException e) {
            throw new IllegalValueException(MESSAGE_MALFORMED_ID);
        }
        return localTime;
    }

    @Override
    public int compareTo(Id o) {
        return localDateTime.compareTo(o.localDateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Id)) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Id other = (Id) o;
        return localDateTime.equals(other.localDateTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(localDateTime);
    }

    @Override
    public String toString() {
        long epochDay = localDateTime.toLocalDate().toEpochDay();
        long nanoOfDay = localDateTime.toLocalTime().toNanoOfDay();
        String epochDayHex = Long.toHexString(epochDay);
        String nanoOfDayHex = Long.toHexString(nanoOfDay);
        String stringRepresentation = String.format(STRING_REPRESENTATION_FORMAT, epochDayHex, nanoOfDayHex);
        return stringRepresentation;
    }
}
