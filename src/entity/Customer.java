package entity;

public class Customer {
    private String customerId;
    private String customerTittle;
    private String customerName;
    private String customerAddress;
    private String city;
    private String Province;
    private String PostalCode;

    public Customer(String customerId, String customerTittle, String customerName, String customerAddress, String city, String province, String postalCode) {
        this.customerId = customerId;
        this.customerTittle = customerTittle;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.city = city;
        Province = province;
        PostalCode = postalCode;
    }

    public Customer() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTittle() {
        return customerTittle;
    }

    public void setCustomerTittle(String customerTittle) {
        this.customerTittle = customerTittle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }
}
