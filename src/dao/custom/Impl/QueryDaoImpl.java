package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.QueryDao;
import db.DbConnection;
import model.ItemDetailChart;
import views.TM.CustomerWise;
import views.TM.IncomeTM;
import views.TM.MovableTM;
import views.TM.TodayOrderTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<MovableTM> setMostMovableItems() throws SQLException, ClassNotFoundException {
        ArrayList<MovableTM> movableTMArrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT i.itemCode ,COUNT(od.itemCode), sum(od.orderQty) From item i INNER JOIN orderDetail od " +
                "ON i.itemCode = od.itemCode GROUP BY od.itemCode ORDER BY sum(od.orderQty) DESC");

        while (resultSet.next()){
            movableTMArrayList.add(new MovableTM(resultSet.getString(1),
                    resultSet.getInt(2),resultSet.getInt(3)));
        }
        return  movableTMArrayList;
    }


    @Override
    public ArrayList<MovableTM> setLessMovableItem() throws SQLException, ClassNotFoundException {
        ArrayList<MovableTM> movableTMArrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT i.itemCode ,COUNT(od.itemCode), sum(od.orderQty) From item i INNER JOIN orderDetail od " +
                "ON i.itemCode = od.itemCode GROUP BY od.itemCode ORDER BY sum(od.orderQty) ASC");
        while (resultSet.next()){
            movableTMArrayList.add(new MovableTM(resultSet.getString(1),
                    resultSet.getInt(2),resultSet.getInt(3)));
        }
        return  movableTMArrayList;
    }

    @Override
    public int setTodayProfit(String nowDate) throws SQLException, ClassNotFoundException {
        int todayProfit = 0;
        ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(itemProfit) FROM  OrderDetail WHERE orderId IN" +
                "(SELECT orderId FROM `order` WHERE OrderDate ='" + nowDate + "')");
        if(resultSet.next()){
            todayProfit = resultSet.getInt(1);
        }
        return todayProfit;
    }

    @Override
    public ArrayList<TodayOrderTM> setTableTodayOrder(String date) throws SQLException, ClassNotFoundException {
        ArrayList<TodayOrderTM> temp = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT sum(itemProfit), sum(orderQty),sum(itemDiscount),orderId FROM orderDetail WHERE orderId IN " +
                "(SELECT orderId FROM `order` WHERE OrderDate ='" + date + "') GROUP BY orderId");
        while (resultSet.next()){
            temp.add(new TodayOrderTM(resultSet.getString(4),
                    resultSet.getDouble(3),
                    resultSet.getDouble(2),
                    resultSet.getDouble(1)));

        }
        return temp;
    }

    @Override
    public ArrayList<ItemDetailChart> setItemDetailChart(String date) throws SQLException, ClassNotFoundException {

        ArrayList<ItemDetailChart> itemDetailCharts = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode, SUM(orderQty) FROM orderDetail WHERE orderId IN" +
                "(SELECT orderId FROM `order` WHERE OrderDate ='" + date + "') GROUP BY itemCode");
        while (resultSet.next()){
            itemDetailCharts.add(new ItemDetailChart(resultSet.getString(1),resultSet.getInt(2)));
        }
        return itemDetailCharts;
    }

    @Override
    public ArrayList<IncomeTM> getYearProfit(String year) throws SQLException, ClassNotFoundException {
        ArrayList<IncomeTM> monthDetail = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode, COUNT(itemCode),SUM(orderQty), SUM(itemProfit) FROM orderDetail Where orderId IN" +
                "(SELECT orderId FROM `order` Where orderDate BETWEEN  '" + year + "''-01-01' AND '" + year + "''-12-31') " +
                "GROUP BY itemCode;");
        while (resultSet.next()){
            monthDetail.add(new IncomeTM(resultSet.getString(1),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)));
        }
        return  monthDetail;
    }

    @Override
    public ArrayList<IncomeTM> getMonthProfit(String date) throws SQLException, ClassNotFoundException {
        ArrayList<IncomeTM> monthDetail = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode, COUNT(itemCode),SUM(orderQty), SUM(itemProfit) FROM orderDetail Where orderId IN" +
                "(SELECT orderId FROM `order` Where orderDate BETWEEN  '2021-''" + date + "''-01' AND '2021-''" + date + "''-29' ) " +
                "GROUP BY itemCode;");

        while (resultSet.next()){
            monthDetail.add(new IncomeTM(resultSet.getString(1),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)));
        }
        return  monthDetail;
    }

    @Override
    public ArrayList<CustomerWise> getCustomerWiseProfit() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT o.cusId, SUM(od.itemProfit), SUM(od.orderQty) From" +
                " `order` o INNER JOIN orderDetail od ON o.orderId = od.orderId GROUP BY o.cusId");
        ArrayList<CustomerWise> customerWiseArrayList = new ArrayList<>();
        while (resultSet.next()){
            customerWiseArrayList.add(new CustomerWise(resultSet.getString(1),
                    resultSet.getDouble(2),resultSet.getInt(3)));
        }
        return  customerWiseArrayList;
    }

}
