package seedu.address.logic.commands;

import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;

/**
 * Displays a help window that redirects users to the user guide.
 */
public class HelpCommand extends AlwaysRunnableCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final ApplicationState currentApplicationState;

    public HelpCommand(ApplicationState currentApplicationState) {
        this.currentApplicationState = currentApplicationState;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult.Builder(SHOWING_HELP_MESSAGE)
                .goShowHelp()
                .setNextApplicationState(currentApplicationState)
                .build();
    }
}
