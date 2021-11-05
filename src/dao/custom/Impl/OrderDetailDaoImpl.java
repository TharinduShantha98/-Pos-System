package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDao;
import db.DbConnection;
import entity.OrderDetail;
import javafx.scene.control.Alert;
import views.TM.OrderManageCartTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDaoImpl implements OrderDetailDao {

    @Override
    public boolean add(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {

        return CrudUtil.executeUpdate("INSERT INTO orderDetail VALUES(?,?,?,?,?,?)",orderDetail.getOrderId(),
                orderDetail.getItemCode(),orderDetail.getOrderQty(),orderDetail.getItemDiscount(),orderDetail.getCost(),
                orderDetail.getItemProfit());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `order` ORDER BY orderId DESC");
        ArrayList<String> orderId  = new ArrayList<>();

        while (resultSet.next()){
            orderId.add(resultSet.getString(1));
        }
        return  orderId;
    }

    @Override
    public double getItemCost(String itemCode, String orderId) throws SQLException, ClassNotFoundException {
        double itemCost = 0;
        ResultSet resultSet = CrudUtil.executeQuery("SELECT cost FROM orderDetail WHERE itemCode = ? AND orderId = ?", itemCode, orderId);
        if(resultSet.next()){
            itemCost = resultSet.getDouble(1);
        }
        return  itemCost;
    }

    @Override
    public boolean updateOrderDetail(String itemCode, String orderId, int updateQty, int qty, double unitPrice)
            throws SQLException, ClassNotFoundException {

        return  CrudUtil.executeUpdate("UPDATE orderDetail SET orderQty = (orderQty-"+updateQty+"),itemDiscount = itemDiscount- ((itemDiscount /" +qty+") *"+updateQty+")," +
                " cost = (cost -( " +unitPrice* updateQty+"))," +
                " itemProfit = itemProfit - (itemProfit/"+qty+")*"+ updateQty +" WHERE orderId = '"+orderId+"' AND" +
                " itemCode = '"+itemCode+"'");

    }



    public ArrayList<OrderManageCartTM> selectItem(String orderId) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getDbConnection().getConnection().prepareStatement(
                "SELECT * FROM orderDetail WHERE orderId = '" +orderId +"'");
        ArrayList<OrderManageCartTM> itemArrayList = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            itemArrayList.add(new OrderManageCartTM(resultSet.getString(2),
                    resultSet.getInt(3),resultSet.getDouble(4),resultSet.getDouble(5)));
        }
        return itemArrayList;
    }

    @Override
    public int setTotalProfit() throws SQLException, ClassNotFoundException {
        int totalProfit=0;
        ResultSet resultSet = CrudUtil.executeQuery("SELECT SUM(itemProfit) FROM OrderDetail");
        if(resultSet.next()){
            totalProfit = resultSet.getInt(1);
        }else{
            new Alert(Alert.AlertType.WARNING,"not yet").show();
        }
        return totalProfit;
    }

    @Override
    public ArrayList<String> setMostProfitItem() throws SQLException, ClassNotFoundException {
        ArrayList<String> mostProfitItemData = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode, orderQty,itemProfit From orderDetail WHERE itemProfit IN" +
                " (SELECT MAX(itemProfit) FROM orderDetail);");
        if(resultSet.next()){
            mostProfitItemData.add(resultSet.getString(1));
            mostProfitItemData.add(resultSet.getString(2));
            mostProfitItemData.add(resultSet.getString(3));
        }
        return mostProfitItemData;

    }


}
