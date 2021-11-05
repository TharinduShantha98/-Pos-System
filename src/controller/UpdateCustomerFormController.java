package controller;

import Validation.Validations;
import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.Impl.CustomerBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UpdateCustomerFormController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerTittle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtPostalCode;
    public JFXTextField txtProvince;
    public JFXButton btnUpdateCustomer;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern customerId = Pattern.compile("(C-)[0-1]{3}");
    Pattern customerTittle = Pattern.compile("^[A-z]+$");
    Pattern customerName = Pattern.compile("^[A-z]+$");
    Pattern customerAddress = Pattern.compile("^[A-z0-9-]+$");
    Pattern customerCity = Pattern.compile("^[A-z]+$");
    Pattern customerPostalCode = Pattern.compile("^[0-9]+$");

    public void initialize(){
        setMap();
    }
    private void setMap() {
        map.put(txtCustomerId,customerId);
        map.put(txtCustomerTittle,customerTittle);
        map.put(txtCustomerName,customerName);
        map.put(txtCustomerAddress,customerAddress);
        map.put(txtCustomerCity,customerCity);
        map.put(txtPostalCode,customerPostalCode);


    }





    public void searchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = customerBO.customerSearch(txtCustomerId.getText());
        if(c1 != null){
            txtCustomerId.setText(c1.getCustomerId());
            txtCustomerTittle.setText(c1.getCustomerTittle());
            txtCustomerName.setText(c1.getCustomerName());
            txtCustomerAddress.setText(c1.getCustomerAddress());
            txtCustomerCity.setText(c1.getCity());
            txtProvince.setText(c1.getProvince());
            txtPostalCode.setText(c1.getPostalCode());
        }

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        CustomerDTO c1 = new CustomerDTO(
                txtCustomerId.getText(),
                txtCustomerTittle.getText(),
                txtCustomerName.getText(),
                txtCustomerAddress.getText(),
                txtCustomerCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        if(customerBO.customerUpdate(c1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Update success").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Update  not success").show();
        }



    }

    public void validation_key_release(KeyEvent keyEvent) {

        Object response = Validations.validationStep(map,btnUpdateCustomer);
        if(keyEvent.getCode() == KeyCode.ENTER){
            if(response instanceof  TextField){
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            }else {

            }
        }
    }
}
