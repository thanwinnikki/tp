package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2101_GROUP_BUILDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.GroupCommand;
import seedu.address.logic.commands.GroupsCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MarkAsDoneCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.logic.state.GroupInformationState;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.GroupUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person), null);
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        Group group = CS2101_GROUP_BUILDER.build();
        ApplicationState applicationState = new GroupInformationState(group);
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task),
                applicationState);
        assertEquals(new AddTaskCommand(task, group), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, null) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", null) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), null);
        assertEquals(new DeleteCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        Group group = CS2101_GROUP_BUILDER.build();
        ApplicationState applicationState = new GroupInformationState(group);
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), applicationState);
        assertEquals(new DeleteTaskCommand(INDEX_FIRST, group), command);
    }

    @Test
    public void parseCommand_deleteGroup() throws Exception {
        DeleteGroupCommand command = (DeleteGroupCommand) parser.parseCommand(
                DeleteGroupCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), null);
        assertEquals(new DeleteGroupCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_markAsDone() throws Exception {
        Group group = CS2101_GROUP_BUILDER.build();
        ApplicationState applicationState = new GroupInformationState(group);
        MarkAsDoneCommand command = (MarkAsDoneCommand) parser.parseCommand(
                MarkAsDoneCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), applicationState);
        assertEquals(new MarkAsDoneCommand(INDEX_FIRST, group), command);
    }

    @Test
    public void parseCommand_group() throws Exception {
        GroupCommand command = (GroupCommand) parser.parseCommand(
                GroupCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), null);
        assertEquals(new GroupCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor), null);
        assertEquals(new EditCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, null) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", null) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")), null);
        assertEquals(new FindCommand(new PersonNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findGroup() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindGroupCommand command = (FindGroupCommand) parser.parseCommand(
                FindGroupCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")), null);
        assertEquals(new FindGroupCommand(new GroupNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_addGroup() throws Exception {
        Group group = new GroupBuilder().build();
        AddGroupCommand command = (AddGroupCommand) parser.parseCommand(GroupUtil.getGroupCommand(group), null);
        assertEquals(new AddGroupCommand(group), command);
    }


    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, null) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", null) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD, null) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3", null) instanceof ListCommand);
    }

    @Test
    public void parseCommand_groups() throws Exception {
        assertTrue(parser.parseCommand(GroupsCommand.COMMAND_WORD, null) instanceof GroupsCommand);
        assertTrue(parser.parseCommand(GroupsCommand.COMMAND_WORD + " 3", null) instanceof GroupsCommand);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        Group group = CS2101_GROUP_BUILDER.build();
        ApplicationState applicationState = new GroupInformationState(group);
        RemoveCommand command = (RemoveCommand) parser.parseCommand(
                RemoveCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased(), applicationState);
        assertEquals(new RemoveCommand(INDEX_FIRST, CS2101_GROUP_BUILDER.build()), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", null));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand", null));
    }
}
