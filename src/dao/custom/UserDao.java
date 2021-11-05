package dao.custom;

import dao.CrudDao;
import entity.Employ;

import java.sql.SQLException;

public interface UserDao extends CrudDao<Employ,String> {
    public boolean verify(String name ,String tittle , String Password) throws SQLException, ClassNotFoundException;

}
