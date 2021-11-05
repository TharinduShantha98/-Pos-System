package bo.custom;

import bo.SuperBO;
import views.TM.MovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LessMovableBO  extends SuperBO {
    public ArrayList<MovableTM> setLessMovableItem() throws SQLException, ClassNotFoundException;
}
