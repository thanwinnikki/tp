package seedu.address.ui;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Listable> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel() {
        super(FXML);
    }

    public <T> void setList(ObservableList<T> list) {
        ObservableList<Listable> listableList = list.stream()
                .map(Listable::convertModelEntityToListable)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        personListView.setItems(listableList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Listable> {
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
