package dao.custom;

import dao.CrudDao;
import entity.OrderDetail;
import views.TM.OrderManageCartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailDao extends CrudDao<OrderDetail,String> {
    ArrayList<String> getAllOrderId() throws SQLException, ClassNotFoundException;
    double getItemCost(String itemCode, String orderId) throws SQLException, ClassNotFoundException;
    boolean updateOrderDetail(String itemCode, String orderId ,int updateQty, int qty, double unitPrice) throws SQLException, ClassNotFoundException;
    ArrayList<OrderManageCartTM> selectItem(String orderId) throws SQLException, ClassNotFoundException;
    int setTotalProfit() throws SQLException, ClassNotFoundException;
    public  ArrayList<String> setMostProfitItem() throws SQLException, ClassNotFoundException;



}
