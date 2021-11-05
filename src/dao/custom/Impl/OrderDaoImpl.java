package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.OrderDao;
import db.DbConnection;
import entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO `order` VALUES(?,?,?,?,?,?)",order.getOrderId(),order.getOrderDate(),
                order.getOrderTime(), order.getCusId(),order.getEmployId(),order.getTotalCost());


    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT orderId FROM `Order` ORDER BY orderId DESC LIMIT 1");
        if(resultSet.next()){
            int tempId = Integer.parseInt(resultSet.getString(1).split("-")[1]);
            tempId = tempId +1;
            return  "O-" + tempId;

        }else{
            return "O-100";
        }
    }

    @Override
    public boolean updateOrderTable(double losItemCost, String orderId) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate( "UPDATE `order` SET totalCost = totalCost - ? WHERE orderId = ?",losItemCost,orderId);



    }
}
