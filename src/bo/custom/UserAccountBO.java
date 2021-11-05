package bo.custom;
import bo.SuperBO;
import model.UserAccountDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserAccountBO  extends SuperBO {

    public boolean EmployAdd(UserAccountDTO c1) throws SQLException, ClassNotFoundException;
    public UserAccountDTO EmploySearch(String EmployId) throws SQLException, ClassNotFoundException;
    public  boolean EmployUpdate(UserAccountDTO c1) throws SQLException, ClassNotFoundException;
    public  boolean EmployDelete(String c1) throws SQLException, ClassNotFoundException;
    public ArrayList<UserAccountDTO> getAllEmploy() throws SQLException, ClassNotFoundException;
}
