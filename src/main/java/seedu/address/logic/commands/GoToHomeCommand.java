package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class GoToHomeCommand extends AlwaysRunnableCommand {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_SUCCESS = "Now on Home Page.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult.Builder(MESSAGE_SUCCESS)
                .goToHome()
                .build();
    }
}
