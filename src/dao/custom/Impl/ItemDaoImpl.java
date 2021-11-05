package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.ItemDao;
import db.DbConnection;
import entity.Item;
import model.ItemDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {

        return  CrudUtil.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?,?)",item.getItemCode(),item.getDescription(),item.getPackSize(),
                item.getBuyingPrice(),item.getUnitPrice(),item.getItemDiscount(),item.getQtyOnHand());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM item WHERE itemCode = ?",s);

    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("UPDATE item SET itemCode =?,Description =?,PackSize =?,buyIngPrice =?,unitPrice =?," +
                "discount =?,qtyOnHand =? WHERE itemCode ='"+item.getItemCode()+"'", item.getItemCode(),item.getDescription(),
                item.getPackSize(),item.getBuyingPrice(),item.getUnitPrice(),item.getItemDiscount(),item.getQtyOnHand());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemCode = ?", s);
        if(resultSet.next()){
            Item item = new Item(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getInt(7));
            return item;
        }else {
           return  null;

        }
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item");
        while (resultSet.next()){
            itemArrayList.add(new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getInt(7)));
        }
        return itemArrayList;
    }

    @Override
    public ArrayList<String> getItemCode() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT itemCode FROM item");
        ArrayList<String> itemCode = new ArrayList<>();
        while (resultSet.next()){
            itemCode.add(resultSet.getString(1));
        }
        return itemCode;
    }

    @Override
    public double getItemProfit(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM item WHERE itemCode = '" + itemCode + "'");
        double temp = 0.0;
        if(resultSet.next()){
            temp = resultSet.getDouble(5) - resultSet.getDouble(4);
        }
        return temp;
    }

    @Override
    public boolean modifyItemsQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("UPDATE item SET qtyOnHand = (qtyOnHand - ? ) WHERE itemCode = ?",qty,itemCode);

    }

    @Override
    public boolean updateItemTable(int updateQty, String itemCode) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE item SET  qtyOnHand = (qtyOnHand + ?) WHERE itemCode = ?",updateQty,itemCode);


    }
}
