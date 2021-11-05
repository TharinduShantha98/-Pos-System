package bo.custom.Impl;

import bo.custom.CustomerWiseBO;
import dao.DAOFactory;
import dao.custom.Impl.QueryDaoImpl;
import dao.custom.QueryDao;
import db.DbConnection;
import views.TM.CustomerWise;
import views.TM.IncomeTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerWiseBOImpl implements CustomerWiseBO {

    private final QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    public ArrayList<CustomerWise> getCustomerWiseProfit() throws SQLException, ClassNotFoundException {
        return queryDao.getCustomerWiseProfit();



    }








}
