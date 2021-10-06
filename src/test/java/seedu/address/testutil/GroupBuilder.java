package seedu.address.testutil;

import seedu.address.model.group.Group;
import seedu.address.model.group.Name;
import seedu.address.model.person.Person;

public class GroupBuilder {

    private Name name;
    private String member;

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    public GroupBuilder withMember(String member) {
        this.member = member;
        return this;
    }

    public Group build() {
        return new Group();
    }
}
