package controller;


import bo.BOFactory;
import bo.custom.CustomerBO;
import bo.custom.Impl.CustomerBOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.CustomerDTO;
import views.TM.CustomerTM;

import java.sql.SQLException;

public class AllCustomerFormController {


    public TableView tblAllCustomer;
    public TableColumn colCusId;
    public TableColumn colCusTitle;
    public TableColumn colCusName;
    public TableColumn coiCusAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    public void initialize() throws SQLException, ClassNotFoundException {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colCusTitle.setCellValueFactory(new PropertyValueFactory<>("cusTittle"));
        colCusName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        coiCusAddress.setCellValueFactory(new PropertyValueFactory<>("cusAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("cusCity"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("cusProvince"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        settable();


    }


    private void settable() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTM> customers = FXCollections.observableArrayList();
        for (CustomerDTO c1 : customerBO.getAllCustomer()
             ) {
            CustomerTM tm = new CustomerTM(c1.getCustomerId(),c1.getCustomerTittle(), c1.getCustomerName(),
                    c1.getCustomerAddress(),c1.getCity(), c1.getProvince(),c1.getPostalCode());

            customers.add(tm);

        }
        tblAllCustomer.setItems(customers);

    }




}
