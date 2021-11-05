package bo.custom.Impl;

import bo.custom.AnnuallyBO;
import dao.DAOFactory;
import dao.custom.Impl.QueryDaoImpl;
import dao.custom.QueryDao;
import views.TM.IncomeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnnuallyIncomeBOImpl implements AnnuallyBO {

    private  final  QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    @Override
    public ArrayList<IncomeTM> getYearProfit(String year) throws SQLException, ClassNotFoundException {
        return queryDao.getYearProfit(year);
    }
}
