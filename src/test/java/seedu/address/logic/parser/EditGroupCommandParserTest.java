package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_VOLLEYBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_VOLLEYBALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.testutil.EditGroupDescriptorBuilder;

public class EditGroupCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE);

    private EditGroupCommandParser parser = new EditGroupCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_VOLLEYBALL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditGroupCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_VOLLEYBALL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_VOLLEYBALL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid phone

        // valid name followed by invalid phone. The test case for invalid name followed by valid description
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DESCRIPTION_DESC_SPORTS + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + NAME_DESC_VOLLEYBALL + DESCRIPTION_DESC_SPORTS;

        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withName(VALID_NAME_VOLLEYBALL).withDescription(VALID_DESCRIPTION_SPORTS).build();
        EditGroupCommand expectedCommand = new EditGroupCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TASK_1;

        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_TASK_1).build();
        EditGroupCommand expectedCommand = new EditGroupCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_VOLLEYBALL;
        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withName(VALID_NAME_VOLLEYBALL).build();
        EditGroupCommand expectedCommand = new EditGroupCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TASK_1;
        descriptor = new EditGroupDescriptorBuilder().withDescription(VALID_DESCRIPTION_TASK_1).build();
        expectedCommand = new EditGroupCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NAME_DESC_VOLLEYBALL + DESCRIPTION_DESC_SPORTS
                + NAME_DESC_SWIMMING + DESCRIPTION_DESC_TASK_1;

        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder().withName(VALID_NAME_SWIMMING)
                .withDescription(VALID_DESCRIPTION_TASK_1).build();
        EditGroupCommand expectedCommand = new EditGroupCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_DESCRIPTION_DESC + DESCRIPTION_DESC_TASK_1;
        EditGroupCommand.EditGroupDescriptor descriptor = new EditGroupDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_TASK_1).build();
        EditGroupCommand expectedCommand = new EditGroupCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_TASK_1 + INVALID_NAME_DESC + NAME_DESC_SWIMMING;
        descriptor = new EditGroupDescriptorBuilder().withName(VALID_NAME_SWIMMING)
                .withDescription(VALID_DESCRIPTION_TASK_1).build();
        expectedCommand = new EditGroupCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
