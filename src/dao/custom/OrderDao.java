package dao.custom;

import dao.CrudDao;
import entity.Order;

import java.sql.SQLException;

public interface OrderDao extends CrudDao<Order,String> {
    String getOrderId() throws SQLException, ClassNotFoundException;
    boolean updateOrderTable(double losItemCost, String orderId) throws SQLException, ClassNotFoundException;


}
