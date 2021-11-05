package model;

public class UserAccountDTO {
    private String EmployId;
    private String EmployName;
    private String EmployTittle;
    private String EmployAddress;
    private String EmployPassword;

    public UserAccountDTO() {
    }

    public UserAccountDTO(String employId, String employName, String employTittle, String employAddress, String employPassword) {
        setEmployId(employId);
        setEmployName(employName);
        setEmployTittle(employTittle);
        setEmployAddress(employAddress);
        setEmployPassword(employPassword);
    }

    public String getEmployId() {
        return EmployId;
    }

    public void setEmployId(String employId) {
        EmployId = employId;
    }

    public String getEmployName() {
        return EmployName;
    }

    public void setEmployName(String employName) {
        EmployName = employName;
    }

    public String getEmployTittle() {
        return EmployTittle;
    }

    public void setEmployTittle(String employTittle) {
        EmployTittle = employTittle;
    }

    public String getEmployAddress() {
        return EmployAddress;
    }

    public void setEmployAddress(String employAddress) {
        EmployAddress = employAddress;
    }

    public String getEmployPassword() {
        return EmployPassword;
    }

    public void setEmployPassword(String employPassword) {
        EmployPassword = employPassword;
    }
}
