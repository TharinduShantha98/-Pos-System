package controller;

import bo.BOFactory;
import bo.custom.Impl.ItemBOImpl;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.ItemDTO;


import java.sql.SQLException;

public class AddItemsFormController {

    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtSize;
    public JFXTextField txtBuyPrice;
    public JFXTextField txtSaleUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtItemDiscount;
    Double discount1;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){
        //setDiscount();
    }

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemDTO item = new ItemDTO(txtItemCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtBuyPrice.getText()),
                Double.parseDouble(txtSaleUnitPrice.getText()),
                txtItemDiscount.getText(),
                Integer.parseInt(txtQty.getText()));

        if(itemBO.addItems(item)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved Item ").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION," Not Saved Item ").show();
        }
    }
}
