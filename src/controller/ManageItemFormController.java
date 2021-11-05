package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManageItemFormController {

    public JFXButton btnBack;

    public void initialize(){
        try {
            gotoPage("../views/addItemsForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public AnchorPane context3;


    public void addItemsOnAction(ActionEvent actionEvent) throws IOException {
        gotoPage("../views/addItemsForm.fxml");

    }

    public void searchItemsOnAction(ActionEvent actionEvent) throws IOException {
        gotoPage("../views/SearchItemForm.fxml");
    }

    public void updateItemsOnAction(ActionEvent actionEvent) throws IOException {
        gotoPage("../views/UpdateItemForm.fxml");
    }

    public void removeItemsOnAction(ActionEvent actionEvent) throws IOException {
        gotoPage("../views/RemoveItemForm.fxml");
    }

    public void allItemsOnAction(ActionEvent actionEvent) throws IOException {
        gotoPage("../views/AllItemForm.fxml");
    }


    private  void gotoPage(String location ) throws IOException {
        URL resource = getClass().getResource(location);
        Parent Load = FXMLLoader.load(resource);
        context3.getChildren().clear();
        context3.getChildren().add(Load);

    }


    public void goTobackPage(ActionEvent actionEvent) throws IOException {
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
