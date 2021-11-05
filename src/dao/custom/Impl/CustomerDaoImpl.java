package dao.custom.Impl;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entity.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {


    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("INSERT INTO customer VALUES (?,?,?,?,?,?,?)",
                customer.getCustomerId(),customer.getCustomerTittle(), customer.getCustomerName(),
                customer.getCustomerAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode());

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate("DELETE FROM Customer WHERE cusId= ?",s);

    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return  CrudUtil.executeUpdate( "UPDATE customer SET cusId =?,cusTitle =?, cusName = ?,cusAddress = ?," +
                "city = ?,province = ?, postalCode = ? WHERE cusId ='" + customer.getCustomerId() +"'",
                customer.getCustomerId(),customer.getCustomerTittle(),customer.getCustomerName(),customer.getCustomerAddress(),
                customer.getCity(), customer.getProvince(),customer.getPostalCode());


    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer WHERE cusId = ?", s);
        if(resultSet.next()){
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7));
            return  customer;
        }

        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM customer");
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while (resultSet.next()){
            customerArrayList.add(new Customer(resultSet.getString(1)
                    ,resultSet.getString(2)
                    ,resultSet.getString(3)
                    ,resultSet.getString(4)
                    ,resultSet.getString(5)
                    ,resultSet.getString(6)
                    ,resultSet.getString(7)));
        }
        return customerArrayList;
    }

    @Override
    public ArrayList<String> getCustomerId() throws SQLException, ClassNotFoundException {
        ArrayList<String> cusId = new ArrayList();
        ResultSet resultSet = CrudUtil.executeQuery("SELECT cusId FROM customer");
        while (resultSet.next()){
            cusId.add(resultSet.getString(1));
        }
        return cusId;

    }



    @Override
    public String createCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.executeQuery("SELECT cusId FROM Customer ORDER BY cusId DESC LIMIT 1;");
        if(resultSet.next()){
            String customerId  = resultSet.getString(1);

            int id = Integer.parseInt(customerId.split("-")[1]);
            id = id +1;
            return  "C-"+id;
        }else{
            return "C-100";
        }
    }
}
