package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.GroupInformationState;
import seedu.address.logic.state.HomeState;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.group.Group;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_helpFromHome_success() {
        ApplicationState currentApplicationState = new HomeState();
        CommandResult expectedCommandResult = new CommandResult.Builder(SHOWING_HELP_MESSAGE)
                .goShowHelp()
                .goToHome()
                .build();
        assertCommandSuccess(new HelpCommand(currentApplicationState), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpFromGroupInformationState_success() {
        Group group = CS2103T_GROUP_BUILDER.build();
        ApplicationState currentApplicationState = new GroupInformationState(group);
        CommandResult expectedCommandResult = new CommandResult.Builder(SHOWING_HELP_MESSAGE)
                .goShowHelp()
                .displayGroupInformation(group)
                .build();
        assertCommandSuccess(new HelpCommand(currentApplicationState), model, expectedCommandResult, expectedModel);
    }
}
