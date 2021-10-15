package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.names.Name;
import seedu.address.model.person.Person;

public class JsonAdaptedGroupTest {

    @Test
    public void toModelType_validGroupDetails_returnsGroup() throws IllegalValueException {
        String groupNameString = "group";
        List<String> groupMateIds = new ArrayList<>();
        groupMateIds.add("0-1");
        groupMateIds.add("1-2");
        groupMateIds.add("3-5");
        groupMateIds.add("8-13");
        Map<Id, Person> idToPersonMap = new HashMap<>();
        idToPersonMap.put(Id.parse("0-1"), ALICE);
        idToPersonMap.put(Id.parse("1-2"), BENSON);
        idToPersonMap.put(Id.parse("3-5"), CARL);
        idToPersonMap.put(Id.parse("8-13"), DANIEL);
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(groupNameString, groupMateIds).build();

        Name groupName = new Name("group");
        Group group = new Group(groupName);
        group.add(ALICE);
        group.add(BENSON);
        group.add(CARL);
        group.add(DANIEL);

        assertEquals(group, jsonAdaptedGroup.toModelType(idToPersonMap));
    }
}
