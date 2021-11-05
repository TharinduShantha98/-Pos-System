package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class IncomeReportFormController {


    public AnchorPane context3;



    public  void initialize() throws IOException {
        goToPage("../views/MonthlyIncomeForm.fxml");


    }

    public void MonthlyIncomeOnAction(ActionEvent actionEvent) throws IOException {
            goToPage("../views/MonthlyIncomeForm.fxml");
    }

    public void AnnulyIncomeOnAction(ActionEvent actionEvent) throws IOException {
        goToPage("../views/AnnuallyIncomeForm.fxml");
    }

    public void customerWiseInCome(ActionEvent actionEvent) throws IOException {

        goToPage("../views/CustomerWiseForm.fxml");
    }


    private  void goToPage(String location) throws IOException {
        URL resource = getClass().getResource(location);
        Parent Load = FXMLLoader.load(resource);
        context3.getChildren().clear();
        context3.getChildren().add(Load);

    }





}
