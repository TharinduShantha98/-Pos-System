package bo.custom;

import bo.SuperBO;
import model.ItemDetailChart;
import views.TM.TodayOrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminHomeBo extends SuperBO {
    public int setTotalProfit() throws SQLException, ClassNotFoundException;
    public int setTodayProfit(String nowDate) throws SQLException, ClassNotFoundException;
    public ArrayList<String> setMostProfitItem() throws SQLException, ClassNotFoundException;
    public ArrayList<TodayOrderTM> setTableTodayOrder(String date) throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDetailChart> setItemDetailChart(String date) throws SQLException, ClassNotFoundException;
}
