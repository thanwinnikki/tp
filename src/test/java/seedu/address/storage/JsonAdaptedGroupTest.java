package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SPORTS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SWIMMING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TENNIS;
import static seedu.address.storage.JsonAdaptedGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.GroupBuilder.DEFAULT_GROUP_DESCRIPTION;
import static seedu.address.testutil.GroupBuilder.DEFAULT_GROUP_NAME;
import static seedu.address.testutil.TypicalGroups.SWIMMING;
import static seedu.address.testutil.TypicalGroups.TENNIS;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CAROL;
import static seedu.address.testutil.TypicalPersons.DONALD;
import static seedu.address.testutil.TypicalTasks.TASK_F_BUILDER;
import static seedu.address.testutil.TypicalTasks.TASK_G_BUILDER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Name;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;
import seedu.address.testutil.GroupBuilder;

public class JsonAdaptedGroupTest {

    private static final Map<Id, Person> ID_TO_PERSON_MAP = new HashMap<>();

    private static final List<String> TENNIS_GROUP_MATE_IDS = new ArrayList<>();
    private static final List<JsonAdaptedTask> TENNIS_TASKS = new ArrayList<>();

    private static final List<String> SWIMMING_GROUP_MATE_IDS = new ArrayList<>();

    static {
        try {
            ID_TO_PERSON_MAP.put(Id.parse("0-1"), AMY);
            ID_TO_PERSON_MAP.put(Id.parse("1-2"), BOB);
            ID_TO_PERSON_MAP.put(Id.parse("3-5"), CAROL);
            ID_TO_PERSON_MAP.put(Id.parse("8-d"), DONALD);
        } catch (IllegalValueException e) {
            assert false : "The IDs should be valid.";
        }

        TENNIS_GROUP_MATE_IDS.add("0-1");
        TENNIS_GROUP_MATE_IDS.add("1-2");
        TENNIS_GROUP_MATE_IDS.add("3-5");
        TENNIS_GROUP_MATE_IDS.add("8-d");
        TENNIS_TASKS.add(new JsonAdaptedTask(TASK_F_BUILDER.build()));
        TENNIS_TASKS.add(new JsonAdaptedTask(TASK_G_BUILDER.build()));

        SWIMMING_GROUP_MATE_IDS.add("8-d");
        SWIMMING_GROUP_MATE_IDS.add("1-2");
    }

