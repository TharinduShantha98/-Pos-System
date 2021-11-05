package bo.custom;

import bo.SuperBO;
import views.TM.IncomeTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MonthlyBO  extends SuperBO {

    public ArrayList<IncomeTM> getMonthProfit(String date) throws SQLException, ClassNotFoundException;

}
