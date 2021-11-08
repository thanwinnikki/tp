package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.JoinGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new JoinGroupCommand object.
 */
public class JoinGroupCommandParser implements Parser<JoinGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the JoinGroupCommand
     * and returns an AddGroupCommand object for execution.
     *
     * @param args Arguments of the user input to be parsed by JoinGroupCommandParser.
     * @throws ParseException if  the user does not conform to the expected format.
     */
    @Override
    public JoinGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_PERSON);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP, PREFIX_PERSON)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, JoinGroupCommand.MESSAGE_USAGE));
        }

        Index groupIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GROUP).get());
        Set<Index> personIndexes = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_PERSON));

        return new JoinGroupCommand(groupIndex, personIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap ArgumentMultimap of all the stored prefixes' value.
     * @param prefixes ALl prefixes that are supposed to be checked against the argumentMultiMap.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
