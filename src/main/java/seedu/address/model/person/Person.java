package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.common.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: identity fields are present and not null, present field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags;

    /**
     * Builder class for {@code Person}.
     */
    public static class Builder {

        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        /**
         * Constructs a {@code Person.Builder} for a {@code Person} with the given attributes.
         *
         * @param name The name of the person.
         * @param phone The phone number of the person.
         * @param email The email address of the person.
         */
        public Builder(Name name, Phone phone, Email email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        /**
         * Finishes building the {@code Person}.
         *
         * @return The person being built.
         */
        public Person build() {
            return new Person(name, phone, email, address, tags);
        }

        /**
         * Stores the given address.
         *
         * @param address The address to be stored.
         * @return This {@code Person.Builder} instance.
         */
        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        /**
         * Assigns the given tags to the person being built.
         * This replaces any current tags if they exist; it does not add on to them.
         *
         * @param tags The tags to be assigned.
         * @return This {@code Person.Builder} instance.
         */
        public Builder withTags(Set<Tag> tags) {
            this.tags = tags;
            return this;
        }
    }

    /**
     * Constructs a Person object.
     * Identity fields must be present and not null.
     *
     * @param name Name of the person.
     * @param phone Phone number of the person.
     * @param email Email address of the person.
     * @param address Address of the person.
     * @param tags Tags of the person.
     */
    private Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags = new HashSet<>();
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns the address, or null if there is no address saved.
     *
     * @return The address, or null if there is no address saved.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Only checks if 2 persons have the same name or are the same person.
     *
     * @param otherPerson Other person to compare.
     * @return Returns true if both persons have the same name or are the same person and false otherwise.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        boolean haveSameNames = otherPerson.getName().equals(getName());
        boolean haveSamePhones = otherPerson.getPhone().equals(getPhone());
        boolean haveSameEmails = otherPerson.getEmail().equals(getEmail());
        boolean haveNullAddresses = otherPerson.getAddress() == null && getAddress() == null;
        boolean haveSameAddresses = haveNullAddresses
                || (otherPerson.getAddress() != null && otherPerson.getAddress().equals(getAddress()));
        assert otherPerson.getTags() != null && getTags() != null : "Tags should have been initialised as a set.";
        boolean haveSameTags = otherPerson.getTags().equals(getTags());
        return haveSameNames && haveSamePhones && haveSameEmails && haveSameAddresses && haveSameTags;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        if (address != null) {
            builder.append("; Address: ")
                    .append(getAddress());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
