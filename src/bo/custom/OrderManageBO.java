package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderManageBO extends SuperBO {
    public ArrayList<String> getAllOrderId() throws SQLException, ClassNotFoundException;
    public  boolean changeItemQty(String itemCode, String orderId ,int updateQty, int qty, double unitPrice) throws SQLException, ClassNotFoundException;

}
