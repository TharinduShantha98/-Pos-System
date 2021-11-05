package bo.custom;

import bo.SuperBO;
import views.TM.MovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MostMovableBO extends SuperBO {
    public ArrayList<MovableTM> setMostMovableItems() throws SQLException, ClassNotFoundException;

}
