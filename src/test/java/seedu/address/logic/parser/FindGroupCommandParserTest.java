package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;

public class FindGroupCommandParserTest {

    private FindGroupCommandParser parser = new FindGroupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindGroupCommand expectedFindCommand =
                new FindGroupCommand(new GroupNameContainsKeywordsPredicate(Arrays.asList("CS2100", "ES2660")));
        assertParseSuccess(parser, "CS2100 ES2660", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2100 \n \t ES2660  \t", expectedFindCommand);
    }

}
