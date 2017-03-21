package cn.scu.ams.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ����9:41
 * To change this template use File | Settings | File Templates.
 */
public class Customer {
    private Integer customerId;
    private String account;
    private String password;
    private String email;
    private Integer existStatus;
    private String passcode;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
