package bo.custom;

import bo.SuperBO;
import views.TM.IncomeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnnuallyBO  extends SuperBO {
    public ArrayList<IncomeTM> getYearProfit(String year) throws SQLException, ClassNotFoundException;


}
