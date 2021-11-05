package bo.custom.Impl;

import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.Impl.UserDaoImpl;
import dao.custom.UserDao;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    private final UserDao userDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean verify(String name ,String tittle , String password) throws SQLException, ClassNotFoundException {
            return userDao.verify(name,tittle,password);

    }
}
