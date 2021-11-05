package bo.custom.Impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.OrderDetailDao;
import db.DbConnection;
import entity.Order;
import entity.OrderDetail;
import model.ItemDetail;
import model.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private  final OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private  final OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);

    @Override
    public  boolean setOrderDetail(OrderDTO order)  {

        Connection con = null;
        try {
            con = DbConnection.getDbConnection().getConnection();
            con.setAutoCommit(false);
            boolean result =  orderDao.add(new Order(order.getOrderId(),order.getOrderDate(),order.getOrderTime(),
                    order.getCusId(),order.getEmployId(),order.getTotalCost(),order.getItems()));
            if (result){
                if(setOrderDetail(order.getItems(),order.getOrderId())){
                    con.commit();
                    con.setAutoCommit(true);
                    return  true;

                }else{
                    con.rollback();
                    return false;
                }

            }else {
                con.rollback();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public  boolean setOrderDetail(ArrayList<ItemDetail> items, String orderId) throws SQLException, ClassNotFoundException {
        for (ItemDetail temp:items) {

            double itemProfit = (getItemProfit(temp.getItemCode()) * temp.getQty()) - temp.getDiscount();

            boolean result = orderDetailDao.add(new OrderDetail(orderId,temp.getItemCode(),temp.getQty(),
                    temp.getDiscount(),temp.getTotal(),itemProfit));

            if(result){
                modifyItemsQty(temp.getItemCode(),temp.getQty());

            }else{
                return false;
            }

        }


        return true;
    }


    private double getItemProfit(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDao.getItemProfit(itemCode);

    }
    private  boolean modifyItemsQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
       return itemDao.modifyItemsQty(itemCode,qty);

    }


    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
       return  orderDao.getOrderId();
    }

    /*order manage */
    public ArrayList<String>  getAllOrderId() throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getDbConnection().getConnection().prepareStatement(
                "SELECT orderId FROM `order` ORDER BY orderId DESC");

        ResultSet resultSet = statement.executeQuery();
        ArrayList<String> orderId  = new ArrayList<>();

        while (resultSet.next()){
            orderId.add(resultSet.getString(1));
        }
        return  orderId;
    }




}
