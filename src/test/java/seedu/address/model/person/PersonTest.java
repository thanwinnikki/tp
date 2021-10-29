package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.common.Name;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    private static final Name AMY_NAME = new Name(VALID_NAME_AMY);
    private static final Phone AMY_PHONE = new Phone(VALID_PHONE_AMY);
    private static final Email AMY_EMAIL = new Email(VALID_EMAIL_AMY);
    private static final Address AMY_ADDRESS = new Address(VALID_ADDRESS_AMY);
    private static final Set<Tag> AMY_TAGS = new HashSet<>();

    static {
        AMY_TAGS.add(new Tag(VALID_TAG_FRIEND));
    }

    @Test
    public void withAddress_nonNullAddress_successfullyProducesEqualAddress() {
        // Equivalence Partition {address}: Non-null address
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS)
                .build();

        Address addressAfterBuild = person.getAddress();
        assertNotEquals(null, addressAfterBuild);
        assertEquals(AMY_ADDRESS, addressAfterBuild);
    }

    @Test
    public void withAddress_nullAddress_successfullyStoresNull() {
        // Equivalence Partition {address}: Null address
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(null)
                .withTags(AMY_TAGS)
                .build();

        Address addressAfterBuild = person.getAddress();
        assertEquals(null, addressAfterBuild);
    }

    @Test
    public void withAddress_notCalled_successfullyStoresNull() {
        // Equivalence Partition {address}: Method not called
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withTags(AMY_TAGS)
                .build();

        Address addressAfterBuild = person.getAddress();
        assertEquals(null, addressAfterBuild);
    }

    @Test
    public void withTags_nonNullTags_successfullyProducesEqualTags() {
        // Equivalence Partition {tags}: Non-null tags
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS)
                .build();

        Set<Tag> tagsAfterBuild = person.getTags();
        assertNotEquals(null, tagsAfterBuild);
        assertEquals(AMY_TAGS, tagsAfterBuild);
    }

    @Test
    public void withTags_nullTags_successfullyCreatesEmptySet() {
        // Equivalence Partition {tags}: Null tags
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(null)
                .build();

        Set<Tag> tagsAfterBuild = person.getTags();
        assertEquals(new HashSet<>(), tagsAfterBuild);
    }

    @Test
    public void withTags_notCalled_successfullyCreatesEmptySet() {
        // Equivalence Partition {address}: Method not called
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .build();

        Set<Tag> tagsAfterBuild = person.getTags();
        assertEquals(new HashSet<>(), tagsAfterBuild);
    }

    @Test
    public void build_validCompulsoryFields_successfullyProducesEqualFields() {
        // Equivalence Partition {name, phone, email}: Valid non-null fields for builder constructor
        Person person = new Person.Builder(AMY_NAME, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS)
                .build();

        Name nameAfterBuild = person.getName();
        assertEquals(AMY_NAME, nameAfterBuild);

        Phone phoneAfterBuild = person.getPhone();
        assertEquals(AMY_PHONE, phoneAfterBuild);

        Email emailAfterBuild = person.getEmail();
        assertEquals(AMY_EMAIL, emailAfterBuild);
    }

    @Test
    public void build_nullName_throwsNullPointerException() {
        // Equivalence Partition {name}: Null name
        Person.Builder personBuilder = new Person.Builder(null, AMY_PHONE, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS);

        assertThrows(NullPointerException.class, () -> personBuilder.build());
    }

    @Test
    public void build_nullPhone_throwsNullPointerException() {
        // Equivalence Partition {phone}: Null phone
        Person.Builder personBuilder = new Person.Builder(AMY_NAME, null, AMY_EMAIL)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS);

        assertThrows(NullPointerException.class, () -> personBuilder.build());
    }

    @Test
    public void build_nullEmail_throwsNullPointerException() {
        // Equivalence Partition {email}: Null email
        Person.Builder personBuilder = new Person.Builder(AMY_NAME, AMY_PHONE, null)
                .withAddress(AMY_ADDRESS)
                .withTags(AMY_TAGS);

        assertThrows(NullPointerException.class, () -> personBuilder.build());
    }

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
