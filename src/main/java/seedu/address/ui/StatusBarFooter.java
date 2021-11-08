package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.group.Group;

/**
 * A UI for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String MESSAGE_HOME_STATE = "Home";
    private static final String MESSAGE_GROUP_STATE = "Group: %s";

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label applicationState;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

    /**
     * Adjusts the UI accordingly for the Home page/application state.
     */
    public void changeDisplayForHomeAppState() {
        applicationState.setText(MESSAGE_HOME_STATE);
    }

    /**
     * Adjusts the UI accordingly for the Group Information page/application state.
     */
    public void changeDisplayForGroupInformationAppState(Group group) {
        applicationState.setText(String.format(MESSAGE_GROUP_STATE, group));
    }
}
