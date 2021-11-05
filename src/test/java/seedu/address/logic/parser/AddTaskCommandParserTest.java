package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.common.Description;
import seedu.address.model.task.Task;
import seedu.address.testutil.TypicalGroups;
import seedu.address.testutil.TypicalTasks;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser(TypicalGroups.CS2101_GROUP_BUILDER.build());

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = TypicalTasks.TASK_1_BUILDER.build();

        // whitespace only preamble
        assertParseSuccess(parser, DESCRIPTION_DESC_TASK_1, new AddTaskCommand(expectedTask,
                TypicalGroups.CS2101_GROUP_BUILDER.build()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, VALID_DESCRIPTION_TASK_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid description
        assertParseFailure(parser, INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);

        // non-empty preambleSPO
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DESCRIPTION_DESC_TASK_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
    }
}
