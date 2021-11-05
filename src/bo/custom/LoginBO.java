package bo.custom;

import bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public boolean verify(String name ,String tittle , String Password) throws SQLException, ClassNotFoundException;
}
