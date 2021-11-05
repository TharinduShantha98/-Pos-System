package dao.custom;

import dao.CrudDao;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDao extends CrudDao<Item, String> {
    ArrayList<String> getItemCode() throws SQLException, ClassNotFoundException;
    double getItemProfit(String itemCode) throws SQLException, ClassNotFoundException;
    boolean modifyItemsQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
    boolean updateItemTable(int updateQty, String itemCode) throws SQLException, ClassNotFoundException;
}
