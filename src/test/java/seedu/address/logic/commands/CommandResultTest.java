package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.GroupInformationState;
import seedu.address.logic.state.HomeState;
import seedu.address.model.group.Group;

public class CommandResultTest {

    @Test
    public void build_onlyFeedbackSpecified_defaultValuesReturned() {
        // Equivalence Partition {isGoingToShowHelp, isGoingToExit, nextApplicationState, isGoingToCauseUndo}: None
        // specified
        CommandResult commandResult = new CommandResult.Builder("feedback")
                .build();

        assertFalse(commandResult.isGoingToShowHelp());
        assertFalse(commandResult.isGoingToExit());
        assertEquals(new HomeState(), commandResult.getNextApplicationState());
        assertFalse(commandResult.isGoingToCauseUndo());
    }

    @Test
    public void build_setShowHelp_success() {
        // Equivalence Partition {isGoingToShowHelp}: Specified false
        CommandResult commandResult = new CommandResult.Builder("feedback")
                .setGoShowHelp(false)
                .build();
        assertFalse(commandResult.isGoingToShowHelp());

        // Equivalence Partition {isGoingToShowHelp}: Specified true
        commandResult = new CommandResult.Builder("feedback")
                .setGoShowHelp(true)
                .build();
        assertTrue(commandResult.isGoingToShowHelp());
        commandResult = new CommandResult.Builder("feedback")
                .goShowHelp()
                .build();
        assertTrue(commandResult.isGoingToShowHelp());
    }

    @Test
    public void build_setGoExit_success() {
        // Equivalence Partition {isGoingToExit}: Specified false
        CommandResult commandResult = new CommandResult.Builder("feedback")
                .setGoExit(false)
                .build();
        assertFalse(commandResult.isGoingToExit());

        // Equivalence Partition {isGoingToExit}: Specified true
        commandResult = new CommandResult.Builder("feedback")
                .setGoExit(true)
                .build();
        assertTrue(commandResult.isGoingToExit());
        commandResult = new CommandResult.Builder("feedback")
                .goExit()
                .build();
        assertTrue(commandResult.isGoingToExit());
    }

    @Test
    public void build_setNextApplicationState_success() {
        ApplicationState nextApplicationState = new HomeState();
        // Equivalence Partition {nextApplicationState}: Specified as HomeState
        CommandResult commandResult = new CommandResult.Builder("feedback")
                .setNextApplicationState(nextApplicationState)
                .build();
        assertEquals(nextApplicationState, commandResult.getNextApplicationState());
        commandResult = new CommandResult.Builder("feedback")
                .goToHome()
                .build();
        assertEquals(nextApplicationState, commandResult.getNextApplicationState());

        // Equivalence Partition {nextApplicationState}: Specified as GroupInformationState
        Group group = CS2103T_GROUP_BUILDER.build();
        nextApplicationState = new GroupInformationState(group);
        commandResult = new CommandResult.Builder("feedback")
                .setNextApplicationState(nextApplicationState)
                .build();
        assertEquals(nextApplicationState, commandResult.getNextApplicationState());
        commandResult = new CommandResult.Builder("feedback")
                .displayGroupInformation(group)
                .build();
        assertEquals(nextApplicationState, commandResult.getNextApplicationState());
    }

    @Test
    public void build_setGoCauseUndo_success() {
        // Equivalence Partition {isGoingToCauseUndo}: Specified false
        CommandResult commandResult = new CommandResult.Builder("feedback")
                .setGoCauseUndo(false)
                .build();
        assertFalse(commandResult.isGoingToCauseUndo());

        // Equivalence Partition {isGoingToCauseUndo}: Specified true
        commandResult = new CommandResult.Builder("feedback")
                .setGoCauseUndo(true)
                .build();
        assertTrue(commandResult.isGoingToCauseUndo());
        commandResult = new CommandResult.Builder("feedback")
                .goCauseUndo()
                .build();
        assertTrue(commandResult.isGoingToCauseUndo());
    }

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
                        .goShowHelp()
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
                .goShowHelp()
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
