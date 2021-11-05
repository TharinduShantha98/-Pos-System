package controller;

import bo.BOFactory;
import bo.custom.Impl.ItemBOImpl;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import model.ItemDTO;

import java.sql.SQLException;

public class RemoveItemFormController {
    public JFXTextField txtItemCode;
    public Label lblDescription;
    public Label lblPackSize;
    public Label lblBuyingPrice;
    public Label lblSaleUnitPrice;
    public Label lblQty;
    public Label lblItemDiscount;


    private  final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO item = itemBO.searchItem(txtItemCode.getText());
        if(item != null){
            txtItemCode.setText(item.getItemCode());
            lblDescription.setText(item.getDescription());
            lblPackSize.setText(item.getPackSize());
            lblBuyingPrice.setText(String.valueOf(item.getBuyingPrice()));
            lblSaleUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            lblItemDiscount.setText(item.getItemDiscount());
            lblQty.setText(String.valueOf(item.getQtyOnHand()));

        }else{
            new Alert(Alert.AlertType.WARNING,"Not set").show();
        }        
        
    }

    public void deleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if(itemBO.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Success ").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Not success").show();
        }
    }
}
