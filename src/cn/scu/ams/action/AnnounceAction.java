package cn.scu.ams.action;

import cn.scu.ams.domain.Announce;
import cn.scu.ams.service.AnnounceService;
import cn.scu.ams.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ypx
 * Date: 17-3-15
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public class AnnounceAction extends ActionSupport implements ModelDriven<Announce> {
    private AnnounceService announceService;
    private Announce announce = new Announce();
    private HttpServletRequest request = ServletActionContext.getRequest();
    private Integer currentPage = 1;

    public Announce getModel(){
        return announce;
    }

    public void setAnnounceService(AnnounceService announceService){
        this.announceService = announceService;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String save(){
        String content = announce.getContent();
        String contentTitle = announce.getContentTitle();

        if(content.equals("")){
            String info = "公告内容为空";
            this.request.setAttribute("announceInfo",info);
            return INPUT;
        }else if(contentTitle.equals("")){
            String info = "公告标题为空";
            this.request.setAttribute("announceInfo",info);
            return INPUT;
        }else{
            Boolean result = announceService.save(announce);
            if(result){
                String info = "公告发布成功";
                this.request.setAttribute("announceInfo",info);
                return SUCCESS;
            }else{
                String info = "公告发布失败";
                this.request.setAttribute("announceInfo",info);
                return INPUT;
            }
        }
    }

    //找到所有公告
    public String findAll(){
        PageBean<Announce> pageBean = announceService.findByPage(currentPage);
        if(pageBean == null){

        }else{
            ActionContext.getContext().getValueStack().push(pageBean);
            return "wj_findAll";
        }
        return null;
    }

    public String detail(){

        Announce announce1 = announceService.findById(announce.getAnnounceId());
        this.request.setAttribute("curAnnounce",announce1);
        this.request.setAttribute("curPage",currentPage);
        return "wj_announceDetail";
    }

}
