package controller;

import bo.BOFactory;
import bo.custom.Impl.ItemBOImpl;

import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.ItemDTO;
import java.sql.SQLException;
public class UpdateItemFormController {


    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtSize;
    public JFXTextField txtBuyPrice;
    public JFXTextField txtSaleUnitPrice;
    public JFXTextField txtQty;
    public JFXTextField txtItemDiscount;


    private  final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void searchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO item = itemBO.searchItem(txtItemCode.getText());
        if(item != null){
            txtItemCode.setText(item.getItemCode());
            txtDescription.setText(item.getDescription());
            txtSize.setText(item.getPackSize());
            txtBuyPrice.setText(String.valueOf(item.getBuyingPrice()));
            txtSaleUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            txtItemDiscount.setText(item.getItemDiscount());
            txtQty.setText(String.valueOf(item.getQtyOnHand()));

        }else{
            new Alert(Alert.AlertType.WARNING,"Not set").show();
        }



    }


    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO item = new ItemDTO(txtItemCode.getText(),
                txtDescription.getText(),
                txtSize.getText(),
                Double.parseDouble(txtBuyPrice.getText()),
                Double.parseDouble(txtSaleUnitPrice.getText()),
                txtItemDiscount.getText(),
                Integer.parseInt(txtQty.getText()));

        if(itemBO.updateItem(item)){
            new Alert(Alert.AlertType.CONFIRMATION," successful").show();
        }else{

            new Alert(Alert.AlertType.WARNING,"Not successful").show();
        }



    }
}
