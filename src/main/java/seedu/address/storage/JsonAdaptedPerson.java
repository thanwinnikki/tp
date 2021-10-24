package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.common.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
@JsonDeserialize(builder = JsonAdaptedPerson.Builder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String id;
    private final List<JsonAdaptedTag> tagged;

    /**
     * Builder class for {@code JsonAdaptedPerson}.
     */
    public static class Builder {

        private String name;
        private String phone;
        private String email;
        private String address;
        private String id;
        private List<JsonAdaptedTag> tagged;

        /**
         * Constructs a {@code JsonAdaptedPerson.Builder} for a {@code JsonAdaptedPerson} with the given person details.
         *
         * @param name The name of the person.
         * @param phone The phone number of the person.
         * @param email The email address of the person.
         * @param address The physical address of the person.
         */
        @JsonCreator
        public Builder(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                @JsonProperty("email") String email, @JsonProperty("address") String address) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.address = address;
        }

        /**
         * Starts converting a given {@code Person} into a {@code JsonAdaptedPerson} for Jackson use.
         *
         * @param source The given {@code Person}.
         */
        public Builder(Person source) {
            name = source.getName().fullName;
            phone = source.getPhone().value;
            email = source.getEmail().value;
            address = source.getAddress().value;
            initialiseTagged(source);
        }

        /**
         * Completes the {@code JsonAdaptedPerson} being built by this {@code JsonAdaptedPerson.Builder}.
         *
         * @return The completed {@code JsonAdaptedPerson} object.
         */
        public JsonAdaptedPerson build() {
            return new JsonAdaptedPerson(name, phone, email, address, id, tagged);
        }

        /**
         * Includes the given ID.
         *
         * @param id The ID to be included.
         * @return This {@code JsonAdaptedPerson.Builder} instance.
         */
        public Builder withId(Id id) {
            this.id = id.toString();
            return this;
        }

        /**
         * Includes the given ID.
         *
         * @param id The ID to be included.
         * @return This {@code JsonAdaptedPerson.Builder} instance.
         */
        @JsonProperty
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Includes the given tags.
         *
         * @param tagged The list of tags to be included.
         * @return This {@code JsonAdaptedPerson.Builder} instance.
         */
        @JsonProperty
        public Builder withTagged(List<JsonAdaptedTag> tagged) {
            if (tagged != null) {
                this.tagged = new ArrayList<>();
                this.tagged.addAll(tagged);
            }
            return this;
        }

        private void initialiseTagged(Person source) {
            Set<Tag> tags = source.getTags();
            if (tags.isEmpty()) {
                return;
            }
            tagged = new ArrayList<>();
            tagged.addAll(tags.stream()
                    .map(JsonAdaptedTag::new)
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    private JsonAdaptedPerson(String name, String phone, String email, String address, String id,
                List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.id = id;
        this.tagged = tagged;
    }

    public boolean hasId() {
        return id != null;
    }

    public Id getId() throws IllegalValueException {
        assert hasId() : "This JsonAdaptedPerson has no ID.";
        return Id.parse(id);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        if (tagged != null) {
            for (JsonAdaptedTag tag : tagged) {
                personTags.add(tag.toModelType());
            }
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }

}
