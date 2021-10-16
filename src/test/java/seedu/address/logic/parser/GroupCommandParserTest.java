package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.model.group.Group;
import seedu.address.model.names.Name;
import seedu.address.testutil.GroupBuilder;


public class GroupCommandParserTest {
    private GroupCommandParser parser = new GroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new GroupBuilder(TENNIS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_SMTH, new GroupCommand(expectedGroup));

        // multiple names - last name accepted
        assertParseSuccess(parser,NAME_DESC_SMTH + NAME_DESC_SMTH2, new GroupCommand(expectedGroup));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_SMTH, expectedMessage, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_SMTH, String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupCommand.MESSAGE_USAGE));
    }
}
