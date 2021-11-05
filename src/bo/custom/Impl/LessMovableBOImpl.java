package bo.custom.Impl;

import bo.custom.LessMovableBO;
import dao.DAOFactory;
import dao.custom.Impl.QueryDaoImpl;
import dao.custom.QueryDao;
import views.TM.MovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class LessMovableBOImpl implements LessMovableBO {

    private final QueryDao queryDao = (QueryDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    @Override
    public ArrayList<MovableTM> setLessMovableItem() throws SQLException, ClassNotFoundException {
       return queryDao.setLessMovableItem();
    }
}
