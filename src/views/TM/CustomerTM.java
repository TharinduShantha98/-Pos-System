package views.TM;

public class CustomerTM {
        private String cusId;
        private String cusTittle;
        private String cusName;
        private String cusAddress;
        private String cusCity;
        private String cusProvince;
        private String postalCode;

    public CustomerTM() {
    }

    public CustomerTM(String cusId, String cusTittle, String cusName, String cusAddress, String cusCity, String cusProvince, String postalCode) {
        this.setCusId(cusId);
        this.setCusTittle(cusTittle);
        this.setCusName(cusName);
        this.setCusAddress(cusAddress);
        this.setCusCity(cusCity);
        this.setCusProvince(cusProvince);
        this.setPostalCode(postalCode);
    }


    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getCusTittle() {
        return cusTittle;
    }

    public void setCusTittle(String cusTittle) {
        this.cusTittle = cusTittle;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity;
    }

    public String getCusProvince() {
        return cusProvince;
    }

    public void setCusProvince(String cusProvince) {
        this.cusProvince = cusProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
