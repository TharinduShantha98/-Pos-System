package controller;


import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.Impl.CustomerBOImpl;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.CustomerDTO;

import java.sql.SQLException;

public class SearchCustomerFromController {

    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerTittle;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerCity;
    public JFXTextField txtPostalCode;
    public JFXTextField txtProvince;


    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO c1 = customerBO.customerSearch(txtCustomerId.getText());
            if(c1 != null){
                txtCustomerId.setText(c1.getCustomerId());
                txtCustomerTittle.setText(c1.getCustomerTittle());
                txtCustomerName.setText(c1.getCustomerName());
                txtCustomerAddress.setText(c1.getCustomerAddress());
                txtCustomerCity.setText(c1.getCity());
                txtProvince.setText(c1.getProvince());
                txtPostalCode.setText(c1.getPostalCode());
            }else{
                new Alert(Alert.AlertType.WARNING, "Not Set").show();
            }


        } catch (SQLException  | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
