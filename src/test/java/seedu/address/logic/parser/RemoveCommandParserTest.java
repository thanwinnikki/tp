package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.testutil.TypicalGroups;

public class RemoveCommandParserTest {

    private RemoveCommandParser parser = new RemoveCommandParser(TypicalGroups.CS2101_GROUP_BUILDER.build());

    @Test
    public void parse_validArgs_returnsRemoveCommand() {
        assertParseSuccess(parser, "1", new RemoveCommand(INDEX_FIRST,
                TypicalGroups.CS2101_GROUP_BUILDER.build()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
    }
}
