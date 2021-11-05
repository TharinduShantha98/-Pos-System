package controller;

import bo.BOFactory;
import bo.custom.AnnuallyBO;
import bo.custom.Impl.AnnuallyIncomeBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import views.TM.IncomeTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AnnuallyIncomeFormController {


    public TableView<IncomeTM> tblYear;
    public TableColumn colItemCode;
    public TableColumn colSalesQty;
    public TableColumn colItemProfit;
    public JFXTextField txtYear;
    public LineChart yearChart;
    public Label lblYear;
    public JFXButton btnBack;

    AnnuallyBO annuallyBO = (AnnuallyBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ANNUALLY_INCOME);


    public void  initialize(){

        colItemProfit.setCellValueFactory(new PropertyValueFactory<>("itemProfit"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colSalesQty.setCellValueFactory(new PropertyValueFactory<>("saleItemQty"));


    }



    public void setYearDataOnAction(ActionEvent actionEvent) {

        lblYear.setText(txtYear.getText());
        try {
            setTblYear();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        setYearChart();
    }

    ObservableList<IncomeTM> tmObservableList = FXCollections.observableArrayList();
    private  void setTblYear() throws SQLException, ClassNotFoundException {
        for (IncomeTM temp :annuallyBO.getYearProfit(txtYear.getText())
        ) {
            tmObservableList.add(temp);
        }
        tblYear.setItems(tmObservableList);
    }

    private  void setYearChart(){
        XYChart.Series<String,Number> set1 = new XYChart.Series<>();
        set1.setName("Profit");

        for(int i=0; i < tmObservableList.size(); i++){
            set1.getData().add(new XYChart.Data<>(tmObservableList.get(i).getItemCode(),tmObservableList.get(i).getItemProfit()));

        }

        XYChart.Series<String,Number> set2 = new XYChart.Series<>();
        set1.setName("sales QTY");

        for(int i=0; i < tmObservableList.size(); i++){
            set2.getData().add(new XYChart.Data<>(tmObservableList.get(i).getItemCode(),tmObservableList.get(i).getSaleItemQty()));

        }
        yearChart.getData().addAll(set1, set2);



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
