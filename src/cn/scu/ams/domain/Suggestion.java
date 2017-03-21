package cn.scu.ams.domain;

/**
 * Created with IntelliJ IDEA.
 * User: 18346
 * Date: 17-3-14
 * Time: ÉÏÎç9:45
 * To change this template use File | Settings | File Templates.
 */
public class Suggestion {
    private Integer suggestionId;
    private String contentType;
    private String content;
    private String pubTime;
    private String reTime;
    private Integer dealStatus;
    private String feedback;
    private Integer employeeId;
    private Integer existStatus;

    public Integer getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(Integer suggestionId) {
        this.suggestionId = suggestionId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getReTime(){
        return reTime;
    }

    public void setReTime(String reTime){
        this.reTime = reTime;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getExistStatus() {
        return existStatus;
    }

    public void setExistStatus(Integer existStatus) {
        this.existStatus = existStatus;
    }
}
