package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.UserDao;
import entity.Employ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean add(Employ employ) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("INSERT INTO employ VALUES (?,?,?,?,?)",employ.getEmployId(),employ.getEmployName(),
                employ.getEmployTittle(), employ.getEmployAddress(),employ.getEmployPassword());

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM employ WHERE employID= ?",s);

    }

    @Override
    public boolean update(Employ employ) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Employ search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Employ> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM employ");
        ArrayList<Employ> userAccountArray= new ArrayList<>();
        while (resultSet.next()){
            userAccountArray.add(new Employ(resultSet.getString(1)
                    ,resultSet.getString(2)
                    ,resultSet.getString(3)
                    ,resultSet.getString(4)
                    ,resultSet.getString(5)
            ));
        }
        return userAccountArray;
    }

    @Override
    public boolean verify(String name, String tittle, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM employ WHERE employName = ? AND employTitle = ? AND employPassWord = ?",
                name, tittle, password);
        if(resultSet.next()){
            return true;
        }else{
            return false;
        }
    }
}
