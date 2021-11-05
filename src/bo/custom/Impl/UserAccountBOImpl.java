package bo.custom.Impl;

import bo.custom.UserAccountBO;

import dao.DAOFactory;
import dao.custom.UserDao;
import entity.Employ;
import model.UserAccountDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserAccountBOImpl implements UserAccountBO {

    private final  UserDao userDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean EmployAdd(UserAccountDTO c1) throws SQLException, ClassNotFoundException {
       return userDao.add(new Employ(c1.getEmployId(), c1.getEmployName(),
               c1.getEmployTittle(), c1.getEmployAddress(), c1.getEmployPassword()));

    }

    @Override
    public UserAccountDTO EmploySearch(String EmployId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean EmployUpdate(UserAccountDTO c1) throws SQLException, ClassNotFoundException {



        return false;
    }

    @Override
    public boolean EmployDelete(String c1) throws SQLException, ClassNotFoundException {
        return userDao.delete(c1);




    }

    @Override
    public ArrayList<UserAccountDTO> getAllEmploy() throws SQLException, ClassNotFoundException {
        ArrayList<UserAccountDTO> allEmployee = new ArrayList<>();
        for (Employ e1:userDao.getAll()
             ) {
            allEmployee.add(new UserAccountDTO(e1.getEmployId(), e1.getEmployName(), e1.getEmployTittle(),
                    e1.getEmployAddress(), e1.getEmployPassword()));
        }

            return allEmployee;

    }






}
