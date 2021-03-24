package org.openjfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.openjfx.data_collection.ComponentsCollection;
import org.openjfx.data_models.PCComponents;
import org.openjfx.data_validator.NumberConversion;
import org.openjfx.file_utilities.FileHandlers.FileActions;
import org.openjfx.gui_utilities.Dialogs;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAdmin implements Initializable {

    @FXML private TableView<PCComponents> tableView;
    @FXML private TableColumn<PCComponents, Integer> numberColumn;
    @FXML private TableColumn<PCComponents, String> nameColumn;
    @FXML private TableColumn<PCComponents, String> typeColumn;
    @FXML private TableColumn<PCComponents, String> specsColumn;
    @FXML private TableColumn<PCComponents, Double> priceColumn;
    @FXML private TextField searchInput;
    @FXML private TextField cNumber;
    @FXML private TextField cName;
    @FXML private TextField price;
    @FXML private TextArea cDesc;
    @FXML private ComboBox<String> typeOptions;
    private final FileActions<PCComponents> file = new FileActions<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Opens a file containing the default lists of components.
        file.open("initialComponents.txt", "Loading system data...");
        // Initializes the tableview.
        ComponentsCollection.setTableView(tableView);
        // Fills the component type combobox with values.
        ComponentsCollection.fillCombobox_TYPE(typeOptions);
        // Whenever there is a new component type, they are added to the component type combobox
        ComponentsCollection.fillCombobox_TYPE_listOnChanged(typeOptions, typeColumn);

        // Initializes all tableview columns to be editable.
        numberColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberConversion.StringToInteger()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        specsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberConversion.StringToDouble()));
    }

    /** Adds a new component to the tableview. */

    @FXML
    void addComponent() {
        String componentNumber = cNumber.getText();
        String componentName = cName.getText();
        String componentSpecs = cDesc.getText();
        String componentPrice = price.getText();
        String componentType = typeOptions.getValue();

        try {
            PCComponents c = new PCComponents(componentNumber, componentName, componentType, componentSpecs, componentPrice);
            ComponentsCollection.addToCollection(c);
            resetFields();

            Dialogs.showSuccessDialog("Component Added Successfully!");
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(), "");
        }
    }

    /** Resets all input fields after successful addition of components and refreshes the tableview. */

    void resetFields() {
        cName.setText("");
        cDesc.setText("");
        price.setText("");
        tableView.refresh();
    }

    /** The following methods are used to validate component data that is directly edited on the tableview. */

    @FXML
    void numberOnEdit(TableColumn.CellEditEvent<PCComponents, Integer> event) {
        try {
            event.getRowValue().setComponentNumber(Integer.toString(event.getNewValue()));
            tableView.refresh();
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(),"");
            tableView.refresh();
        } catch (NullPointerException ignored) {
            tableView.refresh();
        }
    }

    @FXML
    void nameOnEdit(TableColumn.CellEditEvent<PCComponents, String> event) {
        try {
            event.getRowValue().setComponentName(event.getNewValue());
            tableView.refresh();
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(),"");
            tableView.refresh();
        }
    }

    @FXML
    void typeOnEdit(TableColumn.CellEditEvent<PCComponents, String> event) {
        try {
            event.getRowValue().setComponentType(event.getNewValue());
            tableView.refresh();
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(),"");
            tableView.refresh();
        }
    }

    @FXML
    void specsOnEdit(TableColumn.CellEditEvent<PCComponents, String> event) {
        try {
            event.getRowValue().setComponentSpecs(event.getNewValue());
            tableView.refresh();
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(),"");
            tableView.refresh();
        }
    }

    @FXML
    void priceOnEdit(TableColumn.CellEditEvent<PCComponents, Double> event) {
        try {
            event.getRowValue().setComponentPrice(Double.toString(event.getNewValue()));
            tableView.refresh();
        } catch (IllegalArgumentException e) {
            Dialogs.showWarningDialog(e.getMessage(),"");
            tableView.refresh();
        } catch (NullPointerException ignored) {
            tableView.refresh();
        }
    }

    /** search() - searches through the tableview with the given search word. */

    @FXML
    void search() {
        throw new UnsupportedOperationException("Method not yet implemented");
    }
}
