package cn.scu.ams.action;

import cn.scu.ams.domain.Suggestion;
import cn.scu.ams.service.SuggestionService;
import cn.scu.ams.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class SuggestionAction extends ActionSupport implements ModelDriven<Suggestion> {
    private SuggestionService suggestionService;
    private Suggestion suggestion = new Suggestion();
    private HttpServletRequest request = ServletActionContext.getRequest();
    private HttpSession session = request.getSession();
    private Integer currentPage = 1;

    public Suggestion getModel(){
        return suggestion;
    }

    public void setSuggestionService(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    public String findAll2(){
        List<Suggestion> list = suggestionService.findAll();
        if(list == null){
            String info = "问题和意见列表为空";
            this.request.setAttribute("suggestionInfo",info);
            return "index";
        }else{
            ActionContext.getContext().getValueStack().set("list",list);
            return SUCCESS;
        }
    }

    public String detail(){
        Suggestion currentSuggestion = suggestionService.findById(suggestion.getSuggestionId());
        session.setAttribute("curSuggestion", currentSuggestion);
        return "detail";
    }

    public String replyDetail(){
        Suggestion currentSuggestion = suggestionService.findById(suggestion.getSuggestionId());
        session.setAttribute("curSuggestion", currentSuggestion);
        return "reDetail";
    }

    public String reply(){
        Suggestion reSuggestion = suggestionService.findById(suggestion.getSuggestionId());
        reSuggestion.setFeedback(suggestion.getFeedback());
        reSuggestion.setDealStatus(1);
        reSuggestion.setEmployeeId(suggestion.getEmployeeId());

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        reSuggestion.setReTime(time);

        if(reSuggestion.getFeedback().equals("")){
            String info = "问题与意见回复为空";
            this.request.setAttribute("replySuggestionInfo",info);
            return "reply";
        } else{
            String info = "问题与意见回复成功";
            this.request.setAttribute("replySuggestionInfo",info);

            suggestionService.reply(reSuggestion);
            return SUCCESS;
        }
    }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        //留言提交
        public String commit(){
            Boolean commitResult = suggestionService.commit(suggestion);
            if(commitResult){
                String commitInfo="留言提交成功！";
                this.request.setAttribute("commitInfo",commitInfo);
                return "wj_success";
            }else{
                String commitInfo="留言提交失败！";
                this.request.setAttribute("commitInfo",commitInfo);
                return "wj_success";
            }
        }

        //找到当前页面的留言

        public String findAll(){
            PageBean<Suggestion> pageBean = suggestionService.findByPage(currentPage);
            if(pageBean == null){

            }else{
                ActionContext.getContext().getValueStack().push(pageBean);
                return "wj_findAll";
            }
            return null;
        }

}
