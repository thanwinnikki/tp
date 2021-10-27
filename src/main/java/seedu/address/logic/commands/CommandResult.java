package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.state.ApplicationState;
import seedu.address.model.group.Group;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private String feedbackToUser;

    /** Help information should be shown to the user. */
    private boolean showHelp;

    /** The application should exit. */
    private boolean exit;

    private ApplicationState nextApplicationState;
    private Object nextDataToStore;
    private boolean isGoingToCauseUndo;

    /**
     * Builder class to help with creating different types of {@code CommandResult} objects.
     */
    public static class Builder {

        private CommandResult commandResultToBuild;

        /**
         * Creates a {@code CommandResult.Builder} to help with creating a {@code CommandResult} object.
         *
         * @param feedbackToUser The feedback to be shown to the user.
         */
        public Builder(String feedbackToUser) {
            commandResultToBuild = new CommandResult(feedbackToUser);
        }

        /**
         * Completes the creation of the {@code CommandResult} object.
         *
         * @return The {@code CommandResult} object that was created.
         */
        public CommandResult build() {
            return commandResultToBuild;
        }

        /**
         * Sets whether the {@code CommandResult} object will cause help information to be shown to the user.
         *
         * @param willShowHelp Whether help information will be shown.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setShowHelp(boolean willShowHelp) {
            commandResultToBuild.showHelp = willShowHelp;
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
         * @param willExit Whether the application will exit.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setExit(boolean willExit) {
            commandResultToBuild.exit = willExit;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to exit.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goExit() {
            return setExit(true);
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to change to the given state.
         *
         * @param nextApplicationState The application state to change to as a result of the command execution.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder setNextAppState(ApplicationState nextApplicationState) {
            assert nextApplicationState != null : "The value of nextApplicationState cannot be null.";
            commandResultToBuild.nextApplicationState = nextApplicationState;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to store the given data.
         *
         * @param nextDataToStore The data to store.
         * @param <T> The type of the data to be stored.
         * @return This {@code CommandResult.Builder} instance.
         */
        public <T> Builder setNextDataToStore(T nextDataToStore) {
            commandResultToBuild.nextDataToStore = nextDataToStore;
            return this;
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to go to the home state.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goToHome() {
            return setNextAppState(ApplicationState.HOME)
                    .setNextDataToStore(null);
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to go to the group information state.
         *
         * @param group The group to display information about.
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder displayGroupInformation(Group group) {
            assert group != null : "The value of group cannot be null.";
            return setNextAppState(ApplicationState.GROUP_INFORMATION)
                    .setNextDataToStore(group);
        }

        /**
         * Sets the {@code CommandResult} object to cause the application to undo.
         *
         * @return This {@code CommandResult.Builder} instance.
         */
        public Builder goCauseUndo() {
            commandResultToBuild.isGoingToCauseUndo = true;
            return this;
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        nextApplicationState = ApplicationState.HOME;
        nextDataToStore = null;
        isGoingToCauseUndo = false;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        showHelp = false;
        exit = false;
        nextApplicationState = ApplicationState.HOME;
        nextDataToStore = null;
        isGoingToCauseUndo = false;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public ApplicationState getNextAppState() {
        return nextApplicationState;
    }

    public <T> T getNextDataToStore() {
        return (T) nextDataToStore;
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
        boolean willBothShowHelp = showHelp == otherCommandResult.showHelp;
        boolean willBothExit = exit == otherCommandResult.exit;
        boolean haveSameNextApplicationStates = nextApplicationState.equals(otherCommandResult.nextApplicationState);
        boolean haveNullNextDataToStore = nextDataToStore == null && otherCommandResult.nextDataToStore == null;
        boolean haveSameNextDataToStore = haveNullNextDataToStore
                || (nextDataToStore != null && nextDataToStore.equals(otherCommandResult.nextDataToStore));
        boolean areBothGoingToCauseUndo = isGoingToCauseUndo == otherCommandResult.isGoingToCauseUndo;
        return haveSameFeedbacksToUser && willBothShowHelp && willBothExit && haveSameNextApplicationStates
                && haveSameNextDataToStore && areBothGoingToCauseUndo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, nextApplicationState, nextDataToStore, isGoingToCauseUndo);
    }
}
