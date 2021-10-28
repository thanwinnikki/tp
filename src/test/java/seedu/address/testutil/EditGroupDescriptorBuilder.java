package seedu.address.testutil;

import seedu.address.logic.commands.EditGroupCommand.EditGroupDescriptor;
import seedu.address.model.common.Description;
import seedu.address.model.common.Name;
import seedu.address.model.group.Group;

/**
 * A utility class to help with building EditGroupDescriptor objects.
 */
public class EditGroupDescriptorBuilder {
    private EditGroupDescriptor descriptor;

    public EditGroupDescriptorBuilder() {
        descriptor = new EditGroupDescriptor();
    }

    public EditGroupDescriptorBuilder(EditGroupDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Returns an {@code EditGroupDescriptor} with fields containing {@code group}'s details
     */
    public EditGroupDescriptorBuilder(Group group) {
        descriptor = new EditGroupDescriptor();
        descriptor.setName(group.getName());
        descriptor.setDescription(group.getDescription());
    }

    /**
     * Sets the {@code Name} of the {@code EditGroupDescriptor} that we are building.
     */
    public EditGroupDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditGroupDescriptor} that we are building.
     */
    public EditGroupDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditGroupDescriptor build() {
        return descriptor;
    }

}
