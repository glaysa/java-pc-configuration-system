package org.openjfx.controllers.popupControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.openjfx.dataCollection.ComponentsCartCollection;
import org.openjfx.dataCollection.ConfigurationCollection;
import org.openjfx.dataModels.PCComponents;
import org.openjfx.guiUtilities.popupDialogs.PopupUtilities;

/** This controller is used for the popup window of components when being shown. */

public class PopupShowComponentController{

    @FXML private AnchorPane parentPane;
    @FXML private Button button;
    @FXML private Label labelName;
    @FXML private Label labelId;
    @FXML private Label labelType;
    @FXML private Label labelPrice;
    @FXML private Label labelDescription;

    /** Display the data */
    public void setComponentToShow(PCComponents componentToShow){
        labelId.setText(Integer.toString(componentToShow.getPCComponentID()));
        labelName.setText(componentToShow.getComponentName());
        labelType.setText(componentToShow.getComponentType());
        labelPrice.setText(Double.toString(componentToShow.getComponentPrice()));
        labelDescription.setText(componentToShow.getComponentSpecs());
    }

    /** Different buttons are assigned depending on which tableview the user is on */

    public void setButtonAsAddToCart(PCComponents componentToShow){
        button.setText("Add to cart");
        button.setOnAction((e) -> {
            ComponentsCartCollection.addToCollection(componentToShow);
            PopupUtilities.closePopup(parentPane);
        });
    }

    public void setButtonAsRemoveFromCart(PCComponents toRemove){
        button.setText("Remove from Cart");
        button.setOnAction((e) -> {
            ComponentsCartCollection.removeSelected(toRemove);
            PopupUtilities.closePopup(parentPane);
        });
    }

    public void setButtonAsSelect(PCComponents selected){
        button.setText("Select");
        button.setOnAction((e) -> {
            ConfigurationCollection.addConfigurationItem(selected);
            PopupUtilities.closePopup(parentPane);
        });
    }

    public void hideButton(){
        button.setDisable(true);
        button.setOpacity(0);
    }
}