package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GroupsCommand.
 */
public class GroupsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new GroupsCommand(), model,
                new CommandResult.Builder(GroupsCommand.MESSAGE_SUCCESS)
                        .setNextAppState(ApplicationState.GROUP_INFORMATION)
                        .build(),
                expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showGroupAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new GroupsCommand(), model,
                new CommandResult.Builder(GroupsCommand.MESSAGE_SUCCESS)
                        .setNextAppState(ApplicationState.GROUP_INFORMATION)
                        .build(),
                expectedModel);
    }
}
