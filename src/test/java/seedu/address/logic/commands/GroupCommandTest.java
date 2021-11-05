package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code GroupCommand}.
 */
public class GroupCommandTest {

    @Test
    public void equals() {
        GroupCommand groupFirstCommand = new GroupCommand(INDEX_FIRST);
        GroupCommand groupSecondCommand = new GroupCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(groupFirstCommand.equals(groupFirstCommand));

        // same values -> returns true
        GroupCommand groupFirstCommandCopy = new GroupCommand(INDEX_FIRST);
        assertTrue(groupFirstCommand.equals(groupFirstCommandCopy));

        // different types -> returns false
        assertFalse(groupFirstCommand.equals(1));

        // null -> returns false
        assertFalse(groupFirstCommand.equals(null));

        // different group -> returns false
        assertFalse(groupFirstCommand.equals(groupSecondCommand));
    }

}
