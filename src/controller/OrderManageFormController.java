package controller;


import bo.BOFactory;
import bo.custom.Impl.OrderManageBOImpl;
import bo.custom.OrderManageBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.Impl.OrderDetailDaoImpl;
import dao.custom.OrderDetailDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import views.TM.OrderIdTM;
import views.TM.OrderManageCartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class OrderManageFormController {


    public TableView<OrderIdTM> tblOrderIdTable;
    public TableColumn colOrderId;
    public TableView<OrderManageCartTM> tblItemDetail;
    public Label lblTotal;
    public TableColumn colItemCode;
    public TableColumn colOrderQty;
    public TableColumn colItemDiscount;
    public TableColumn colCost;
    public Label lblOrderId;
    public Label lblItemCode;
    public Label lblItemDiscount;
    public Label lblOrderQty;
    public JFXTextField txtUpdateQty;
    public JFXButton btnBack;


    OrderManageBO orderManageBO = (OrderManageBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_MANAGE);
    OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);



    String qty;
    String discount;
    String totalCost;

    String orderId;

    public void initialize()  {
            colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
            colItemDiscount.setCellValueFactory(new PropertyValueFactory<>("itemDiscount"));
            colCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));



        try {
            setTblOrderIdTable();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        tblOrderIdTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            orderId = newValue.getOrderId();
            lblOrderId.setText(orderId);


        });

        lblOrderId.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("O-000")){

            }else{
                try {
                    setTblItemDetail();
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            }


        });

        tblItemDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try{
                if(newValue != null) {
                    itemCode = newValue.getItemCode();
                    discount = (String.valueOf(newValue.getItemDiscount()));
                    qty = (String.valueOf(newValue.getOrderQty()));
                    totalCost = (String.valueOf(newValue.getTotalCost()));
                }else{

                }
            }catch (Exception e){
                System.out.println("null point exception ekka awooo");
            }


        });



    }
    public void setTblItemDetail() throws SQLException, ClassNotFoundException {
        ObservableList<OrderManageCartTM> orderManageCartTMS = FXCollections.observableArrayList();
        for ( OrderManageCartTM temp : orderDetailDao.selectItem(orderId)) {
            orderManageCartTMS.add(new OrderManageCartTM(temp.getItemCode(),temp.getOrderQty(),
                    temp.getItemDiscount(), temp.getTotalCost()));

        }


       tblItemDetail.setItems(orderManageCartTMS);
    }









    public void setTblOrderIdTable() throws SQLException, ClassNotFoundException {
        ObservableList<OrderIdTM>  stringObservableList = FXCollections.observableArrayList();

        for (String temp: orderManageBO.getAllOrderId())
              {
            stringObservableList.add(new OrderIdTM(temp));
        }
        tblOrderIdTable.setItems(stringObservableList);
    }


    public void setFiledOnAction(ActionEvent actionEvent) {

            lblItemCode.setText(itemCode);
            lblOrderQty.setText(qty);
            lblItemDiscount.setText(discount);
            lblTotal.setText(totalCost);






    }




    double oneItemForPrice;
    double oneItemForDiscount;
    int newChangeQty;
    String itemCode;
    String getOrderId;
    int oldQty;


    private  void calculationForOderModify(){

        try {
            if(totalCost.equalsIgnoreCase(null) && discount.equalsIgnoreCase(null)){

            }else {
                oneItemForPrice = Double.parseDouble(totalCost) / Integer.parseInt(qty);
                oneItemForDiscount = Double.parseDouble(discount) / Integer.parseInt(qty);

                getOrderId = lblOrderId.getText();
                oldQty = Integer.parseInt(lblOrderQty.getText());


                System.out.println(itemCode);
                System.out.println(oldQty);
                System.out.println(orderId);
                System.out.println(oneItemForDiscount);
                System.out.println(oneItemForPrice);
            }

           /* if(txtQty.getText().equalsIgnoreCase(null)){

            }else{
                newChangeQty = Integer.parseInt(txtQty.getText());
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    public void modifyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        calculationForOderModify();

        if(orderManageBO.changeItemQty(itemCode,getOrderId,Integer.parseInt(txtUpdateQty.getText()),oldQty,oneItemForPrice)){
            new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Not success").show();
        }



    }


    public void backToMainPage(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) btnBack.getScene().getWindow();
        stage1.close();

        URL resource  = getClass().getResource("../views/NewDashBordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Administrator form");
        stage.show();
    }
}
