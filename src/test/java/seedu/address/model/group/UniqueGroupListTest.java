package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2101_GROUP_BUILDER;
import static seedu.address.testutil.TypicalGroups.CS2103T_GROUP_BUILDER;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.testutil.GroupBuilder;

public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();
    private final Group cs2103t = CS2103T_GROUP_BUILDER.build();
    private final Group cs2101 = CS2101_GROUP_BUILDER.build();

    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(CS2103T_GROUP_BUILDER.build()));
    }

    @Test
    public void contains_groupInList_returnsTrue() {

        uniqueGroupList.add(CS2103T_GROUP_BUILDER.build());
        assertTrue(uniqueGroupList.contains(CS2103T_GROUP_BUILDER.build()));
    }

    @Test
    public void contains_groupWithSameNameField_returnsTrue() {
        uniqueGroupList.add(cs2103t);
        Group editedCS2103T = new GroupBuilder(cs2103t).withMembers(CARL).build();
        assertTrue(uniqueGroupList.contains(editedCS2103T));
    }

    @Test
    public void add_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.add(null));
    }

    @Test
    public void add_duplicateGroup_throwsDuplicateGroupException() {
        uniqueGroupList.add(cs2103t);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(cs2103t));
    }

    @Test
    public void setGroup_nullTargetGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(null, cs2103t));
    }

    @Test
    public void setPerson_nullEditedGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroup(cs2103t, null));
    }

    @Test
    public void setPerson_targetGroupNotInList_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.setGroup(cs2103t, cs2103t));
    }

    @Test
    public void setGroup_editedGroupIsSameGroup_success() {
        uniqueGroupList.add(cs2103t);
        uniqueGroupList.setGroup(cs2103t, cs2103t);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(cs2103t);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasSameIdentity_success() {
        uniqueGroupList.add(cs2103t);
        Group editedGroup = new GroupBuilder(cs2103t).withMembers(CARL).build();
        uniqueGroupList.setGroup(cs2103t, editedGroup);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(editedGroup);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasDifferentIdentity_success() { //todo changes after Yeji merge
        uniqueGroupList.add(cs2103t);
        uniqueGroupList.setGroup(cs2103t, cs2101);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(cs2101);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroup_editedGroupHasNonUniqueIdentity_throwsDuplicatePersonException() { //todo changes after Yeji
        uniqueGroupList.add(cs2103t);
        uniqueGroupList.add(cs2101);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroup(cs2103t, cs2101));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove(null));
    }

    @Test
    public void remove_groupDoesNotExist_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(cs2103t));
    }

    @Test
    public void remove_existingGroup_removesGroup() {
        uniqueGroupList.add(cs2103t);
        uniqueGroupList.remove(cs2103t);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullUniqueGroupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((UniqueGroupList) null));
    }

    @Test
    public void setGroups_uniqueGroupList_replacesOwnListWithProvidedUniqueGroupList() { //todo change
        uniqueGroupList.add(cs2103t);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(cs2101);
        uniqueGroupList.setGroups(expectedUniqueGroupList);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.setGroups((List<Group>) null));
    }

    @Test
    public void setGroups_list_replacesOwnListWithProvidedList() { // todo change
        uniqueGroupList.add(cs2103t);
        List<Group> groupList = Collections.singletonList(cs2101);
        uniqueGroupList.setGroups(groupList);
        UniqueGroupList expectedUniqueGroupList = new UniqueGroupList();
        expectedUniqueGroupList.add(cs2101);
        assertEquals(expectedUniqueGroupList, uniqueGroupList);
    }

    @Test
    public void setGroups_listWithDuplicateGroups_throwsDuplicateGroupException() {
        List<Group> listWithDuplicateGroups = Arrays.asList(cs2103t, cs2103t);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroups(listWithDuplicateGroups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueGroupList.asUnmodifiableObservableList().remove(0));
    }

}
