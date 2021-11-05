package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLoginFormController {


    public Button btnAdminLogin;
    public Button btnCancel;

    public void adminCancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) btnAdminLogin.getScene().getWindow();
        stage1.close();

        NewDashBordFormController.stage.close();


        URL resource  = getClass().getResource("../views/NewDashBordForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Administrator form");
        stage.show();

    }

    public void adminLogInOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage1 = (Stage) btnAdminLogin.getScene().getWindow();
        stage1.close();

        NewDashBordFormController.stage.close();

        URL resource  = getClass().getResource("../views/AdministratorHomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Administrator form");
        stage.show();


    }
}
