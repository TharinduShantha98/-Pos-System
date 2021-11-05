package bo.custom;

import bo.SuperBO;
import model.ItemDetail;
import model.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    boolean setOrderDetail(OrderDTO order);
    boolean setOrderDetail(ArrayList<ItemDetail> items, String orderId) throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;

}