    @Test
    public void toModelType_validNonNullGroupDetails_returnsGroup() throws IllegalValueException {
        // Equivalence Partition {groupName, description, groupMateIds, tasks}: Valid and non-null
        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        List<JsonAdaptedTask> tasks = new ArrayList<>(TENNIS_TASKS);
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withDescription(VALID_DESCRIPTION_SPORTS)
                .withGroupMateIds(groupMateIds)
                .withTasks(tasks)
                .build();

        Group group = TENNIS.build();

        assertEquals(group, jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        String groupNameString = null;
        List<String> groupMateIds = new ArrayList<>();
        Map<Id, Person> idToPersonMap = new HashMap<>();
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(groupNameString)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        String groupNameString = "T3@m";
        List<String> groupMateIds = new ArrayList<>();
        Map<Id, Person> idToPersonMap = new HashMap<>();
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(groupNameString)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_nullGroupMateIds_returnsGroupWithNoGroupMates() throws IllegalValueException {
        Map<Id, Person> idToPersonMap = new HashMap<>();
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(DEFAULT_GROUP_NAME)
                .withDescription(DEFAULT_GROUP_DESCRIPTION)
                .build();

        Group group = new GroupBuilder()
                .build();

        assertEquals(group, jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_emptyGroupMateIds_returnsGroupWithNoGroupMates() throws IllegalValueException {
        Map<Id, Person> idToPersonMap = new HashMap<>();
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(DEFAULT_GROUP_NAME)
                .withDescription(DEFAULT_GROUP_DESCRIPTION)
                .build();

        Group group = new GroupBuilder()
                .build();

        assertEquals(group, jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_invalidGroupMateIdFormat_throwsIllegalValueException() {
        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        groupMateIds.remove("1-2");
        groupMateIds.add("g-2");
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = Id.MESSAGE_MALFORMED_ID;
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_groupMateIdMissingFromMap_throwsIllegalValueException() throws IllegalValueException {
        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        idToPersonMap.remove(Id.parse("3-5"));
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = JsonAdaptedGroup.MESSAGE_NO_SUCH_PERSON;
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_duplicateGroupMateId_throwsIllegalValueException() {
        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        groupMateIds.add("0-1");
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = JsonAdaptedGroup.MESSAGE_DUPLICATE_GROUP_MATE;
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_differentGroupMateIdReferencingSameGroupMate_throwsIllegalValueException()
            throws IllegalValueException {
        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        idToPersonMap.put(Id.parse("3-5"), BOB);
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withGroupMateIds(groupMateIds)
                .build();
        String expectedMessage = JsonAdaptedGroup.MESSAGE_DUPLICATE_GROUP_MATE;
        assertThrows(IllegalValueException.class, expectedMessage, () -> jsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_groupMateOrdering_returnsGroupWithCorrectGroupMateOrdering() throws IllegalValueException {
        List<String> groupMateIds = new ArrayList<>();
        groupMateIds.add("8-d");
        groupMateIds.add("3-5");
        groupMateIds.add("1-2");
        groupMateIds.add("0-1");
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        JsonAdaptedGroup tennisReversedGroupMatesJsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withDescription(VALID_DESCRIPTION_SPORTS)
                .withGroupMateIds(groupMateIds)
                .withTasks(TENNIS_TASKS)
                .build();

        Group tennisNormal = TENNIS.build();
        assertNotEquals(tennisNormal, tennisReversedGroupMatesJsonAdaptedGroup.toModelType(idToPersonMap));

        Group tennisReversedGroupMates = new GroupBuilder(tennisNormal)
                .withMembers(DONALD, CAROL, BOB, AMY)
                .build();

        assertEquals(tennisReversedGroupMates, tennisReversedGroupMatesJsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void toModelType_respectiveGroupMatesOnly_returnsGroupWithOnlyRespectiveGroupMates()
            throws IllegalValueException {
        Map<Id, Person> idToPersonMap = new HashMap<>(ID_TO_PERSON_MAP);
        List<String> tennisGroupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        JsonAdaptedGroup tennisJsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withDescription(VALID_DESCRIPTION_SPORTS)
                .withGroupMateIds(tennisGroupMateIds)
                .withTasks(TENNIS_TASKS)
                .build();
        List<String> swimmingGroupMateIds = new ArrayList<>(SWIMMING_GROUP_MATE_IDS);
        JsonAdaptedGroup swimmingJsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_SWIMMING)
                .withDescription(VALID_DESCRIPTION_SPORTS)
                .withGroupMateIds(swimmingGroupMateIds)
                .build();
        assertEquals(TENNIS.build(), tennisJsonAdaptedGroup.toModelType(idToPersonMap));
        assertEquals(SWIMMING.build(), swimmingJsonAdaptedGroup.toModelType(idToPersonMap));
    }

    @Test
    public void jsonAdaptedGroupBuilder_sameGroupDifferentConstructors_returnsSameJsonAdaptedGroup()
            throws IllegalValueException {
        Map<Person, Id> personToIdMap = new HashMap<>();
        personToIdMap.put(AMY, Id.parse("0-1"));
        personToIdMap.put(BOB, Id.parse("1-2"));
        personToIdMap.put(CAROL, Id.parse("3-5"));
        personToIdMap.put(DONALD, Id.parse("8-d"));

        JsonAdaptedGroup jsonAdaptedGroupFromModel = new JsonAdaptedGroup.Builder(TENNIS.build(), personToIdMap)
                .build();

        List<String> groupMateIds = new ArrayList<>(TENNIS_GROUP_MATE_IDS);
        JsonAdaptedGroup jsonAdaptedGroupFromJson = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .withDescription(VALID_DESCRIPTION_SPORTS)
                .withGroupMateIds(groupMateIds)
                .withTasks(TENNIS_TASKS)
                .build();

        assertEquals(jsonAdaptedGroupFromJson, jsonAdaptedGroupFromModel);
    }

    @Test
    public void equals_differentType_returnsFalse() {
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .build();
        assertNotEquals(null, jsonAdaptedGroup);
        assertNotEquals(new JsonAdaptedGroupTest(), jsonAdaptedGroup);
    }

    @Test
    public void equals_sameReference_returnsTrue() {
        JsonAdaptedGroup jsonAdaptedGroup = new JsonAdaptedGroup.Builder(VALID_NAME_TENNIS)
                .build();
        assertEquals(jsonAdaptedGroup, jsonAdaptedGroup);
    }
}
