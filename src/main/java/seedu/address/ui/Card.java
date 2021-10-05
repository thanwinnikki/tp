package seedu.address.ui;

import javafx.scene.layout.Region;

abstract class Card extends UiPart<Region> {

    Card(String fxmlFileName) {
        super(fxmlFileName);
    }
}
