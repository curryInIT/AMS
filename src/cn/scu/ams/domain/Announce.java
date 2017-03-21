package cn.scu.ams.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:43
 * To change this template use File | Settings | File Templates.
 */
public class Announce {
    private Integer announceId;
    private String contentTitle;
    private String content;
    private Integer employeeId;
    private String pubTime;
    private Integer existStatus;

    public Integer getAnnounceId(){
        return announceId;
    }

    public void setAnnounceId(Integer announceId){
        this.announceId = announceId;
    }

    public String getContentTitle(){
        return contentTitle;
    }

    public void setContentTitle(String contentTitle){
        this.contentTitle = contentTitle;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Integer getEmployeeId(){
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId){
        this.employeeId = employeeId;
    }

    public String getPubTime(){
        return pubTime;
    }

    public void setPubTime(String pubTime){
        this.pubTime = pubTime;
    }

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }
}
