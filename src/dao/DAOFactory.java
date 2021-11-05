package dao;

import dao.custom.Impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }


    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER, ORDERDETAIL, USER ,QUERYDAO
    }

    public static DAOFactory getDaoFactory(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDao getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerDaoImpl();
            case ITEM:
                return new ItemDaoImpl();
            case ORDER:
                return  new OrderDaoImpl();
            case ORDERDETAIL:
                return new OrderDetailDaoImpl();
            case USER:
                return  new UserDaoImpl();
            case QUERYDAO:
                return new QueryDaoImpl();
            default:
                return null;


        }

    }


}
