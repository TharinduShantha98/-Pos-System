package controller;


import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.Impl.CustomerBOImpl;
import bo.custom.Impl.ItemBOImpl;
import bo.custom.Impl.OrderBOImpl;
import bo.custom.ItemBO;
import bo.custom.OrderBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.CustomerDTO;
import model.ItemDTO;
import model.ItemDetail;

import model.OrderDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import views.TM.CartTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrderFormController {


    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<String> cmbCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtDescription;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtDiscount;
    public JFXTextField txtQty;
    public TableView<CartTM> tblCartTM;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTotal;
    public Label orderId;
    public JFXButton btnPlaceOrder;
    public Label orderId1;
    public Label lbltotal2;
    public Label lblDiscount;
    public JFXTextField txtPay;
    public Label lblBalance;
    public JFXButton btnBack;

    private  final OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    private  final ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);



    double unitDiscount;
    double total;
    int cartRow = -1;

    public void initialize(){

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        btnPlaceOrder.setDisable(true);


        setDateAndTime();


        try {
            setCmbCustomerId();
            setCmbItemCode();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setLabelOrderId();
        } catch (SQLException |ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerDetail(newValue);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
               setItemDetail(newValue);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        tblCartTM.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                cartRow = (int) newValue;
        });

        lblTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("0.00/=")){

            }else{
                btnPlaceOrder.setDisable(false);
            }
        });



    }




    private void setDateAndTime(){
        new DateAndTime().setTimeAndData(lblDate,lblTime);
    }

    private void setCmbCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerId = customerBO.getCustomerId();
        cmbCustomerId.getItems().addAll(customerId);

    }
    private  void setCmbItemCode() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemCode = itemBO.getItemCode();
        cmbItemCode.getItems().addAll(itemCode);

    }


    private void setCustomerDetail(String cusId) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = customerBO.customerSearch(cusId);
        txtCustomerName.setText(c1.getCustomerName());
        txtCustomerAddress.setText(c1.getCustomerAddress());
    }

    private void setItemDetail(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO item = itemBO.searchItem(itemCode);
        txtDescription.setText(item.getDescription());
        txtPackSize.setText(item.getPackSize());
        txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        txtDiscount.setText(String.valueOf(item.getItemDiscount()));
    }



    ObservableList<CartTM> cartTMObservableList = FXCollections.observableArrayList();
    public void addToCartOnAction(ActionEvent actionEvent) {
        String discount =  txtDiscount.getText();
        String[] parts = discount.split("%");
        String part1 = parts[0];
        double discount1 = Double.parseDouble(String.valueOf(part1));

        unitDiscount = (Double.parseDouble(txtUnitPrice.getText())) * discount1/100;

        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        total = (qty * unitPrice) - (qty * unitDiscount);

        if(Integer.parseInt(txtQtyOnHand.getText())> qty) {

            CartTM cartTM = new CartTM(cmbItemCode.getValue(),
                    txtDescription.getText(),
                    txtPackSize.getText(),
                    Integer.parseInt(txtQty.getText()),
                    Double.parseDouble(txtUnitPrice.getText()),
                    unitDiscount * Integer.parseInt(txtQty.getText()),
                    total);
            int i = isExists(cartTM);
            if(i == -1){
                cartTMObservableList.add(cartTM);
            }else{
                CartTM temp =  cartTMObservableList.get(i);
                CartTM newCartTM = new CartTM( temp.getItemCode(),
                        temp.getDescription(),
                        temp.getPackSize(),
                        temp.getQty() +qty,
                        temp.getUnitPrice(),
                        temp.getDiscount() + (qty* unitDiscount),
                        temp.getTotal() +total
                );

                cartTMObservableList.add(newCartTM);
                cartTMObservableList.remove(i);

            }

            tblCartTM.setItems(cartTMObservableList);
            tblCartTM.refresh();
            calculator();
        }else {
            new Alert(Alert.AlertType.WARNING,"Invalid Qty").show();
        }
    }


    private  int isExists(CartTM cartTM){
        for(int i =0; i < cartTMObservableList.size();i++){
            if(cartTM.getItemCode().equalsIgnoreCase(cartTMObservableList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }






    private  void calculator(){
        double total = 0;
        for(int i =0; i < cartTMObservableList.size();i++){
            total = total + cartTMObservableList.get(i).getTotal();
        }
        lblTotal.setText(String.valueOf(total) + "/=");
        Cal();
        lbltotal2.setText(String.valueOf(total));
    }
    private  void Cal(){
        double discount =0;
        for(int i =0; i < cartTMObservableList.size();i++){
            discount = discount + cartTMObservableList.get(i).getDiscount();
        }
        lblDiscount.setText(String.valueOf(discount));



    }







    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
            ArrayList<ItemDetail> items = new ArrayList<>();
        Double total1 = 0.0;
        for (CartTM temp:cartTMObservableList
             ) {
            total1 = total1 + temp.getTotal();
            items.add(new ItemDetail(temp.getItemCode(),
                    temp.getUnitPrice(),temp.getQty(),
                    temp.getDiscount(), temp.getTotal()));
        }

        OrderDTO order = new OrderDTO(orderId.getText(),
                lblDate.getText(),
                lblTime.getText(),
                cmbCustomerId.getValue(),
                "E-001",
                total1,
                items);

        if(orderBO.setOrderDetail(order)){
            createInvoice();
            new Alert(Alert.AlertType.CONFIRMATION,"Success ").show();
            setLabelOrderId();




        }else{
            new Alert(Alert.AlertType.WARNING,"Not Success").show();
        }

    }

    public void ClearTableDataOnAction(ActionEvent actionEvent) {
        if(cartRow == -1){
           new Alert(Alert.AlertType.WARNING,"select row").show();
        }else{
            cartTMObservableList.remove(cartRow);
            calculator();
            tblCartTM.refresh();
        }
    }


    public void createInvoice(){
        try {
            JasperDesign load = JRXmlLoader.load(this.getClass().getResourceAsStream("../views/reports/superMarketBill.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            HashMap map = new HashMap<>();
            String oid = orderId.getText();
            map.put("orderId",oid);
            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, DbConnection.getDbConnection().getConnection());
            JasperViewer.viewReport(jasperPrint,false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }



    
    private  void setLabelOrderId() throws SQLException, ClassNotFoundException {
        orderId.setText(orderBO.getOrderId());
    }

    public void bilpaymentOnAction(ActionEvent actionEvent) {
        if(txtPay.getText().equalsIgnoreCase(null)){

        }else{
            double balance = Double.parseDouble(txtPay.getText()) - Double.parseDouble(lbltotal2.getText());
            lblBalance.setText(String.valueOf(balance));
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
