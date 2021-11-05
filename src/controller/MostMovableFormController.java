package controller;

import bo.BOFactory;
import bo.custom.Impl.MostMovableBOImpl;
import bo.custom.MostMovableBO;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import views.TM.MovableTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableFormController {


    public TableView<MovableTM> tblMostMovable;
    public TableColumn colItemCode;
    public TableColumn colOrderCount;
    public TableColumn colMovableQty;

    public CategoryAxis x;
    public NumberAxis y;
    public BarChart<String,Number> movableQty;


    private  final MostMovableBO mostMovableBO = (MostMovableBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MOST_MOVABLE_ITEM);
    public JFXButton btnBack;


    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderCount.setCellValueFactory(new PropertyValueFactory<>("orderCount"));
        colMovableQty.setCellValueFactory(new PropertyValueFactory<>("MovableQty"));

        try {
            setTblMostMovable();
            setChart();
        } catch (SQLException |ClassNotFoundException e) {
             e.printStackTrace();
        }
    }


    public  void setTblMostMovable() throws SQLException, ClassNotFoundException {
        ObservableList<MovableTM> movableTMObservableList = FXCollections.observableArrayList();
        for (MovableTM temp: mostMovableBO.setMostMovableItems()
              ) {
            movableTMObservableList.add(temp);
        }
        tblMostMovable.setItems(movableTMObservableList);
    }

    public void setChart() throws SQLException, ClassNotFoundException {
        XYChart.Series<String ,Number> set1 = new XYChart.Series<>();
        set1.setName("MOVABLE ITEMS");
        ArrayList<MovableTM> temp = mostMovableBO.setMostMovableItems();
       for (int i=0; i< temp.size(); i++){
           set1.getData().add(new XYChart.Data<>(temp.get(i).getItemCode(),temp.get(i).getMovableQty()));
       }
       movableQty.getData().addAll(set1);


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
