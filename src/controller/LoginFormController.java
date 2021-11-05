package controller;

import bo.BOFactory;
import bo.custom.Impl.LoginBOImpl;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {


    public static String  userName;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXComboBox<String> cmbTitle;
    public JFXButton btnLogin;
    String title = null;

    private final  LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);


    public  void initialize(){
        setCmbTitle();

        cmbTitle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            title = newValue;

        });
    }

    public  void setCmbTitle(){
        ObservableList<String>  observableList = FXCollections.observableArrayList();
        observableList.add("Admin");
        observableList.add("cashier");
        cmbTitle.setItems(observableList);

    }

    public void btnLoginPageOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        if(loginBO.verify(txtUserName.getText(),title,txtPassword.getText())) {
            userName = txtUserName.getText();

            if (title.equalsIgnoreCase("cashier")) {

                Stage stage1 = (Stage) btnLogin.getScene().getWindow();
                stage1.close();

                URL resource = getClass().getResource("../views/NewDashBordForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Administrator form");
                stage.show();
            } else {
                Stage stage1 = (Stage) btnLogin.getScene().getWindow();
                stage1.close();


                URL resource = getClass().getResource("../views/AdministratorHomeForm.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Administrator form");
                stage.show();


            }
        }else{

            new Alert(Alert.AlertType.WARNING,"try Again").show();
        }
    }
}
