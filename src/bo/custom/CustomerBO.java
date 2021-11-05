package bo.custom;


import bo.SuperBO;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    public boolean customerAdd(CustomerDTO c1) throws SQLException, ClassNotFoundException;
    public  CustomerDTO customerSearch(String cusId) throws SQLException, ClassNotFoundException;
    public  boolean customerUpdate(CustomerDTO c1) throws SQLException, ClassNotFoundException;
    public  boolean customerDelete(CustomerDTO c1) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    public  ArrayList<String>  getCustomerId() throws SQLException, ClassNotFoundException;
    public String createCustomerId() throws SQLException, ClassNotFoundException;
}
