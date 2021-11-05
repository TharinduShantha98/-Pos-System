package bo.custom.Impl;

import bo.custom.CustomerBO;

import dao.DAOFactory;
import dao.custom.CustomerDao;
import dao.custom.Impl.CustomerDaoImpl;
import entity.Customer;
import model.CustomerDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDao customerDao = (CustomerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);



    @Override
    public boolean customerAdd(CustomerDTO c1) throws SQLException, ClassNotFoundException {
        return customerDao.add(new Customer(c1.getCustomerId(), c1.getCustomerTittle(),c1.getCustomerName(),
                c1.getCustomerAddress(), c1.getCity(), c1.getProvince(), c1.getPostalCode()));
    }

    @Override
    public CustomerDTO customerSearch(String cusId) throws SQLException, ClassNotFoundException {
        Customer customer =  customerDao.search(cusId);
        if(customer != null) {

            return new CustomerDTO(customer.getCustomerId(), customer.getCustomerTittle(), customer.getCustomerName(),
                    customer.getCustomerAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode());
        }
        return null;
    }


    @Override
    public boolean customerUpdate(CustomerDTO c1) throws SQLException, ClassNotFoundException {
       return customerDao.update(new Customer(c1.getCustomerId(), c1.getCustomerTittle(), c1.getCustomerName(),
               c1.getCustomerAddress(), c1.getCity(), c1.getProvince(), c1.getProvince()));
    }

    @Override
    public boolean customerDelete(CustomerDTO c1) throws SQLException, ClassNotFoundException {
       return  customerDao.delete(c1.getCustomerId());

    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerArrayList = new ArrayList<>();
        for (Customer c:customerDao.getAll()
             ) {
            customerArrayList.add(new CustomerDTO(c.getCustomerId(),c.getCustomerTittle(),c.getCustomerName(),
                    c.getCustomerAddress(),c.getCity(),c.getProvince(),c.getPostalCode()));
        }
       return customerArrayList;
    }

    @Override
    public  ArrayList<String>  getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDao.getCustomerId();
    }

    @Override
    public String createCustomerId() throws SQLException, ClassNotFoundException {
        return customerDao.createCustomerId();
    }


}
