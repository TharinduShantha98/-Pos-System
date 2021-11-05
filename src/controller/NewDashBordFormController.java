package controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class NewDashBordFormController {
    public ImageView Customer;
    public ImageView OrderMange;
    public ImageView admin;
    public ImageView placeOrder;
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDescription;
    public Label lblDate;
    public Label lblTime;

    public static Stage stage;

    public void initialize(){
        setTimeAndDate();


    }

    public void setTimeAndDate(){
        new DateAndTime().setTimeAndData(lblDate,lblTime);

    }




    public void navigate(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();
            Parent root = null;
            switch (icon.getId()){
                case "placeOrder":
                    root = FXMLLoader.load(this.getClass().getResource("../views/CustomerOrderForm.fxml"));
                    break;
                case "Customer":
                    root = FXMLLoader.load(this.getClass().getResource("../views/CustomerDetailForm.fxml"));
                    break;
                case "OrderMange":
                    root = FXMLLoader.load(this.getClass().getResource("../views/OrderManageForm.fxml"));
                    break;
                default:

            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();




                TranslateTransition tt = new TranslateTransition(Duration.millis(500), subScene.getRoot());
                tt.setFromX(+subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }

    public void navigate2(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            Parent root1 = null;
            switch (icon.getId()) {

                case "admin":
                    root1 = FXMLLoader.load(this.getClass().getResource("../views/AdminLoginForm.fxml"));
                    break;
            }

            stage = (Stage) this.root.getScene().getWindow();

            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Admin Login Form");
            stage.show();
        }

    }




    public void PlayMouseEntered(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()){
                case "placeOrder":
                    lblMenu.setText("Place order");
                    lblDescription.setText("click here if you want place new order");
                    break;
                case "Customer":
                    lblMenu.setText("Manage Customer");
                    lblDescription.setText("click here if you want add,search,delete and update customer");
                    break;
                case "OrderMange":
                    lblMenu.setText("Manage Customer");
                    lblDescription.setText("click here if you want manage customer oder");
                    break;
                case "admin":
                    lblMenu.setText("Admin");
                    lblDescription.setText("click here if you want to go admin page");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);

        }


    }


    public void playMouseExited(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof  ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200),icon);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

        }

    }


}
