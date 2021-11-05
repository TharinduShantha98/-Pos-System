package dao.custom;

import dao.SuperDao;
import model.ItemDetailChart;
import views.TM.CustomerWise;
import views.TM.IncomeTM;
import views.TM.MovableTM;
import views.TM.TodayOrderTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {

    ArrayList<MovableTM> setMostMovableItems() throws SQLException, ClassNotFoundException;
    ArrayList<MovableTM> setLessMovableItem() throws SQLException, ClassNotFoundException;
    int setTodayProfit(String nowDate) throws SQLException, ClassNotFoundException;
    ArrayList<TodayOrderTM> setTableTodayOrder(String date) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDetailChart> setItemDetailChart(String date) throws SQLException, ClassNotFoundException;
    ArrayList<IncomeTM>  getYearProfit(String year) throws SQLException, ClassNotFoundException;
    public  ArrayList<IncomeTM> getMonthProfit(String date) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerWise> getCustomerWiseProfit() throws SQLException, ClassNotFoundException;

}
