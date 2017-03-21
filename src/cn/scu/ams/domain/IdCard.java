package cn.scu.ams.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:44
 * To change this template use File | Settings | File Templates.
 */
public class IdCard {
    private Integer cardId;
    private Integer customerId;
    private String applyName;
    private String idNumber;
    private Integer sex;
    private String age;
    private String race;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private String photoPath;
    private Integer existStatus;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }
}
