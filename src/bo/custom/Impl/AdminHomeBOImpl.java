package bo.custom.Impl;

import bo.custom.AdminHomeBo;
import dao.DAOFactory;
import dao.custom.OrderDetailDao;
import dao.custom.QueryDao;
import model.ItemDetailChart;
import views.TM.TodayOrderTM;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminHomeBOImpl  implements AdminHomeBo {


    private  final QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    private  final OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);


    @Override
    public int setTotalProfit() throws SQLException, ClassNotFoundException {
         return  orderDetailDao.setTotalProfit();
    }
    @Override
    public int setTodayProfit(String nowDate) throws SQLException, ClassNotFoundException {
       return queryDao.setTodayProfit(nowDate);
    }
    @Override
    public  ArrayList<String> setMostProfitItem() throws SQLException, ClassNotFoundException {
        return orderDetailDao.setMostProfitItem();
    }
    @Override
     public ArrayList<TodayOrderTM> setTableTodayOrder(String date) throws SQLException, ClassNotFoundException {
        return queryDao.setTableTodayOrder(date);
     }
     @Override
     public ArrayList<ItemDetailChart> setItemDetailChart(String date) throws SQLException, ClassNotFoundException {
           return queryDao.setItemDetailChart(date);

     }








}
