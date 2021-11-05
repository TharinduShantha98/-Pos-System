package controller;

import bo.BOFactory;
import bo.custom.Impl.ItemBOImpl;
import bo.custom.ItemBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.ItemDTO;
import views.TM.ItemTM;

import java.sql.SQLException;

public class AllItemFormController {
    public TableView<ItemTM> tblAllItems;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colBuyIngPrice;
    public TableColumn colSaleUnitPrice;
    public TableColumn colQty;
    public TableColumn colItemDiscount;

    private final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colBuyIngPrice.setCellValueFactory(new PropertyValueFactory<>("buyIngPrice"));
        colSaleUnitPrice.setCellValueFactory(new PropertyValueFactory<>("SaleUnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colItemDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        try {
            setTblAllItems();
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setTblAllItems() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTM> tmObservableList = FXCollections.observableArrayList();

        for(ItemDTO item : itemBO.getAllItems()){
            ItemTM itemTM = new ItemTM(
                    item.getItemCode(),
                    item.getDescription(),item.getPackSize(),
                    String.valueOf(item.getBuyingPrice()),
                    String.valueOf(item.getUnitPrice()),
                    String.valueOf(item.getItemDiscount()),
                    String.valueOf(item.getQtyOnHand()));
            tmObservableList.add(itemTM);
        }
        tblAllItems.setItems(tmObservableList);


    }



}
