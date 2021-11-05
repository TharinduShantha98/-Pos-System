package bo.custom.Impl;

import bo.custom.MonthlyBO;
import dao.DAOFactory;
import dao.custom.QueryDao;
import views.TM.IncomeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class MonthlyIncomeBOImpl implements MonthlyBO {

    private  final QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    @Override
    public ArrayList<IncomeTM> getMonthProfit(String date) throws SQLException, ClassNotFoundException {
        return queryDao.getMonthProfit(date);
    }
}
