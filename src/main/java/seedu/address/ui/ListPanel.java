package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing panel's state as well as the list of listable entities, such as persons, or groups.
 */
public class ListPanel extends UiPart<Region> {
    /**
     * PanelState indicates what the panel is containing, such as persons or groups.
     */
    public enum PanelState {
        PERSONS ("Persons"),
        GROUPS ("Groups"),
        GROUP_MATES ("Group Mates"),
        TASKS ("Tasks");

        private final String description;
        PanelState(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return String.format(description);
        }
    }

    private static final String FXML = "ListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ListPanel.class);

    @FXML
    private Label stateDisplay;

    @FXML
    private ListView<Listable> listableListView;

    /**
     * Creates a {@code ListPanel} with the given {@code ObservableList}.
     */
    public ListPanel() {
        super(FXML);
    }

    /**
     * Sets the state of the panel that is currently displayed on the UI.
     * @param panelState
     */
    public void setState(PanelState panelState) {
        requireNonNull(panelState);
        stateDisplay.setText(panelState.toString());
    }

    /**
     * Sets the list of listable model entities that are currently displayed on the UI.
     * Listable model entities are model entities that are intended to be displayed on the list panel, like a Person or
     * a Group.
     *
     * @param list The list of listable model entity instances.
     * @param <T> The type of the listable model entity.
     */
    public <T> void setList(ObservableList<T> list) {
        ObservableList<Listable> listableList = list.stream()
                .map(Listable::convertModelEntityToListable)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        listableListView.setItems(listableList);
        listableListView.setCellFactory(listView -> new ListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Listable} using a {@code Card}.
     */
    class ListViewCell extends ListCell<Listable> {
        @Override
        protected void updateItem(Listable listable, boolean empty) {
            super.updateItem(listable, empty);

            if (empty || listable == null) {
                setGraphic(null);
                setText(null);
            } else {
                int displayedIndex = getIndex() + 1;
                Card card = listable.getCard(displayedIndex);
                setGraphic(card.getRoot());
            }
        }
    }

}
