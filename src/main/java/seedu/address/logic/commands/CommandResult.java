package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.GroupInformationState;
import seedu.address.logic.state.HomeState;
import seedu.address.model.group.Group;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isGoingToShowHelp;

    /** The application should exit. */
    private final boolean isGoingToExit;

    private final ApplicationState nextApplicationState;
    private final boolean isGoingToCauseUndo;

    /**
     * Builder class to help with creating different types of {@code CommandResult} objects.
     */
    public static class Builder {

        private String feedbackToUser;
        private boolean isGoingToShowHelp;
        private boolean isGoingToExit;
        private ApplicationState nextApplicationState;
        private boolean isGoingToCauseUndo;

        /**
         * Creates a {@code CommandResult.Builder} to help with creating a {@code CommandResult} object.
         *
         * @param feedbackToUser The feedback to be shown to the user.
         */
        public Builder(String feedbackToUser) {
            this.feedbackToUser = feedbackToUser;
            isGoingToShowHelp = false;
            isGoingToExit = false;
            nextApplicationState = new HomeState();
            isGoingToCauseUndo = false;
        }

        /**
         * Completes the creation of the {@code CommandResult} object.
         *
         * @return The {@code CommandResult} object that was created.
         */
        public CommandResult build() {
            return new CommandResult(feedbackToUser, isGoingToShowHelp, isGoingToExit, nextApplicationState,
                    isGoingToCauseUndo);
        }

        /**
         * Sets whether the {@code CommandResult} object will cause help information to be shown to the user.
         *
         * @param isGoingToShowHelp Whether help information will be shown.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setShowHelp(boolean isGoingToShowHelp) {
            this.isGoingToShowHelp = isGoingToShowHelp;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause help information to be shown to the user.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder showHelp() {
            return setShowHelp(true);
        }

        /**
         * Sets whether the {@code CommandResult} object will cause the application to exit.
         *
         * @param isGoingToExit Whether the application will exit.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setGoExit(boolean isGoingToExit) {
            this.isGoingToExit = isGoingToExit;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to exit.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goExit() {
            return setGoExit(true);
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to change to the given state.
         *
         * @param nextApplicationState The application state to change to as a result of the command execution.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setNextApplicationState(ApplicationState nextApplicationState) {
            assert nextApplicationState != null : "The value of nextApplicationState cannot be null.";
            this.nextApplicationState = nextApplicationState;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to go to the home state.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goToHome() {
            return setNextApplicationState(new HomeState());
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to go to the group information state.
         *
         * @param group The group to display information about.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder displayGroupInformation(Group group) {
            assert group != null : "The value of group cannot be null.";
            return setNextApplicationState(new GroupInformationState(group));
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to undo.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goCauseUndo() {
            isGoingToCauseUndo = true;
            return this;
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isGoingToShowHelp, boolean isGoingToExit) {
        this(requireNonNull(feedbackToUser), isGoingToShowHelp, isGoingToExit, new HomeState(), false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(requireNonNull(feedbackToUser), false, false, new HomeState(), false);
    }

    private CommandResult(String feedbackToUser, boolean isGoingToShowHelp, boolean isGoingToExit,
            ApplicationState nextApplicationState, boolean isGoingToCauseUndo) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isGoingToShowHelp = isGoingToShowHelp;
        this.isGoingToExit = isGoingToExit;
        this.nextApplicationState = nextApplicationState;
        this.isGoingToCauseUndo = isGoingToCauseUndo;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isGoingToShowHelp() {
        return isGoingToShowHelp;
    }

    public boolean isGoingToExit() {
        return isGoingToExit;
    }

    public ApplicationState getNextApplicationState() {
        return nextApplicationState;
    }

    public boolean isGoingToCauseUndo() {
        return isGoingToCauseUndo;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        boolean haveSameFeedbacksToUser = feedbackToUser.equals(otherCommandResult.feedbackToUser);
        boolean willBothShowHelp = isGoingToShowHelp == otherCommandResult.isGoingToShowHelp;
        boolean willBothExit = isGoingToExit == otherCommandResult.isGoingToExit;
        assert nextApplicationState != null : "The next application state must always be non-null.";
        boolean haveSameNextApplicationStates = nextApplicationState.equals(otherCommandResult.nextApplicationState);
        boolean areBothGoingToCauseUndo = isGoingToCauseUndo == otherCommandResult.isGoingToCauseUndo;
        return haveSameFeedbacksToUser && willBothShowHelp && willBothExit && haveSameNextApplicationStates
                && areBothGoingToCauseUndo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isGoingToShowHelp, isGoingToExit, nextApplicationState, isGoingToCauseUndo);
    }
}
