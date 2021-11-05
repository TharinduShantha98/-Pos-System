package bo.custom.Impl;

import bo.custom.ItemBO;

import dao.DAOFactory;
import dao.custom.Impl.ItemDaoImpl;
import dao.custom.ItemDao;
import db.DbConnection;

import entity.Item;
import model.ItemDTO;
import views.TM.OrderManageCartTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    private final ItemDao itemDao = (ItemDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItems(ItemDTO item) throws SQLException, ClassNotFoundException {
        return  itemDao.add(new Item(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getBuyingPrice(),
                item.getUnitPrice(),item.getItemDiscount(),item.getQtyOnHand()));

    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
       Item item = itemDao.search(id);
       if(item != null) {
           return new ItemDTO(item.getItemCode(), item.getDescription(), item.getPackSize(), item.getBuyingPrice(), item.getUnitPrice(),
                   item.getItemDiscount(), item.getQtyOnHand());
       }
       return null;
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return  itemDao.update(new Item(item.getItemCode(),item.getDescription(),item.getPackSize(),item.getBuyingPrice(),
                item.getUnitPrice(),item.getItemDiscount(),item.getQtyOnHand()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
       return itemDao.delete(id);
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemArrayList = new ArrayList<>();
        PreparedStatement statement = DbConnection.getDbConnection().getConnection().prepareStatement(
                "SELECT * FROM item");
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            itemArrayList.add(new ItemDTO(
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
    public  ArrayList<String> getItemCode() throws SQLException, ClassNotFoundException {
        return  itemDao.getItemCode();
    }

}
