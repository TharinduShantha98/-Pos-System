package controller;


import bo.BOFactory;
import bo.custom.CustomerWiseBO;
import bo.custom.Impl.CustomerWiseBOImpl;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import views.TM.CustomerWise;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class CustomerWiseFormController {


    public TableView tblCustomerWiseProfit;
    public TableColumn colCusId;
    public TableColumn colOrderQty;
    public TableColumn colTotalIncome;
    public LineChart chartCusWise;
    public CategoryAxis X;
    public NumberAxis y;
    public JFXButton btnBack;


    CustomerWiseBO customerWiseBO = (CustomerWiseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER_WISE_INCOME);

    public void initialize(){
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("itemProfit"));
        colTotalIncome.setCellValueFactory(new PropertyValueFactory<>("itemQty"));


        try {
            setTblCustomerWiseProfit();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        setChartCusWise();

    }
    ObservableList<CustomerWise> customerWiseObservableList = FXCollections.observableArrayList();
    private  void setTblCustomerWiseProfit() throws SQLException, ClassNotFoundException {
        for (CustomerWise temp: customerWiseBO.getCustomerWiseProfit()
             ) {
            customerWiseObservableList.add(temp);
        }


        tblCustomerWiseProfit.setItems(customerWiseObservableList);

    }


    private void setChartCusWise(){
        XYChart.Series<String,Number> set1 = new XYChart.Series<>();
        set1.setName("Customer wise Profit");

        for(int i=0; i < customerWiseObservableList.size(); i++){
            set1.getData().add(new XYChart.Data<>(customerWiseObservableList.get(i).getCusId(),
                    customerWiseObservableList.get(i).getItemProfit()));

        }
        chartCusWise.getData().addAll(set1);



    }


    public void backToMain(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) btnBack.getScene().getWindow();
        stage1.close();

        URL resource  = getClass().getResource("../views/AdministratorHomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Administrator form");
        stage.show();

    }
}
