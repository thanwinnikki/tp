package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * A UI component that displays information of a {@code Listable}.
 */
abstract class Card extends UiPart<Region> {

    /**
     * Creates a {@code Card} with the given FXML file.
     */
    Card(String fxmlFileName) {
        super(fxmlFileName);
    }
}
