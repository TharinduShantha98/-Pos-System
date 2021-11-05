package bo.custom;


import bo.SuperBO;
import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO  extends SuperBO {

    public boolean addItems(ItemDTO item) throws SQLException, ClassNotFoundException;
    public  ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException;
    public  boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public  boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    public  ArrayList<String> getItemCode() throws SQLException, ClassNotFoundException;




}
