package bo.custom;

import bo.SuperBO;
import views.TM.CustomerWise;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerWiseBO extends SuperBO {
     ArrayList<CustomerWise> getCustomerWiseProfit() throws SQLException, ClassNotFoundException;
}
