package controller;

import bo.BOFactory;
import bo.custom.AdminHomeBo;
import bo.custom.Impl.AdminHomeBOImpl;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.ItemDetailChart;
import views.TM.TodayOrderTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdministratorHomeFormController {

    public Label lblDate;
    public Label lblTime;
    public Label lblSetTotalProfit;
    public Label lblDailyProfit;
    public Label lblMostProfitItemProfit;
    public Label lblMostItemCode;
    public Label lblMostProfitQty;
    public TableView tblTodayOrder;
    public TableColumn colOrderId;
    public TableColumn colFullDiscount;
    public TableColumn colTotalQty;
    public TableColumn colProfit;
    public LineChart<String,Number> tblSaleItemTable;
    public ImageView ImgItemMange;
    public ImageView incomeProfit;
    public ImageView ImgMostItem;
    public ImageView ImgLessItem;
    public ImageView ImgSetting;
    public ImageView ImgLogOut;
    public Label lblMenu;
    public Label lblDescription;

    private final AdminHomeBo adminHomeBo = (AdminHomeBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN_HOME);
    public AnchorPane root;


    public void initialize(){
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colFullDiscount.setCellValueFactory(new PropertyValueFactory<>("totalDiscount"));
        colTotalQty.setCellValueFactory(new PropertyValueFactory<>("TotalQty"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("TotalProfit"));




        setTimeAndDate();

        try {
            setTotalProfit();
            setDailyProfit();
           // setLblMostProfitItemProfit();
            setTableTodayOrderDetail();
            setChart();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    private  void setTimeAndDate(){
        new DateAndTime().setTimeAndData(lblDate,lblTime);

    }

    private  void setTotalProfit() throws SQLException, ClassNotFoundException {
        int total = adminHomeBo.setTotalProfit();
        lblSetTotalProfit.setText(String.valueOf(total));
    }


    private void setDailyProfit() throws SQLException, ClassNotFoundException {
        int dailyProfit = adminHomeBo.setTodayProfit(lblDate.getText());
        lblDailyProfit.setText(String.valueOf(dailyProfit));

    }

    private  void setLblMostProfitItemProfit() throws SQLException, ClassNotFoundException {
        ArrayList<String> arrayList = adminHomeBo.setMostProfitItem();
        lblMostItemCode.setText(arrayList.get(0));
        lblMostProfitQty.setText(arrayList.get(1));
        lblMostProfitItemProfit.setText(arrayList.get(2));

    }

    private  void setTableTodayOrderDetail() throws SQLException, ClassNotFoundException {
        ObservableList<TodayOrderTM> observableList = FXCollections.observableArrayList();
        for (TodayOrderTM temp: adminHomeBo.setTableTodayOrder(lblDate.getText())) {
            observableList.add(temp);
        }
        tblTodayOrder.setItems(observableList);

    }


    private void setChart() throws SQLException, ClassNotFoundException {
        XYChart.Series<String,Number> set1 = new XYChart.Series<>();
        ArrayList<ItemDetailChart> temp = adminHomeBo.setItemDetailChart(lblDate.getText());
        for(int i=0; i < temp.size(); i++){
            set1.getData().add(new XYChart.Data<>(temp.get(i).getItemCode(),temp.get(i).getTotalQty()));
        }
        tblSaleItemTable.getData().addAll(set1);

    }


    public void mouseEntered(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()){
                case "ImgItemMange":
                    lblMenu.setText("Item Manage");
                    lblDescription.setText("click here if you want manage items");
                    break;
                case "ImgMostItem":
                    lblMenu.setText("Most movable item");
                    lblDescription.setText("click here if you want see most  Movable items");
                    break;
                case "ImgLessItem":
                    lblMenu.setText("Less movable item");
                    lblDescription.setText("click here if you want see less  Movable items");
                    break;
                case "incomeProfit":
                    lblMenu.setText("Income report");
                    lblDescription.setText("click here if you want see income report annually, monthly and customerWise");
                    break;
                case "ImgSetting":
                    lblMenu.setText("Setting");
                    lblDescription.setText("click here if you want to change setting");
                    break;
                case "ImgLogOut":
                    lblMenu.setText("LogOut");
                    lblDescription.setText("click here if you want to logOut");

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

    public void mouseExited(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof  ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200),icon);
            scaleTransition.setToY(1);
            scaleTransition.setToX(1);
            scaleTransition.play();

            icon.setEffect(null);
            lblMenu.setText("WELCOME");
            lblDescription.setText("Please select one of above main operations to proceed");
        }


    }

    public void navigation(MouseEvent mouseEvent) throws IOException {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            Parent root = null;
            switch (icon.getId()){
                case "ImgItemMange":
                    root = FXMLLoader.load(this.getClass().getResource("../views/ManageItemForm.fxml"));
                    break;
                case "ImgMostItem":
                    root = FXMLLoader.load(this.getClass().getResource("../views/MostMovableForm.fxml"));
                    break;
                case "ImgLessItem":
                    root = FXMLLoader.load(this.getClass().getResource("../views/lessMovableItems.fxml"));
                    break;
                case "incomeProfit":
                    root = FXMLLoader.load(this.getClass().getResource("../views/IncomeReportForm.fxml"));
                    break;
                case "ImgSetting":
                    root = FXMLLoader.load(this.getClass().getResource("../views/UserAccountForm.fxml"));
                    break;


            }

            if(root != null){
                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
            }


        }

    }
}
