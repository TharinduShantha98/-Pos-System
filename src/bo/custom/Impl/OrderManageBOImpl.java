package bo.custom.Impl;

import bo.custom.OrderManageBO;
import dao.DAOFactory;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.Impl.OrderDaoImpl;
import dao.custom.Impl.OrderDetailDaoImpl;
import dao.custom.ItemDao;
import dao.custom.OrderDao;
import dao.custom.OrderDetailDao;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManageBOImpl implements OrderManageBO {
    private final OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    private final OrderDao orderDao = (OrderDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<String> getAllOrderId() throws SQLException, ClassNotFoundException {
        return new ArrayList<>(orderDetailDao.getAllOrderId());
    }

    @Override
    public  boolean changeItemQty(String itemCode, String orderId ,int updateQty, int qty, double unitPrice)
            throws SQLException, ClassNotFoundException {
        double itemCost = getItemCost(itemCode,orderId);
        System.out.println(itemCost+"=========================");

        boolean result =  orderDetailDao.updateOrderDetail(itemCode,orderId,updateQty,qty,unitPrice);
        if(result){
            double lostItemCost = (itemCost/qty) * updateQty;

            if(updateOrderTable(lostItemCost,orderId)){
                if(updateItemTable(updateQty,itemCode)){
                    return true;
                }else {
                    return false;
                }

            }else{
                return false;
            }

        }else{
            return  false;
        }
    }

    public double getItemCost(String itemCode, String orderId) throws SQLException, ClassNotFoundException {
       return orderDetailDao.getItemCost(itemCode,orderId);
    }

    public boolean updateOrderTable(double losItemCost, String orderId) throws SQLException, ClassNotFoundException {
       return  orderDao.updateOrderTable(losItemCost,orderId);

    }

    private  boolean updateItemTable(int updateQty, String itemCode) throws SQLException, ClassNotFoundException {
       return  itemDao.updateItemTable(updateQty,itemCode);

    }



}
