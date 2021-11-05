package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));
        assertTrue(commandResult.equals(new CommandResult.Builder("feedback").build()));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));
        assertFalse(commandResult.equals(
                new CommandResult.Builder("different")
                        .build()
        ));

        // different isGoingToShowHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));
        assertFalse(commandResult.equals(
                new CommandResult.Builder("feedback")
                        .showHelp()
                        .build()
        ));

        // different isGoingToExit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
        assertFalse(commandResult.equals(
                new CommandResult.Builder("feedback")
                        .goExit()
                        .build()
        ));

        // different nextApplicationState value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult.Builder("feedback")
                        .displayGroupInformation(CS2103T_GROUP_BUILDER.build())
                        .build()
        ));

        // different isGoingToCauseUndo value -> returns false
        assertFalse(commandResult.equals(
                new CommandResult.Builder("feedback")
                        .goCauseUndo()
                        .build()
        ));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());
        assertNotEquals(commandResult.hashCode(), new CommandResult.Builder("different")
                .build().hashCode());

        // different isGoingToShowHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());
        assertNotEquals(commandResult.hashCode(), new CommandResult.Builder("feedback")
                .showHelp()
                .build().hashCode());

        // different isGoingToExit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
        assertNotEquals(commandResult.hashCode(), new CommandResult.Builder("feedback")
                .goExit()
                .build().hashCode());

        // different nextApplicationState value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult.Builder("feedback")
                .displayGroupInformation(CS2103T_GROUP_BUILDER.build())
                .build().hashCode());

        // different isGoingToCauseUndo value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult.Builder("feedback")
                .goCauseUndo()
                .build().hashCode());
    }
}
