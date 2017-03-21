package cn.scu.ams.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:43
 * To change this template use File | Settings | File Templates.
 */
public class Company {
    private Integer companyId;
    private String companyName;
    private String applyCustomer;
    private String CustomerIdNumber;
    private String CustomerPhone;
    private String CustomerEmail;
    private String CustomerAddress;
    private String companyAddress;
    private String managerArea;
    private String managerProducts;
    private String companyDescribe;
    private String registerMoney;
    private String uploadFilesPath;
    private Integer existStatus;
    private Integer customerId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getManagerArea() {
        return managerArea;
    }

    public void setManagerArea(String managerArea) {
        this.managerArea = managerArea;
    }

    public String getManagerProducts() {
        return managerProducts;
    }

    public void setManagerProducts(String managerProducts) {
        this.managerProducts = managerProducts;
    }

    public String getCompanyDescribe() {
        return companyDescribe;
    }

    public void setCompanyDescribe(String companyDescribe) {
        this.companyDescribe = companyDescribe;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getUploadFilesPath() {
        return uploadFilesPath;
    }

    public void setUploadFilesPath(String uploadFilesPath) {
        this.uploadFilesPath = uploadFilesPath;
    }

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }

    public String getApplyCustomer() {
        return applyCustomer;
    }

    public void setApplyCustomer(String applyCustomer) {
        this.applyCustomer = applyCustomer;
    }

    public String getCustomerIdNumber() {
        return CustomerIdNumber;
    }

    public void setCustomerIdNumber(String customerIdNumber) {
        CustomerIdNumber = customerIdNumber;
    }

    public String getCustomerPhone() {
        return CustomerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        CustomerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
